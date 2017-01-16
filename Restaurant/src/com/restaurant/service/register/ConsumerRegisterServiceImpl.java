package com.restaurant.service.register;

import com.restaurant.dao.register.ConsumerRegisterDao;
import com.restaurant.entity.Consumer;
import com.restaurant.service.security.Security;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lhw on 2016/12/4.
 */
@Service("consumerRegisterService")
public class ConsumerRegisterServiceImpl implements ConsumerRegisterService {

    private ConsumerRegisterDao consumerRegisterDao;

    @Override
    public boolean isExists(String userName) {
        return consumerRegisterDao.exist(userName);
    }

    //若用户名已存在则返回false，不存在则保存用户并返回true
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public boolean saveConsumer(Consumer consumer) {
        consumer.setPassword(Security.MD5(consumer.getPassword()));
        if (consumerRegisterDao.exist(consumer.getUserName())){
            return false;
        } else {
            consumerRegisterDao.save(consumer);
            return true;
        }
    }

    public ConsumerRegisterDao getConsumerRegisterDao() {
        return consumerRegisterDao;
    }

    @Resource(name = "consumerRegisterDao")
    public void setConsumerRegisterDao(ConsumerRegisterDao consumerRegisterDao) {
        this.consumerRegisterDao = consumerRegisterDao;
    }
}
