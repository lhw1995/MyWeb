package com.restaurant.service.order;

import com.restaurant.controller.dto.CartDishDto;
import com.restaurant.dao.dish.DishInfoDao;
import com.restaurant.dao.order.OrderDao;
import com.restaurant.entity.Consumer;
import com.restaurant.entity.Dish;
import com.restaurant.entity.DishOrder;
import com.restaurant.entity.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/27.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private DishInfoDao dishInfoDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public String buyDish(HttpSession session) {
        Map<Dish,Integer> dishMap = new HashMap<>();
        Map<Integer, CartDishDto> map = null;
        if (session.getAttribute("cart") != null) {
            map = (Map<Integer, CartDishDto>) session.getAttribute("cart");
        }
        for(Map.Entry<Integer, CartDishDto> entry:map.entrySet()){
            Dish dish = dishInfoDao.getDishById(entry.getValue().getDishId());
            dishMap.put(dish,entry.getValue().getBuyCount());
        }
        List<Dish> checkEnoughList = orderDao.checkEnough(dishMap);
        if (checkEnoughList.size() > 0){
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < checkEnoughList.size(); i++) {
                stringBuffer.append(checkEnoughList.get(i).getDishName()+",");
            }
            stringBuffer.append("数量不足");
            return String.valueOf(stringBuffer);
        }
        Consumer consumer = (Consumer) session.getAttribute("loginUser");
        orderDao.createOrder(consumer,dishMap);
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<DishOrder, List<Item>> getOrder(HttpSession session) {
        Consumer consumer = (Consumer) session.getAttribute("loginUser");
        return orderDao.getOrder(consumer);
    }

    @Transactional(readOnly = true)
    @Override
    public Map<DishOrder, List<Item>> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void confirmOrder(int orderId) {
        orderDao.confirmOrder(orderId);
    }

    public DishInfoDao getDishInfoDao() {
        return dishInfoDao;
    }

    @Resource
    public void setDishInfoDao(DishInfoDao dishInfoDao) {
        this.dishInfoDao = dishInfoDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Resource
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
