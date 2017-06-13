package com.test;

import com.entity.*;
import com.entity.relation.UserRPaper;
import com.model.Interfaces.InfoLogic;
import com.utills.gson.JsonManger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class InfoLogicTest implements InfoLogic {

    private Data data;
    private List<UserBean> user;
    private List<PaperBean> paper;
    private List<QuestionBean> question;
    private List<UserRPaper> userRPapers;

    public InfoLogicTest() {
        init();
    }

    private void init(){
        Data data;
        JsonManger jsonManger = new JsonManger();
        try {
            data =  jsonManger.loadDataFormJson();
            this.paper = data.getPAPER();
            this.userRPapers = data.getUSER_PAPER();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateInfor(int id, String name, String signature) {

        init();

        JsonManger jsonManger = new JsonManger();

        Data data;

        try {
            data = jsonManger.loadDataFormJson();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        List<UserBean> list = data.getUSER();

        UserBean targetUser = list.get(id - 1);

        targetUser.setName(name);
        targetUser.setSignature(signature);

        try {
            jsonManger.saveDataIntoJson(data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean loadRecord(UserTransferBean R) {

        init();

        JsonManger jsonManger = new JsonManger();

        List<UserRPaper> list;

        int id = R.getId();
        R.setUserRPapers(getUserRPaper(id));
        R.setPaperBeans(getPaper(R.getUserRPapers()));

        return true;
    }

    @Override
    public boolean addRecord(UserRPaper record) {

        JsonManger jsonManger = new JsonManger();

        Data data;

        try {
            data = jsonManger.loadDataFormJson();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }

        List<UserRPaper> list = data.getUSER_PAPER();

        record.setId(list.size());

        list.add(record);

        try {
            jsonManger.saveDataIntoJson(data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //返回指定用户的考试记录
    List<UserRPaper> getUserRPaper(int id){

        List<UserRPaper> list = new ArrayList<>();

        for(UserRPaper r : userRPapers){
            if(r.getUserId() == id){
                list.add(r);
            }
        }

        return list;
    }

    //返回用户考试记录中对应的试卷集合，长度应与用户的考试记录相同
    List<PaperBean> getPaper(List<UserRPaper> userRPapers){

        List<PaperBean> list = new ArrayList<>();

        for(UserRPaper r : userRPapers){
            for(PaperBean p : paper){
                if(r.getPaperId() == p.getId()){
                    list.add(p);
                }
            }
        }

        return list;
    }
}
