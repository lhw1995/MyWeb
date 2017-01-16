package com.restaurant.controller;

import com.restaurant.entity.Dish;
import com.restaurant.service.recommend.RecommendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2017/1/2.
 */
@Controller
@RequestMapping("/restaurant")
public class RecmdController {

    private RecommendService recommendService;

     @ResponseBody
     @RequestMapping(value = "/dish/recmd",method = RequestMethod.GET)
     public List<Dish> getRecmdDish(HttpSession session){
         return recommendService.getRecommend(session);
     }

    public RecommendService getRecommendService() {
        return recommendService;
    }

    @Resource
    public void setRecommendService(RecommendService recommendService) {
        this.recommendService = recommendService;
    }
}
