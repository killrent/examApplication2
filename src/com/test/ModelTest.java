package com.test;

import com.entity.Data;
import com.entity.PaperBean;
import com.entity.relation.UserRPaper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Interfaces.LoginLogic;
import com.model.LoginLogicImpl;
import com.utills.ModelHelper;
import com.utills.gson.JsonManger;

import java.io.*;

public class ModelTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
//        JsonManger jsonManger = new JsonManger();
//        Data data = jsonManger.loadDataFormJson();
//        //System.out.print(data.getUSER()[0].getEmail());
//
//        PaperBean[] papers = data.getPAPER();
//
//        LoginLogicTest loginLogic = new LoginLogicTest();
//
//        String email = "123@qq.com";
//
//        UserRPaper userRPaper[] = loginLogic.getUserRPaper(2);
//
//        for(UserRPaper x : userRPaper){
//            System.out.println(x.getId()+":"+ x.getPaperId());
//        }

        LoginLogic logic = new LoginLogicImpl();

        Gson gson = new Gson();

        System.out.println(gson.toJson(logic.signIn("10086@qq.com", "123456")));

    }
}