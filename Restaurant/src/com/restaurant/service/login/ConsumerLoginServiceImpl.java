package com.restaurant.service.login;

import com.restaurant.controller.dto.LoginConsumerDto;
import com.restaurant.dao.login.ConsumerLoginDao;
import com.restaurant.entity.Consumer;
import com.restaurant.service.security.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lhw on 2016/12/2.
 */
@Service("consumerLoginService")
public class ConsumerLoginServiceImpl implements ConsumerLoginService {

    private ConsumerLoginDao consumerLoginDao;

    //对密码加密并调用dao层的登录方法
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public Consumer consumerLogin(LoginConsumerDto loginConsumerDto) {
        return consumerLoginDao.consumerLogin(loginConsumerDto.getUserName(), Security.MD5(loginConsumerDto.getPassword()));
    }

    public ConsumerLoginDao getConsumerLoginDao() {
        return consumerLoginDao;
    }

    @Resource(name = "consumerLoginDao")
    public void setConsumerLoginDao(ConsumerLoginDao consumerLoginDao) {
        this.consumerLoginDao = consumerLoginDao;
    }
}
