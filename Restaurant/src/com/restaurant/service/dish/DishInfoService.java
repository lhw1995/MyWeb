package com.restaurant.service.dish;

import com.restaurant.controller.dto.AddDishDto;
import com.restaurant.controller.dto.ChangeDishDto;
import com.restaurant.entity.Dish;
import com.restaurant.entity.DishImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/19.
 */
public interface DishInfoService {
    //获取所有菜品的数目
    int dishCount();
    //获取当前页的菜品
    List<Dish> getCurPageDish(int pageNum, int size);
    //添加菜品
    void addDish(AddDishDto dishDto,String path);
    //根据菜名查询菜品
    List<Dish> getDishByName(int pageNum, int size,String dishName);
    //获取查询到的菜品的数目
    int dishCountByName(String dishName);
    //根据id删除菜品
    void deleteDish(int id);
    //根据id获取菜品
    Dish getDishById(int id);
    //修改菜品信息
    void changeDish(HttpSession session, HttpServletRequest request, ChangeDishDto changeDishDto, int id, MultipartFile[] multiDishPic) throws IOException;
    //上传多张菜品图预览
    Map<String,Object> uploadMultiDishPicPre(HttpSession session, MultipartFile[] multiDishPic);
    //查询菜品的图片
    List<DishImage> getDishImgList(int dishId);

}
