package com.utills;

import com.model.Interfaces.InfoLogic;
import com.model.Interfaces.LoginLogic;
import com.test.InfoLogicTest;
import com.test.LoginLogicTest;

/*
 * 单例模式管理接口
 */
public class ModelHelper {

    private static ModelHelper instance;

    private LoginLogic loginLogic;
    private InfoLogic infoLogic;

    private ModelHelper(){
        this.loginLogic = new LoginLogicTest();
        this.infoLogic = new InfoLogicTest();
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

    public static InfoLogic getInforgicTest(){ return getInstance().infoLogic; }
}
