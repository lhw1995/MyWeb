package com.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/restaurant")
public class HeadPorUploadPre {

    //上传头像预览并检查
    @ResponseBody
    @RequestMapping(value = "/uploadPreview",headers = "content-type=multipart/form-data")
    public Map<String,Object> uploadPre(@RequestParam(value = "chooseHeadFile") MultipartFile chooseHeadFile, HttpSession session, HttpServletRequest request){
        String path = session.getServletContext().getRealPath("/images/headPortrait/headPortraitPre");
        String fileName = chooseHeadFile.getOriginalFilename();
        Map<String,Object> map = new HashMap<>();
        System.out.println(path);

        //将0.1秒之前缓存的图片清除
        File file=new File(path+"/");
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String time = tempList[i].toString().substring(tempList[i].toString().length()-17,tempList[i].toString().length()-4);
            if ((System.currentTimeMillis() - Long.valueOf(time))>100) {
                tempList[i].delete();
            }
        }
        // 获取图片的扩展名
        String extensionName = fileName
                .substring(fileName.lastIndexOf(".") + 1);
        //检查文件类型
        if ((!"jpg".equals(extensionName)) && (!"png".equals(extensionName)) && (!"gif".equals(extensionName))){
            map.put("error","文件类型不符合要求");
            return map;
        }

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        String newFileName = String.valueOf(System.currentTimeMillis())
                + "." + extensionName;

        File targetFile = new File(path, newFileName);

        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            chooseHeadFile.transferTo(targetFile);
            //检查图片大小
            if (targetFile.length()>1024*1024){
                map.put("error","文件大小超出1M");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("message","上传成功");
        map.put("fileName",newFileName);
        return map;
    }
}
