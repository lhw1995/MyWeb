package com.restaurant.service.login;

import com.restaurant.controller.dto.LoginConsumerDto;
import com.restaurant.entity.Consumer;

/**
 * Created by lhw on 2016/12/2.
 */
public interface ConsumerLoginService {
    Consumer consumerLogin(LoginConsumerDto loginConsumerDto);
}
