package com.utills;

<<<<<<< HEAD
import com.model.Interfaces.InforLogic;
import com.model.Interfaces.LoginLogic;
import com.test.InforLogicTest;
=======
import com.model.Interfaces.InfoLogic;
import com.model.Interfaces.LoginLogic;
import com.test.InfoLogicTest;
>>>>>>> refs/remotes/EmiyaYang/master
import com.test.LoginLogicTest;

/*
 * 单例模式管理接口
 */
public class ModelHelper {

    private static ModelHelper instance;

    private LoginLogic loginLogic;
<<<<<<< HEAD
    private InforLogic inforLogic;

    private ModelHelper(){
        this.loginLogic = new LoginLogicTest();
        this.inforLogic = new InforLogicTest();
=======
    private InfoLogic infoLogic;

    private ModelHelper(){
        this.loginLogic = new LoginLogicTest();
        this.infoLogic = new InfoLogicTest();
>>>>>>> refs/remotes/EmiyaYang/master
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

<<<<<<< HEAD
    public static InforLogic getInforgicTest(){ return getInstance().inforLogic; }
=======
    public static InfoLogic getInforgicTest(){ return getInstance().infoLogic; }
>>>>>>> refs/remotes/EmiyaYang/master
}
