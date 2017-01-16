package com.restaurant.controller;

import com.restaurant.entity.Consumer;
import com.restaurant.service.changepwd.ConsumerChangePwdService;
import com.restaurant.service.register.ConsumerRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by lhw on 2016/12/13.
 */
@Controller
@RequestMapping("/restaurant")
public class ConsumerFindPwdController {

    private ConsumerRegisterService consumerRegisterService;
    private ConsumerChangePwdService consumerChangePwdService;

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public String findPwdStep1(String userName, String confirm, HttpSession session, HttpServletRequest request) throws Exception {
        //检查验证码是否输入正确
        String captchaValue = (String) session.getAttribute("captcha");
        if (!captchaValue.equals(confirm)){
            request.setAttribute("errorMsg","验证码输入错误");
            return "findPwd";
        }
        //检查用户是否存在
        if (!consumerRegisterService.isExists(userName)) {
            request.setAttribute("errorMsg","该用户不存在");
            return "findPwd";
        }
        //发送邮件
        Consumer consumer = consumerChangePwdService.getConsumerByName(userName);
        consumerChangePwdService.sendEmail(consumer);
        request.setAttribute("message","true");
        return "findPwd";
    }

    public ConsumerRegisterService getConsumerRegisterService() {
        return consumerRegisterService;
    }

    @Resource
    public void setConsumerRegisterService(ConsumerRegisterService consumerRegisterService) {
        this.consumerRegisterService = consumerRegisterService;
    }

    public ConsumerChangePwdService getConsumerChangePwdService() {
        return consumerChangePwdService;
    }

    @Resource
    public void setConsumerChangePwdService(ConsumerChangePwdService consumerChangePwdService) {
        this.consumerChangePwdService = consumerChangePwdService;
    }
}
