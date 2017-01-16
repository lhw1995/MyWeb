package com.restaurant.controller;

import com.restaurant.controller.dto.ChangeInfoDto;
import com.restaurant.controller.dto.LoginConsumerDto;
import com.restaurant.entity.Consumer;
import com.restaurant.service.changeinfo.ConsumerChangeInfoService;
import com.restaurant.service.login.ConsumerLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by lhw on 2016/12/8.
 */
@Controller
@RequestMapping(value = "/restaurant")
public class ConsumerChangeInfoController {

    private ConsumerChangeInfoService consumerChangeInfoService;
    private ConsumerLoginService consumerLoginService;

    //修改个人信息
    @RequestMapping(value = "/consumerinfo",method = RequestMethod.POST)
    public String changeInfo(ChangeInfoDto changeInfoDto, HttpSession session, HttpServletRequest request) throws IOException{
        Consumer consumer = (Consumer)session.getAttribute("loginUser");
        consumerChangeInfoService.updateInfo(request,session,changeInfoDto,consumer.getId());
        //重新登录
        Consumer consumer2 = consumerLoginService.consumerLogin((LoginConsumerDto) session.getAttribute("loginConsumerDto"));
        session.setAttribute("loginUser",consumer2);
        return "redirect:index";
    }

    public ConsumerChangeInfoService getConsumerChangeInfoService() {
        return consumerChangeInfoService;
    }

    @Resource(name = "consumerChangeInfoService")
    public void setConsumerChangeInfoService(ConsumerChangeInfoService consumerChangeInfoService) {
        this.consumerChangeInfoService = consumerChangeInfoService;
    }

    public ConsumerLoginService getConsumerLoginService() {
        return consumerLoginService;
    }

    @Resource(name = "consumerLoginService")
    public void setConsumerLoginService(ConsumerLoginService consumerLoginService) {
        this.consumerLoginService = consumerLoginService;
    }
}
