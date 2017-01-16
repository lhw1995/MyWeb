package com.restaurant.service.changepwd;

import com.restaurant.entity.Consumer;

/**
 * Created by lhw on 2016/12/11.
 */
public interface ConsumerChangePwdService {
    void changePwd(String userName,String password);
    void sendEmail(Consumer consumer) throws Exception;
    Consumer getConsumerByName(String userName);
}
