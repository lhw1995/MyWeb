package com.restaurant.controller.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lhw on 2016/12/8.
 */
public class ChangeInfoDto {
    private String showName;
    private String phone;
    private MultipartFile chooseHeadFile;

    @Override
    public String toString() {
        return "ChangeInfoDto{" +
                "showName='" + showName + '\'' +
                ", phone='" + phone + '\'' +
                ", chooseHeadFile=" + chooseHeadFile +
                '}';
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getChooseHeadFile() {
        return chooseHeadFile;
    }

    public void setChooseHeadFile(MultipartFile chooseHeadFile) {
        this.chooseHeadFile = chooseHeadFile;
    }
}
