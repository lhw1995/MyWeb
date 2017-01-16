package com.restaurant.controller;

import com.restaurant.entity.Consumer;
import com.restaurant.entity.DishOrder;
import com.restaurant.entity.Item;
import com.restaurant.service.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/27.
 */
@Controller
@RequestMapping("/restaurant")
public class OrderController {

    private OrderService orderService;

    //下订单
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public String addDishOrder(HttpSession session, ModelMap modelMap){
        String dishMessage = orderService.buyDish(session);
        if (dishMessage != null){
            modelMap.put("message",dishMessage);
            return "messagepage";
        } else {
            modelMap.put("message","购买成功");
            session.removeAttribute("cart");
            return "messagepage";
        }
    }

    //查询订单
    @RequestMapping(value = "/order",method = RequestMethod.GET)
    public String getOrder(HttpSession session,ModelMap modelMap) {
        //Map<订单，订单对应的项目>
        Map<DishOrder,List<Item>> orderListMap = orderService.getOrder(session);
        modelMap.put("orderMap",orderListMap);
        return "orders";
    }

    //确认订单
    @ResponseBody
    @RequestMapping(value = "/order/{orderId}",method = RequestMethod.PUT)
    public Map<String,String> confirmOrder(@PathVariable("orderId") int id) {
        orderService.confirmOrder(id);
        Map<String,String> map = new HashMap<>();
        map.put("status","received");
        return map;
    }
    //查询所有订单
    @RequestMapping(value = "/order/all",method = RequestMethod.GET)
    public String getAllOrder(HttpSession session,ModelMap modelMap) {
        //Map<订单，订单对应的项目>
        Map<DishOrder,List<Item>> orderListMap = orderService.getAllOrder();
        modelMap.put("orderMap",orderListMap);
        return "orders";
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
