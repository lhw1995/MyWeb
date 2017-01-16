package com.restaurant.service.changeinfo;

import com.restaurant.controller.dto.ChangeInfoDto;
import com.restaurant.entity.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lhw on 2016/12/8.
 */
public interface ConsumerChangeInfoService {
    void updateInfo(HttpServletRequest request, HttpSession session, ChangeInfoDto changeInfoDto, int id) throws IOException;
}
