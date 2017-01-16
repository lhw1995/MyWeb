package com.restaurant.dao.dish;

import com.restaurant.entity.Dish;
import com.restaurant.entity.DishImage;

import java.util.List;

/**
 * Created by lhw on 2016/12/23.
 */
public interface DishImgDao {
    void saveImgs(String fileName,Dish dish);
    void deleteImgs(Dish dish);
    List<DishImage> getDIshImgList(Dish dish);
}
