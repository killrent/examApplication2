package com.utills;

import com.model.Interfaces.LoginLogic;
import com.test.LoginLogicTest;

/*
 * 单例模式管理接口
 */
public class ModelHelper {

    private static ModelHelper instance;

    private LoginLogic loginLogic;

    private ModelHelper(){
        this.loginLogic = new LoginLogicTest();
    }

    public static ModelHelper getInstance(){
        if (instance == null){
            instance = new ModelHelper();
        }
        return instance;
    }

    public static LoginLogic getLoginLogic(){
        return getInstance().loginLogic;
    }
}
