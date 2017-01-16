package com.restaurant.service.order;

import com.restaurant.entity.DishOrder;
import com.restaurant.entity.Item;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/27.
 */
public interface OrderService {
    //下订单
    String buyDish(HttpSession session);
    //查询订单
    Map<DishOrder,List<Item>> getOrder(HttpSession session);
    //查询所有订单
    Map<DishOrder,List<Item>> getAllOrder();
    //确认订单
    void confirmOrder(int orderId);
}
