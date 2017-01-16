package com.restaurant.controller;

import com.restaurant.controller.dto.AddToCartDto;
import com.restaurant.controller.dto.CartDishDto;
import com.restaurant.dao.dish.DishInfoDao;
import com.restaurant.entity.Dish;
import com.restaurant.service.dish.CartService;
import com.restaurant.service.dish.DishInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lhw on 2016/12/24.
 */
@Controller
@RequestMapping("/restaurant")
public class CartController {

    private CartService cartService;

    //删除指定id的商品
    @ResponseBody
    @RequestMapping(value = "/cart/{id}",method = RequestMethod.DELETE)
    public Map<Integer,CartDishDto> deleteFromCart(@PathVariable int id, HttpSession session) {
        return cartService.deleteFromCart(id,session);

    }

    //将商品添加至购物车
    @ResponseBody
    @RequestMapping(value = "/cart",method = RequestMethod.POST)
    public Map<Integer,CartDishDto> addToCart(@RequestBody AddToCartDto addToCartDto, HttpSession session){
        return cartService.addDishToCart(addToCartDto,session);
    }

    //改变商品数量
    @ResponseBody
    @RequestMapping(value = "/cart/{id}",method = RequestMethod.PUT)
    public Map<Integer,CartDishDto> changeCount(@PathVariable("id") int dishId,@RequestBody AddToCartDto addToCartDto, HttpSession session){
        return cartService.changeDishCount(dishId,Integer.valueOf(addToCartDto.getBuyCount()),session);
    }

    public CartService getCartService() {
        return cartService;
    }

    @Resource
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }
}
