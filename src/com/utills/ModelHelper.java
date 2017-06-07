package com.utills;

import com.model.Interfaces.LoginLogic;
import com.model.Interfaces.SearchLogic;
import com.model.LoginLogicImpl;
import com.model.SearchLogicImpl;

/*
 * 单例模式管理接口
 */
public class ModelHelper {

    private static ModelHelper instance;

    private LoginLogic loginLogic;

    private SearchLogic searchLogic;

    private ModelHelper(){
        this.loginLogic = new LoginLogicImpl();
        this.searchLogic = new SearchLogicImpl();
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

    public static SearchLogic getSearchLogic() {
        return getInstance().searchLogic;
    }
}
