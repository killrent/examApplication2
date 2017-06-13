package com.model.Interfaces;

import com.entity.UserTransferBean;
import com.entity.relation.UserRPaper;

/**
 * Created by 10388 on 2017/6/8.
 */
public interface InfoLogic {

    //TODO 更新个人信息
    boolean updateInfor(int id,String name, String signature);

    //TODO 加载考试记录
    boolean loadRecord(UserTransferBean R);

    //TODO 更新考试记录
    boolean addRecord(UserRPaper record);
}
