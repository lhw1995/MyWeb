package com.restaurant.dao.changepwd;

import com.restaurant.entity.Consumer;

/**
 * Created by lhw on 2016/12/11.
 */
public interface ConsumerChangePwdDao {
    void changePwd(String userName,String password);
    Consumer getConsumerByName(String userName);
}
