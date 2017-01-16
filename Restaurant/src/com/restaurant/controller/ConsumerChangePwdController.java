package com.restaurant.controller;

import com.restaurant.entity.Consumer;
import com.restaurant.function.Captcha;
import com.restaurant.service.changepwd.ConsumerChangePwdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by lhw on 2016/12/11.
 */
@Controller
@RequestMapping("/restaurant")
public class ConsumerChangePwdController {

    private ConsumerChangePwdService consumerChangePwdService;

    //修改密码
    @RequestMapping(value = "/pwdchange3",method = RequestMethod.PUT)
    public String changePwdStep3(String password,HttpSession session,ModelMap modelMap){
        String user = (String)session.getAttribute("userNameChange");
        consumerChangePwdService.changePwd(user,password);
        modelMap.put("message","密码修改成功");
        return "messagepage";
    }

    //检查修改密码邮件是否过期
    @RequestMapping(value = "/changePwdStep2",method = RequestMethod.GET)
    public String changePwdStep2(String user,String time,HttpSession session){
        session.setAttribute("userNameChange",user);
        if ((System.currentTimeMillis() - Long.valueOf(time)) > 1000*60*5){
            return "index";
        }
        return "changePwd2";
    }

    //获得验证码
    @RequestMapping(value = "/captcha",method = RequestMethod.GET)
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        Captcha captcha = new Captcha(20, 60);
        captcha.generate();

        // 将认证码存入SESSION
        session.setAttribute("captcha", captcha.getValue());

        OutputStream oStream = response.getOutputStream();

        ImageIO.write(captcha.getImage(), "JPEG", oStream);
    }

    //检查验证码输入是否正确并发送邮件修改密码
    @RequestMapping(value = "/checkCaptcha")
    public String checkCaptcha(@RequestParam("confirm") String confirm, HttpSession session, HttpServletRequest request) throws Exception {
        String captchaValue = (String) session.getAttribute("captcha");
        Consumer consumer = (Consumer) session.getAttribute("loginUser");
        if (confirm.equals(captchaValue)){
            request.setAttribute("message","true");
            //发送邮件
            consumerChangePwdService.sendEmail(consumer);
            return "changePwd";
        } else {
            request.setAttribute("message","验证码输入错误");
            return "changePwd";
        }
    }

    public ConsumerChangePwdService getConsumerChangePwdService() {
        return consumerChangePwdService;
    }

    @Resource
    public void setConsumerChangePwdService(ConsumerChangePwdService consumerChangePwdService) {
        this.consumerChangePwdService = consumerChangePwdService;
    }
}
