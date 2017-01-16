package com.restaurant.controller;

import com.restaurant.controller.dto.AssessDto;
import com.restaurant.service.assess.AssessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lhw on 2016/12/31.
 */
@Controller
@RequestMapping("/restaurant")
public class AssessController {

    private AssessService assessService;

    //添加评价
    @RequestMapping(value = "/assess",method = RequestMethod.POST)
    public RedirectView sendAssess(AssessDto assessDto, @RequestParam("assessPic") MultipartFile[] multipartFile, HttpSession session){
        assessService.addAssess(assessDto,multipartFile,session);
        if (assessDto == null){
            return new RedirectView("/restaurant/index", true, false, false);
        }
        return new RedirectView("/restaurant/order", true, false, false);
    }

    //获得某个菜品的所有评价
    @ResponseBody
    @RequestMapping(value = "/assess/{dishId}",method = RequestMethod.GET)
    public Map<String,Object> getAssess(@PathVariable int dishId){
        Map<String,Object> map = new HashMap<>();
        map = assessService.getAssess(dishId);
        return map;
    }

    //上传评论图片预览
    @ResponseBody
    @RequestMapping(value = "/assessimg",headers = "content-type=multipart/form-data",method = RequestMethod.POST)
    public Map<String,Object> uploadPre(@RequestParam(value = "assessPic") MultipartFile[] multiImg, HttpSession session){
        Map<String,Object> map = assessService.uploadAssessImgPre(session,multiImg);
        return map;
    }

    public AssessService getAssessService() {
        return assessService;
    }

    @Resource
    public void setAssessService(AssessService assessService) {
        this.assessService = assessService;
    }
}
