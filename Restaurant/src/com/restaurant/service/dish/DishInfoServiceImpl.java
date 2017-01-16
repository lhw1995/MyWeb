package com.restaurant.service.dish;

import com.restaurant.controller.dto.AddDishDto;
import com.restaurant.controller.dto.ChangeDishDto;
import com.restaurant.dao.dish.DishImgDao;
import com.restaurant.dao.dish.DishInfoDao;
import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import com.restaurant.entity.DishImage;
import com.restaurant.function.DeleteFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by lhw on 2016/12/19.
 */
@Service("dishInfoService")
public class DishInfoServiceImpl implements DishInfoService {

    private DishInfoDao dishInfoDao;
    private DishImgDao dishImgDao;


    //获取当前页的菜品
    @Transactional(readOnly = true)
    @Override
    public List<Dish> getCurPageDish(int pageNum, int size) {
        int startNum = (pageNum - 1) * size;
        return dishInfoDao.getCurPageDish(startNum,size);
    }

    //获得菜品数量
    @Transactional(readOnly = true)
    @Override
    public int dishCount() {
        return dishInfoDao.dishCount();
    }

    //添加菜品
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void addDish(AddDishDto dishDto,String path) {
        Dish dish = new Dish();
        dish.setDishName(dishDto.getDishName());
        dish.setClassify(dishDto.getClassify());
        dish.setCount(0);
        dish.setCover(path);
        dish.setInventory(dishDto.getInventory());
        dish.setPrice(dishDto.getPrice());
        dish.setScore(0);
        dishInfoDao.addDish(dish);
    }

    //根据菜名查询菜品
    @Transactional(readOnly = true)
    @Override
    public List<Dish> getDishByName(int pageNum, int size,String dishName) {
        int startNum = (pageNum - 1) * size;
        return dishInfoDao.getDishByName(startNum,size,dishName);
    }

    //获取查询到的菜品的数目
    @Transactional(readOnly = true)
    @Override
    public int dishCountByName(String dishName) {
        return dishInfoDao.dishCountByName(dishName);
    }

    //根据id删除菜品
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteDish(int id) {
        dishInfoDao.deleteDish(id);
    }

    //根据id获取菜品
    @Transactional(readOnly = true)
    @Override
    public Dish getDishById(int id) {
        return dishInfoDao.getDishById(id);
    }

    //修改菜品信息
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void changeDish(HttpSession session, HttpServletRequest request,ChangeDishDto changeDishDto, int id, MultipartFile[] multiDishPic) throws IOException {
        Dish dish = dishInfoDao.getDishById(id);
        dish.setDishName(changeDishDto.getDishName());
        dish.setPrice(changeDishDto.getPrice());
        dish.setClassify(changeDishDto.getClassify());
        dish.setInventory(changeDishDto.getInventory());
        //保存封面
        if (changeDishDto.getCover().getSize() != 0){
            //保存新图片
            String path = session.getServletContext().getRealPath("/images/food");

            String fileName = changeDishDto.getCover().getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName
                    .substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = String.valueOf(System.currentTimeMillis())
                    + "." + extensionName;

            //删除原来的封面
            String lastFileName = dish.getCover()
                    .substring(dish.getCover().lastIndexOf("/"));
            DeleteFile.delete(path,lastFileName);

            File targetFile = new File(path, newFileName);

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            //保存
            try {
                changeDishDto.getCover().transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //保存至数据库
            String savePath = request.getContextPath()+"/images/food/"+newFileName;
            dish.setCover(savePath);
        } else {
            System.out.println("没有修改图片");
        }

        //保存菜品图
        if (multiDishPic[0].getSize()>0) {
            String path = session.getServletContext().getRealPath("/images/food/multipic");
            List<String> fileName = new ArrayList<>();
            for (MultipartFile obj : multiDishPic) {
                fileName.add(obj.getOriginalFilename());
            }

            // 获取图片的扩展名
            List<String> extensionName = new ArrayList<>();
            for (int i = 0; i < fileName.size(); i++) {
                extensionName.add(fileName.get(i).substring(fileName.get(i).lastIndexOf(".") + 1));
            }

            //删除原来的文件
            List<DishImage> dishImageList = dishImgDao.getDIshImgList(dish);
            for (int i = 0; i < dishImageList.size(); i++) {
                String lastFileName = dishImageList.get(i).getImage()
                        .substring(dishImageList.get(i).getImage().lastIndexOf("/"));
                DeleteFile.delete(path,lastFileName);
            }

            dishImgDao.deleteImgs(dish);

            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            List<String> newFileName = new ArrayList<>();
            for (int i = 0; i < extensionName.size(); i++) {
                newFileName.add(String.valueOf(System.currentTimeMillis()) + "." + extensionName.get(i));
                File targetFile = new File(path, newFileName.get(i));

                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }

                //保存
                try {
                    multiDishPic[i].transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //保存至数据库
                dishImgDao.saveImgs("/images/food/multipic/" + newFileName.get(i), dish);
            }
        }
        dishInfoDao.changeDish(dish);
    }

    //上传多张菜品图预览
    @Override
    public Map<String,Object> uploadMultiDishPicPre(HttpSession session, MultipartFile[] multiDishPic) {
        Map<String,Object> map = new HashMap<>();
        String path = session.getServletContext().getRealPath("/images/food/multipic/multipicPre");
        List<String> fileName = new ArrayList<>();
        for (MultipartFile obj:multiDishPic) {
            fileName.add(obj.getOriginalFilename());
        }

        //将0.1秒之前缓存的图片清除
        File file=new File(path+"/");
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String time = tempList[i].toString().substring(tempList[i].toString().length()-17,tempList[i].toString().length()-4);
            if ((System.currentTimeMillis() - Long.valueOf(time))>100) {
                tempList[i].delete();
            }
        }

        // 获取图片的扩展名并检查文件类型
        List<String> extensionName = new ArrayList<>();
        if (fileName.size() > 3) {
            map.put("error","最多只能上传三张图片");
            return map;
        }
        for (int i = 0; i < fileName.size(); i++) {
            extensionName.add(fileName.get(i).substring(fileName.get(i).lastIndexOf(".") + 1));
            if ((!"jpg".equals(extensionName.get(i))) && (!"png".equals(extensionName.get(i))) && (!"gif".equals(extensionName.get(i)))){
                map.put("error","文件类型不符合要求");
                return map;
            }
        }

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        List<String> newFileName = new ArrayList<>();
        for (int i = 0; i < extensionName.size(); i++) {
            newFileName.add(String.valueOf(System.currentTimeMillis())+ "." + extensionName.get(i));
            File targetFile = new File(path, newFileName.get(i));

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            //保存
            try {
                multiDishPic[i].transferTo(targetFile);
                //检查图片大小
                if (targetFile.length()>1024*1024){
                    map.put("error","文件大小超出1M");
                    return map;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("message","上传成功");
        map.put("fileName",newFileName);
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DishImage> getDishImgList(int dishId) {
        Dish dish = dishInfoDao.getDishById(dishId);
        return dishImgDao.getDIshImgList(dish);
    }


    public DishInfoDao getDishInfoDao() {
        return dishInfoDao;
    }

    @Resource
    public void setDishInfoDao(DishInfoDao dishInfoDao) {
        this.dishInfoDao = dishInfoDao;
    }

    public DishImgDao getDishImgDao() {
        return dishImgDao;
    }

    @Resource
    public void setDishImgDao(DishImgDao dishImgDao) {
        this.dishImgDao = dishImgDao;
    }
}
