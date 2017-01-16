package com.restaurant.controller;

import com.restaurant.entity.DishImage;
import com.restaurant.service.dish.DishInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/23.
 */
@Controller
@RequestMapping("/restaurant")
public class UploadMultiPre {

    private DishInfoService dishInfoService;

    @ResponseBody
    @RequestMapping(value = "/multiPre",headers = "content-type=multipart/form-data")
    public Map<String,Object> uploadPre(@RequestParam(value = "uploadMul") MultipartFile[] multiDishPic, HttpSession session){
        Map<String,Object> map = dishInfoService.uploadMultiDishPicPre(session,multiDishPic);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/multiPic/{id}")
    public List<DishImage> getMultiPic(@PathVariable int id){
        List<DishImage> dishImageList = dishInfoService.getDishImgList(id);
        return dishImageList;
    }

    public DishInfoService getDishInfoService() {
        return dishInfoService;
    }

    @Resource
    public void setDishInfoService(DishInfoService dishInfoService) {
        this.dishInfoService = dishInfoService;
    }
}
