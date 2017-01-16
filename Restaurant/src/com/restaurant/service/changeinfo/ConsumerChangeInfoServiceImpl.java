package com.restaurant.service.changeinfo;

import com.restaurant.controller.dto.ChangeInfoDto;
import com.restaurant.dao.changeinfo.ConsumerChangeInfoDao;
import com.restaurant.entity.Consumer;
import com.restaurant.function.DeleteFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * Created by lhw on 2016/12/8.
 */
@Service("consumerChangeInfoService")
public class ConsumerChangeInfoServiceImpl implements ConsumerChangeInfoService{

    private ConsumerChangeInfoDao consumerChangeInfoDao;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateInfo(HttpServletRequest request, HttpSession session, ChangeInfoDto changeInfoDto, int id) throws IOException{
        if (changeInfoDto.getChooseHeadFile().getSize()>0) {
            //改变了头像
            String path = session.getServletContext().getRealPath("/images/headPortrait");

            String fileName = changeInfoDto.getChooseHeadFile().getOriginalFilename();
            //删除原来的头像
            Consumer consumer = (Consumer)session.getAttribute("loginUser");
            if (!"/images/headPortrait/head.jpg".equals(consumer.getHeadPortrait())) {
                String lastFileName = consumer.getHeadPortrait()
                        .substring(consumer.getHeadPortrait().lastIndexOf("/"));
                DeleteFile.delete(path,lastFileName);
            }

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
                changeInfoDto.getChooseHeadFile().transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //保存至数据库
            String savePath = request.getContextPath() + "/images/headPortrait/" + newFileName;
            consumerChangeInfoDao.updateInfo(id,changeInfoDto.getShowName(),changeInfoDto.getPhone(), savePath);
        } else {
            //未改变头像
            consumerChangeInfoDao.updateInfo(id,changeInfoDto.getShowName(),changeInfoDto.getPhone());
        }
    }

    public ConsumerChangeInfoDao getConsumerChangeInfoDao() {
        return consumerChangeInfoDao;
    }

    @Resource(name = "consumerChangeInfoDao")
    public void setConsumerChangeInfoDao(ConsumerChangeInfoDao consumerChangeInfoDao) {
        this.consumerChangeInfoDao = consumerChangeInfoDao;
    }
}
