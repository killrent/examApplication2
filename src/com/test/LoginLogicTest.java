package com.test;

import com.entity.*;
import com.entity.relation.UserRPaper;
import com.model.Interfaces.LoginLogic;
import com.utills.gson.JsonManger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginLogicTest implements LoginLogic {

    final int MAX_RECORD_LENGTH = 500;

    private Data data;
    private UserBean[] user;
    private PaperBean[] paper;
    private QuestionBean[] question;

    private UserRPaper[] userRPapers;

    private JsonManger jsonManger;

    public LoginLogicTest() {

        jsonManger = new JsonManger();

        try {
            data = jsonManger.loadDataFormJson();
            user = data.getUSER();
            paper = data.getPAPER();
            question = data.getQUESTION();

            userRPapers = data.getUSER_PAPER();

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

        UserTransferBean userTransferBean = new UserTransferBean(user.length,"新人驾到",email,"新兵上任三桶水！",null,null);

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

        UserRPaper[] record;

        for(UserBean x: user){
            if(email.equals((x.getEmail())) && password.equals(x.getPassword())){
                record = getUserRPaper(x.getId());
                return new UserTransferBean(x.getId(),x.getName(),x.getEmail(),x.getSignature(),record,getPaper(record));
            }
        }
        return null;
    }

    //返回指定用户的考试记录
    UserRPaper[] getUserRPaper(int id){

        List<UserRPaper> list = new ArrayList<>();

        for(UserRPaper r : userRPapers){
            if(r.getUserId() == id){
                list.add(r);
            }
        }

        return list.toArray(new UserRPaper[list.size()]);
    }

    //返回用户考试记录中对应的试卷集合，长度应与用户的考试记录相同
     PaperBean[] getPaper(UserRPaper[] userRPapers){

        List<PaperBean> list = new ArrayList<>();

        for(UserRPaper r : userRPapers){
            for(PaperBean p : paper){
                if(r.getPaperId() == p.getId()){
                    list.add(p);
                }
            }
        }

        return list.toArray(new PaperBean[list.size()]);
    }
}
