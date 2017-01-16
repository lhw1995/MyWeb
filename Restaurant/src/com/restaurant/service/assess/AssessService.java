package com.restaurant.service.assess;

import com.restaurant.controller.dto.AssessDto;
import com.restaurant.entity.Assess;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by lhw on 2016/12/31.
 */
public interface AssessService {
    //添加评论
    void addAssess(AssessDto assessDto, MultipartFile[] multipartFile, HttpSession session);
    //获得评论
    Map<String,Object> getAssess(int dishId);
    //图片预览
    Map<String,Object> uploadAssessImgPre(HttpSession session,MultipartFile[] multiImg);
}
