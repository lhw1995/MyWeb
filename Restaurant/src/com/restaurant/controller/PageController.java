package com.restaurant.controller;

import com.restaurant.controller.dto.CartDishDto;
import com.restaurant.entity.Dish;
import com.restaurant.service.dish.DishInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by lhw on 2016/12/9.
 */
@Controller
@RequestMapping("/restaurant")
public class PageController {

    private DishInfoService dishInfoService;

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    @RequestMapping("/consumerinfo")
    public String changeInfo(){
        return "changeInfo";
    }
    @RequestMapping("/codes")
    public String codes(){
        return "codes";
    }
    @RequestMapping("/gallery")
    public String gallery(){
        return "gallery";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/single")
    public String single(){
        return "single";
    }
    @RequestMapping("/consumerpwd")
    public String changePwd(){
        return "changePwd";
    }
    @RequestMapping("/consumerpwd2")
    public String changePwd2(){
        return "changePwd2";
    }
    @RequestMapping("/consumerpwdfind")
    public String findPwd(){
        return "findPwd";
    }
    @RequestMapping("/dishadd")
    public String addDish(){
        return "addDish";
    }
    @RequestMapping("/dishmanage")
    public String manageDish(){
        return "manageDish";
    }
    @RequestMapping(value = "/message",method = {RequestMethod.POST,RequestMethod.GET})
    public String message(String message, ModelMap modelMap, HttpServletRequest request){
        if (message == null){
            if (request.getAttribute("message") != null)
            message = (String)request.getAttribute("message");
        }
        if ("registersuc".equals(message)) {
            modelMap.put("message", "注册成功，");
        } else if (message.equals("changepwdsuc")){
            modelMap.put("message", "密码修改成功，");
        } else if (message.equals("nologin")){
            modelMap.put("message", "未登录不能访问该页面，");
        } else if (message.equals("nomanager")){
            modelMap.put("message", "非管理员不能访问该页面，");
        }  else if (message.equals("manager")){
            modelMap.put("message", "管理员不能访问该页面，");
        }
        return "messagepage";
    }
    @RequestMapping("/singledish")
    public String singleDish(){
        return "single";
    }
    @RequestMapping("/cartpage")
    public String cartPage(HttpSession session,ModelMap modelMap){
        float sumPrice = 0;
        int sumCount = 0;
        if (session.getAttribute("cart") != null){
            Map<Integer, CartDishDto> map = (Map<Integer, CartDishDto>) session.getAttribute("cart");
            for(Map.Entry<Integer, CartDishDto> entry:map.entrySet()){
                sumPrice += entry.getValue().getBuyCount() * entry.getValue().getPrice();
                sumCount += entry.getValue().getBuyCount();
            }
            modelMap.put("sumPrice",sumPrice);
            modelMap.put("sumCount",sumCount);
        }
        return "cart";
    }
    @RequestMapping("/assesspage/{dishId}")
    public String assess(@PathVariable int dishId, @RequestParam("itemId") int itemId, String confirm,ModelMap modelMap){
        if (!"true".equals(confirm)){
            return "index";
        }
        Dish dish = dishInfoService.getDishById(dishId);
        modelMap.put("dish",dish);
        modelMap.put("itemId",itemId);
        return "assesspage";
    }

    public DishInfoService getDishInfoService() {
        return dishInfoService;
    }

    @Resource
    public void setDishInfoService(DishInfoService dishInfoService) {
        this.dishInfoService = dishInfoService;
    }
}
