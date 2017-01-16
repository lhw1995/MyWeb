package com.restaurant.service.dish;

import com.restaurant.controller.dto.AddToCartDto;
import com.restaurant.controller.dto.CartDishDto;
import com.restaurant.dao.dish.DishInfoDao;
import com.restaurant.entity.Dish;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lhw on 2016/12/26.
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    private DishInfoDao dishInfoDao;

    @Transactional(readOnly = true)
    @Override
    public Map<Integer, CartDishDto> addDishToCart(AddToCartDto addToCartDto, HttpSession session) {
        int addDishId = Integer.valueOf(addToCartDto.getDishId());
        Map<Integer, CartDishDto> map = new HashMap<>();
        Map<Integer, CartDishDto> sessionMap = null;
        Dish dish = dishInfoDao.getDishById(addDishId);
        //判断session中的map是否存在该商品
        int buyCountPre = 0;
        boolean existFlag = false;
        if (session.getAttribute("cart") != null) {
            sessionMap = (Map<Integer, CartDishDto>) session.getAttribute("cart");
            try{
                buyCountPre = sessionMap.get(addDishId).getBuyCount();
            }catch (NullPointerException e){
                buyCountPre = 0;
            }
            Set set = sessionMap.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                if ((Integer)iterator.next() == addDishId) {
                    existFlag = true;
                    break;
                }
            }
        }

        CartDishDto cartDishDto = null;
        if (existFlag) {
            //购物车中已经有该商品
            cartDishDto = new CartDishDto();
            cartDishDto.setDishId(addDishId);
            cartDishDto.setBuyCount(Integer.valueOf(addToCartDto.getBuyCount()) + buyCountPre);
            cartDishDto.setCover(dish.getCover());
            cartDishDto.setDishName(dish.getDishName());
            cartDishDto.setPrice(dish.getPrice());
        } else {
            cartDishDto = new CartDishDto();
            cartDishDto.setDishId(addDishId);
            cartDishDto.setBuyCount(Integer.valueOf(addToCartDto.getBuyCount()));
            cartDishDto.setCover(dish.getCover());
            cartDishDto.setDishName(dish.getDishName());
            cartDishDto.setPrice(dish.getPrice());
        }
        //把map原来的元素放进去
        if (sessionMap != null) {
            map.putAll(sessionMap);
        }
        map.put(addDishId,cartDishDto);
        session.setAttribute("cart",map);
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Integer, CartDishDto> deleteFromCart(int id, HttpSession session) {
        Map<Integer, CartDishDto> map = (Map<Integer, CartDishDto>) session.getAttribute("cart");
        map.remove(id);
        session.setAttribute("cart",map);
        return map;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<Integer, CartDishDto> changeDishCount(int dishId,int buyCount, HttpSession session) {
        Map<Integer, CartDishDto> map = (Map<Integer, CartDishDto>) session.getAttribute("cart");
        int id = dishId;
        CartDishDto cartDishDto = map.get(id);
        cartDishDto.setBuyCount(buyCount);
        map.put(id,cartDishDto);
        session.setAttribute("cart",map);
        return map;
    }

    public DishInfoDao getDishInfoDao() {
        return dishInfoDao;
    }

    @Resource
    public void setDishInfoDao(DishInfoDao dishInfoDao) {
        this.dishInfoDao = dishInfoDao;
    }
}
