package com.restaurant.controller;

import com.restaurant.controller.dto.RegisterConsumerDto;
import com.restaurant.entity.Consumer;
import com.restaurant.service.register.ConsumerRegisterService;
import com.restaurant.service.security.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by lhw on 2016/12/4.
 */
@Controller
@RequestMapping("/restaurant")
public class ConsumerRegisterController {

    private ConsumerRegisterService consumerRegisterService;
    //用户注册
    @ResponseBody
    @RequestMapping(value = "/consumer",method = RequestMethod.POST)
    public Object register(@RequestBody RegisterConsumerDto registerConsumerDto){
        Consumer consumer = new Consumer();
        consumer.setUserName(registerConsumerDto.getUserName());
        consumer.setPassword(registerConsumerDto.getPassword());
        consumer.setShowName(registerConsumerDto.getShowName());
        consumer.setEmail(registerConsumerDto.getEmail());
        consumer.setPhone(registerConsumerDto.getPhone());
        consumer.setHeadPortrait("/images/headPortrait/head.jpg");
        consumer.setUserNameMD5(Security.MD5(registerConsumerDto.getUserName()));
        if (consumerRegisterService.saveConsumer(consumer)){
            //保存了用户
            return consumer;
        } else {
            //用户已存在
            return null;
        }
    }
    //检查用户是否存在
    @ResponseBody
    @RequestMapping(value = "/checkExists",method = RequestMethod.POST)
    public Object register(@RequestBody String userName){

        userName = userName.substring(0,userName.length()-1);
        if (consumerRegisterService.isExists(userName)){
            //用户已存在
            return false;
        } else {
            //用户不存在
            return true;
        }
    }
    public ConsumerRegisterService getConsumerRegisterService() {
        return consumerRegisterService;
    }

    @Resource(name = "consumerRegisterService")
    public void setConsumerRegisterService(ConsumerRegisterService consumerRegisterService) {
        this.consumerRegisterService = consumerRegisterService;
    }
}
