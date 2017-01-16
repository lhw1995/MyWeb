package com.restaurant.service.assess;

import com.restaurant.controller.dto.AssessDto;
import com.restaurant.dao.assess.AssessDao;
import com.restaurant.dao.changeinfo.ConsumerChangeInfoDao;
import com.restaurant.dao.order.OrderDao;
import com.restaurant.entity.Assess;
import com.restaurant.entity.AssessImage;
import com.restaurant.entity.Consumer;
import com.restaurant.entity.Item;
import com.restaurant.function.DeleteFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lhw on 2016/12/31.
 */
@Service("assessService")
public class AssessServiceImpl implements AssessService {

    private OrderDao orderDao;
    private AssessDao assessDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void addAssess(AssessDto assessDto, MultipartFile[] multipartFile, HttpSession session) {
        Item item = orderDao.getItemById(assessDto.getItemId());
        item.setScore(assessDto.getScore());
        assessDao.updateScore(item);

        Consumer consumer = (Consumer) session.getAttribute("loginUser");

        Assess assess = new Assess();
        assess.setConsumer(consumer);
        assess.setItem(item);
        assess.setTime(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()));
        assess.setContent(assessDto.getContent());

        assessDao.saveAssess(assess);
        for (int i = 0; i < multipartFile.length; i++) {
            if(multipartFile[i].getSize() > 0) {

                String path = session.getServletContext().getRealPath("/images/assess");

                String fileName = multipartFile[i].getOriginalFilename();

                // 获取图片的扩展名
                String extensionName = fileName
                        .substring(fileName.lastIndexOf(".") + 1);
                // 新的图片文件名 = 获取时间戳+"."图片扩展名
                String newFileName = String.valueOf(System.currentTimeMillis())
                        + "." + extensionName;

                File targetFile = new File(path, newFileName);

                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }

                //保存
                try {
                    multipartFile[i].transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //保存至数据库
                String savePath = "/images/assess/" + newFileName;

                AssessImage assessImage = new AssessImage();
                assessImage.setAssess(assess);
                assessImage.setImage(savePath);
                assessDao.saveAssessImg(assessImage);
            }
        }
    }

    @Override
    public Map<String,Object> getAssess(int dishId) {
        return assessDao.getAssess(dishId);
    }

    @Override
    public Map<String, Object> uploadAssessImgPre(HttpSession session, MultipartFile[] multiImg) {
        Map<String,Object> map = new HashMap<>();
        String path = session.getServletContext().getRealPath("/images/assess/assessimgpre");
        List<String> fileName = new ArrayList<>();
        for (MultipartFile obj:multiImg) {
            fileName.add(obj.getOriginalFilename());
        }

        //将0.1秒之前缓存的图片清除
        File file=new File(path+"/");
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            String time = tempList[i].toString().substring(tempList[i].toString().length()-17,tempList[i].toString().length()-4);
            if ((System.currentTimeMillis() - Long.valueOf(time))>100) {
                tempList[i].delete();
            }
        }

        // 获取图片的扩展名并检查文件类型
        List<String> extensionName = new ArrayList<>();
        if (fileName.size() > 5) {
            map.put("error","最多只能上传五张图片");
            return map;
        }
        for (int i = 0; i < fileName.size(); i++) {
            extensionName.add(fileName.get(i).substring(fileName.get(i).lastIndexOf(".") + 1));
            if ((!"jpg".equals(extensionName.get(i))) && (!"png".equals(extensionName.get(i))) && (!"gif".equals(extensionName.get(i)))){
                map.put("error","文件类型不符合要求");
                return map;
            }
        }

        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        List<String> newFileName = new ArrayList<>();
        for (int i = 0; i < extensionName.size(); i++) {
            newFileName.add(String.valueOf(System.currentTimeMillis())+ "." + extensionName.get(i));
            File targetFile = new File(path, newFileName.get(i));

            if(!targetFile.exists()){
                targetFile.mkdirs();
            }

            //保存
            try {
                multiImg[i].transferTo(targetFile);
                //检查图片大小
                if (targetFile.length()>1024*1024){
                    map.put("error","文件大小超出1M");
                    return map;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("message","上传成功");
        map.put("fileName",newFileName);
        return map;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    @Resource
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public AssessDao getAssessDao() {
        return assessDao;
    }

    @Resource
    public void setAssessDao(AssessDao assessDao) {
        this.assessDao = assessDao;
    }
}
