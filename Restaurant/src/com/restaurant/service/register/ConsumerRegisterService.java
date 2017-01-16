package com.restaurant.service.register;

import com.restaurant.entity.Consumer;
import org.springframework.stereotype.Service;

/**
 * Created by lhw on 2016/12/4.
 */
public interface ConsumerRegisterService {
    boolean saveConsumer(Consumer consumer);
    boolean isExists(String userName);
}
