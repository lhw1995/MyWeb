package com.restaurant.controller;

import com.restaurant.controller.dto.AddDishDto;
import com.restaurant.controller.dto.ChangeDishDto;
import com.restaurant.controller.dto.ChangeInfoDto;
import com.restaurant.controller.model.Page;
import com.restaurant.entity.Dish;
import com.restaurant.service.dish.DishInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/19.
 */
@Controller
@RequestMapping(value = "/restaurant")
public class DishController {

    private DishInfoService dishInfoService;
    private Page page;

    //获得菜品信息
    @ResponseBody
    @RequestMapping(value = "/dish",method = RequestMethod.GET)
    public Map<String,Object> getDish(int currentPage) {
        int dishCount = dishInfoService.dishCount();
        if (dishCount % 5 == 0) {
            page.setTotalPageCount(dishCount / 5);
        } else {
            page.setTotalPageCount(dishCount / 5 + 1);
        }
        if (currentPage > page.getTotalPageCount()){
            page.setCurrPageNo(page.getTotalPageCount());
        } else if(currentPage <= 0){
            page.setCurrPageNo(1);
        }else {
            page.setCurrPageNo(currentPage);
        }
        page.setPageSize(5);
        List<Dish> dishCurList = dishInfoService.getCurPageDish(page.getCurrPageNo(), page.getPageSize());

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("dishCurList",dishCurList);
        return map;
    }

    //添加菜品
    @RequestMapping(value = "/dish",method = RequestMethod.POST)
    public String addDish(AddDishDto dishDto, HttpSession session, HttpServletRequest request){
        String path = session.getServletContext().getRealPath("/images/food");

        String fileName = dishDto.getCover().getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = fileName
                .substring(fileName.lastIndexOf(".") + 1);
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis())
                + "." + extensionName;

        File targetFile = new File(path, newFileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            dishDto.getCover().transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //保存至数据库
        String savePath = request.getContextPath()+"/images/food/"+newFileName;
        dishInfoService.addDish(dishDto,savePath);
        return "redirect:dishmanage";
    }

    //菜品封面预览
    @ResponseBody
    @RequestMapping(value = "/uploadDishPre",headers = "content-type=multipart/form-data")
    public Map<String,Object> uploadPre(@RequestParam(value = "cover") MultipartFile cover, HttpSession session, HttpServletRequest request){
        String path = session.getServletContext().getRealPath("/images/food/pre");
        String fileName = cover.getOriginalFilename();
        Map<String,Object> map = new HashMap<>();

        //将0.1秒之前缓存的图片清除
        File file=new File(path+"/");
        File[] tempList = file.listFiles();
        System.out.println("该目录下对象个数："+tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            String time = tempList[i].toString().substring(tempList[i].toString().length()-17,tempList[i].toString().length()-4);
            if ((System.currentTimeMillis() - Long.valueOf(time))>100) {
                tempList[i].delete();
            }
        }
        // 获取图片的扩展名
        String extensionName = fileName
                .substring(fileName.lastIndexOf(".") + 1);
        //检查文件类型
        if ((!"jpg".equals(extensionName)) && (!"png".equals(extensionName)) && (!"gif".equals(extensionName))){
            map.put("error","文件类型不符合要求");
            return map;
        }

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis())
                + "." + extensionName;

        File targetFile = new File(path, newFileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            cover.transferTo(targetFile);
            //检查图片大小
            if (targetFile.length()>1024*1024){
                map.put("error","文件大小超出1M");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("message","上传成功");
        map.put("fileName",newFileName);
        return map;
    }

    //根据菜名查询菜品
    @ResponseBody
    @RequestMapping(value = "/dish/{dishName}",method = RequestMethod.GET)
    public Object getDishByName(@PathVariable("dishName") String dishName,int currentPage){
        int dishCount = dishInfoService.dishCountByName(dishName);
        if (dishCount % 5 == 0) {
            page.setTotalPageCount(dishCount / 5);
        } else {
            page.setTotalPageCount(dishCount / 5 + 1);
        }
        if (currentPage > page.getTotalPageCount()){
            page.setCurrPageNo(page.getTotalPageCount());
        } else if(currentPage <= 0){
            page.setCurrPageNo(1);
        }else {
            page.setCurrPageNo(currentPage);
        }
        page.setPageSize(5);
        List<Dish> dishCurList = dishInfoService.getDishByName(page.getCurrPageNo(), page.getPageSize(),dishName);

        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("dishCurList",dishCurList);
        return map;
    }

    //根据id删除菜品
    @ResponseBody
    @RequestMapping(value = "/dish/{id}",method = RequestMethod.DELETE)
    public void deleteDish(@PathVariable("id") String id){
        System.out.println("删除菜品");
        dishInfoService.deleteDish(Integer.valueOf(id));
    }
    //进入修改菜品页面
    @RequestMapping(value = "/dishinfo/{id}",method = RequestMethod.GET)
    public String changeDish(@PathVariable("id") String id, ModelMap modelMap){
        Dish dish = dishInfoService.getDishById(Integer.valueOf(id));
        modelMap.put("dish",dish);
        return "changeDish";
    }
    //修改菜品信息
    @RequestMapping(value = "/dishchange2/{id}",method = RequestMethod.POST)
    public String changeDishInfo(@PathVariable("id") String id,@RequestParam(value = "uploadMul") MultipartFile[] multiDishPic, ChangeDishDto changeDishDto, HttpSession session,HttpServletRequest request) throws IOException {
        dishInfoService.changeDish(session,request,changeDishDto,Integer.valueOf(id),multiDishPic);
        return "redirect:../dishmanage";
    }

    //获得单个菜品信息
    @RequestMapping(value = "/singledish/{id}")
    public String getSingleDish(@PathVariable("id") String id,ModelMap modelMap) {
        Dish dish = dishInfoService.getDishById(Integer.valueOf(id));
        List dishImgList = dishInfoService.getDishImgList(Integer.valueOf(id));
        modelMap.put("dish",dish);
        modelMap.put("dishImgList",dishImgList);
        return "single";
    }


    public DishInfoService getDishInfoService() {
        return dishInfoService;
    }

    @Resource
    public void setDishInfoService(DishInfoService dishInfoService) {
        this.dishInfoService = dishInfoService;
    }

    public Page getPage() {
        return page;
    }

    @Resource
    public void setPage(Page page) {
        this.page = page;
    }
}
