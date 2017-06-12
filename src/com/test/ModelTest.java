package com.test;

import com.entity.Data;
import com.entity.PaperBean;
import com.entity.UserTransferBean;
import com.entity.relation.UserRPaper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Interfaces.LoginLogic;
import com.utills.JDBCUtils.ResultSets;
import com.utills.ModelHelper;
import com.utills.gson.JsonManger;

import java.io.*;
import java.sql.ResultSet;
import java.util.List;

public class ModelTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        JsonManger jsonManger = new JsonManger();

        LoginLogicTest loginLogicTest = new LoginLogicTest();

        List<UserRPaper> list =  loginLogicTest.getUserRPaper(2);

        UserTransferBean userTransferBean = loginLogicTest.signIn("123@qq.com","yjq80810929");


        System.out.print(userTransferBean.getUserRPapers().get(0).getAccuracy());
    }
}