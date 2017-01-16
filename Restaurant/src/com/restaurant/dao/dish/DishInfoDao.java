package com.restaurant.dao.dish;

import com.restaurant.controller.dto.ChangeDishDto;
import com.restaurant.entity.Dish;

import java.util.List;

/**
 * Created by lhw on 2016/12/19.
 */
public interface DishInfoDao {
    //获取所有菜品的数目
    int dishCount();
    //获取当前页的菜品
    List getCurPageDish(int startNum, int size);
    //添加菜品
    void addDish(Dish dish);
    //根据菜名查询菜品
    List<Dish> getDishByName(int startNum,int size,String dishName);
    //获取查询到的菜品的数目
    int dishCountByName(String dishName);
    //根据id删除菜品
    void deleteDish(int id);
    //根据id获取菜品
    Dish getDishById(int id);
    //修改菜品
    void changeDish(Dish dish);
}
