package com.restaurant.controller;

import com.restaurant.controller.dto.LoginConsumerDto;
import com.restaurant.entity.Consumer;
import com.restaurant.service.login.ConsumerLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by lhw on 2016/12/1.
 */
@Controller
@RequestMapping("/restaurant")
public class ConsumerLoginController {

    private ConsumerLoginService consumerLoginService;

    //用户登录
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(@RequestBody LoginConsumerDto loginConsumerDto, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        //调用Service层的登录方法，得到Consumer实例
        Consumer consumer = consumerLoginService.consumerLogin(loginConsumerDto);
        session.setAttribute("loginConsumerDto",loginConsumerDto);
        //登录成功
        if (consumer != null){
            if ("on".equals(loginConsumerDto.getRemName())){
                Cookie loginCookie = new Cookie("userName", loginConsumerDto.getUserName());
                response.addCookie(loginCookie);
            }
            session.setAttribute("loginUser",consumer);
        } else {
            //登录失败
            return null;
        }
        return consumer;
    }


    //退出登录
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:index";
    }

    public ConsumerLoginService getConsumerLoginService() {
        return consumerLoginService;
    }

    @Resource(name = "consumerLoginService")
    public void setConsumerLoginService(ConsumerLoginService consumerLoginService) {
        this.consumerLoginService = consumerLoginService;
    }
}
