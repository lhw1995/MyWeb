package com.restaurant.dao.register;

import com.restaurant.entity.Consumer;

import java.util.List;

/**
 * Created by lhw on 2016/12/4.
 */
public interface ConsumerRegisterDao {
    boolean exist(String userName);
    void save(Consumer consumer);
    //获得所有用户
    List<Consumer> consumerList();
}
