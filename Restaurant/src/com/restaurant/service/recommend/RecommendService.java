package com.restaurant.service.recommend;

import com.restaurant.entity.Dish;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lhw on 2017/1/2.
 */
public interface RecommendService {
    //获得推荐的菜品
    List<Dish> getRecommend(HttpSession session);
}
