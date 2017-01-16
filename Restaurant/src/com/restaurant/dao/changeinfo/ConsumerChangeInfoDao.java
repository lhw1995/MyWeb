package com.restaurant.dao.changeinfo;

import com.restaurant.entity.Consumer;

/**
 * Created by lhw on 2016/12/8.
 */
public interface ConsumerChangeInfoDao {
    void updateInfo(int consumerId,String showName,String phone,String headPortrait);
    void updateInfo(int consumerId,String showName,String phone);
    Consumer getConsumerById(int id);
}
