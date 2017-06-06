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

        // already has this email.
        System.out.println(logic.checkEmail("15dqtan@stu.edu.cn"));

        // have no this email yet.
        System.out.println(logic.checkEmail("you are doooomed!"));

        // ordinary sign up
        System.out.println(gson.toJson(logic.signUp("assault65535@gmail.com", "123456")));

        // try to sign up a duplicate account
        System.out.println(gson.toJson(logic.signUp("15dqtan@stu.edu.cn", "1234567")));

        // Access accepted, has former records.
        System.out.println(gson.toJson(logic.signIn("15dqtan@stu.edu.cn", "123456")));

        // Access accepted, no exam records.
        System.out.println(gson.toJson(logic.signIn("10086@qq.com", "123456")));

        // Access denied, wrong password.
        System.out.println(gson.toJson(logic.signIn("10086@qq.com", "1234567")));

        // Access denied, no such email.
        System.out.println(gson.toJson(logic.signIn("100861@qq.com", "123456")));
    }
}