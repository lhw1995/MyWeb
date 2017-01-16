package com.restaurant.service.changepwd;

import com.restaurant.dao.changepwd.ConsumerChangePwdDao;
import com.restaurant.entity.Consumer;
import com.restaurant.function.SendEmail;
import com.restaurant.service.security.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * Created by lhw on 2016/12/11.
 */
@Service("consumerChangePwdService")
public class ConsumerChangePwdServiceImpl implements ConsumerChangePwdService {

    private ConsumerChangePwdDao consumerChangePwdDao;

    @Override
    public void changePwd(String userName, String password) {
        consumerChangePwdDao.changePwd(userName,Security.MD5(password));
    }

    //发送修改密码的邮件
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void sendEmail(Consumer consumer) throws Exception{
        String userNameMD5 = consumer.getUserNameMD5();
        long time = System.currentTimeMillis();
        String ip = InetAddress.getLocalHost().getHostAddress();
        //设置邮件内容和收件人
        String content = "请点击链接修改密码(5分钟内有效):<a href=\"http://"+ip+":8000/restaurant/changePwdStep2?user="+userNameMD5+"&time="+time+"\">修改密码</a>";
        String recipient = consumer.getEmail();
        //发送邮件修改密码
        SendEmail.send("修改密码",content,recipient);
    }

    @Override
    public Consumer getConsumerByName(String userName) {
        return consumerChangePwdDao.getConsumerByName(userName);
    }

    public ConsumerChangePwdDao getConsumerChangePwdDao() {
        return consumerChangePwdDao;
    }

    @Resource
    public void setConsumerChangePwdDao(ConsumerChangePwdDao consumerChangePwdDao) {
        this.consumerChangePwdDao = consumerChangePwdDao;
    }
}
