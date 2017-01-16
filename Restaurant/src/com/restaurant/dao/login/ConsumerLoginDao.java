package com.restaurant.dao.login;

import com.restaurant.entity.Consumer;

/**
 * Created by lhw on 2016/12/2.
 */
public interface ConsumerLoginDao {
    Consumer consumerLogin(String userName,String password);
}
