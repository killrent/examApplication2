package com.test;

import com.entity.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Interfaces.LoginLogic;
import com.utills.ModelHelper;
import com.utills.gson.JsonManger;

import java.io.*;

public class ModelTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        JsonManger jsonManger = new JsonManger();
        Data data = jsonManger.loadDataFormJson();
        //System.out.print(data.getUSER()[0].getEmail());

        LoginLogic loginLogic = new LoginLogicTest();

        loginLogic.checkEmail("123@qq.com");
    }
}