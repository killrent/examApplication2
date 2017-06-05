package com.model.Interfaces;

import com.entity.UserTransferBean;

public interface LoginLogic {

    //TODO 判断邮箱是否被占用
    boolean checkEmail(String email);

    //TODO 添加到数据库或本地存储
    UserTransferBean signUp(String email, String password);

    //TODO 获取用户信息
    UserTransferBean signIn(String email, String password);
}
