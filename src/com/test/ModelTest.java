package com.test;

import com.entity.Data;
import com.entity.PaperBean;
import com.entity.relation.UserRPaper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Interfaces.LoginLogic;
import com.model.Interfaces.SearchLogic;
import com.model.LoginLogicImpl;
import com.utills.ModelHelper;
import com.utills.Please;
import com.utills.gson.JsonManger;

import java.io.*;

public class ModelTest {

    public static void main(String[] args) throws Exception {

        SearchLogic logic = ModelHelper.getSearchLogic();

        System.out.println(Please.gson.toJson(logic.getPaperBean("2017", 1)));

        System.out.println(Please.gson.toJson(logic.getQuestionBeanByPaperId(1, 1)));
    }
}