package com.test;

import com.entity.*;
import com.model.Interfaces.LoginLogic;
import com.utills.gson.JsonManger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;


public class LoginLogicTest implements LoginLogic {

    private Data data;
    private UserBean[] user;
    private PaperBean[] paper;
    private QuestionBean[] question;
    private JsonManger jsonManger;

    public LoginLogicTest() {

        jsonManger = new JsonManger();

        try {
            data = jsonManger.loadDataFormJson();
            user = data.getUSER();
            paper = data.getPAPER();
            question = data.getQUESTION();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean checkEmail(String email) {

        for(UserBean x: user){
            if(email.equals(x.getEmail()))
                return true;
        }

        return false;
    }

    @Override
    public UserTransferBean signUp(String email, String password) {

        user = Arrays.copyOf(user,user.length + 1);

        user[user.length - 1] = new UserBean(user.length,"新人驾到",email,password,"新兵上任三桶水！");

        data.setUSER(user);

        UserTransferBean userTransferBean = new UserTransferBean("新人驾到",email,"新兵上任三桶水！");

        try {
            jsonManger.saveDataIntoJson(data);
            return userTransferBean;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserTransferBean signIn(String email, String password) {

        for(UserBean x: user){
            if(email.equals((x.getEmail())) && password.equals(x.getPassword())){
                return new UserTransferBean(x.getName(),x.getEmail(),x.getSignature());
            }
        }
        return null;
    }
}
