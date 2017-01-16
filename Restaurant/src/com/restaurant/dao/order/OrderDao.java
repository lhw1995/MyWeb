package com.restaurant.dao.order;

import com.restaurant.controller.dto.CartDishDto;
import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import com.restaurant.entity.DishOrder;
import com.restaurant.entity.Item;

import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/27.
 */
public interface OrderDao {
    /**
     * 检查要购买的菜品是否足够，若足够则返回空的list，
     * 若有菜品不够，则返回对应菜品的List
     * */
    List<Dish> checkEnough(Map<Dish,Integer> dishMap);
    /**
     * 创建订单和项目
     * */
    void createOrder(Consumer consumer,Map<Dish,Integer> dishMap);

    /**
     * 查询订单和项目
     */
    Map<DishOrder,List<Item>> getOrder(Consumer consumer);

    /**
     * 查询所有订单
     */
    Map<DishOrder,List<Item>> getAllOrder();

    /**
     * 确认订单
     */
    void confirmOrder(int orderId);
    /**
     * 获得item
     */
    Item getItemById(int itemId);
}
