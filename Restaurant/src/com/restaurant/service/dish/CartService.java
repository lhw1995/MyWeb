package com.restaurant.service.dish;

import com.restaurant.controller.dto.AddToCartDto;
import com.restaurant.controller.dto.CartDishDto;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by lhw on 2016/12/26.
 */
public interface CartService {
    //向购物车中添加商品
    Map<Integer,CartDishDto> addDishToCart(AddToCartDto addToCartDto,HttpSession session);
    //删除购物车中的商品
    Map<Integer,CartDishDto> deleteFromCart(int id,HttpSession session);
    //改变购物车中商品的数量
    Map<Integer,CartDishDto> changeDishCount(int dishId,int buyCount,HttpSession session);
}
