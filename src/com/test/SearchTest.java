package com.test;

import com.entity.Data;
import com.entity.PaperBean;
import com.entity.QuestionBean;
import com.model.Interfaces.SearchLogic;
import com.utills.gson.JsonManger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by ssHss on 2017/5/30.
 */
public class SearchTest implements SearchLogic {


    @Override
    public List<PaperBean> getPaperBean(String keyword, int sortway)  {
        List<PaperBean> ans = new ArrayList<>();
        JsonManger jsonManger =new JsonManger();
        Data data;
        try {
            data = jsonManger.loadDataFormJson();
            Pattern pattern =Pattern.compile(keyword,Pattern.CASE_INSENSITIVE);
            Matcher matcher;
            for(int i=0;i<data.getPAPER().size();i++){
                matcher = pattern.matcher(data.getPAPER().get(i).getName());
                if (matcher.find()){
                    System.out.print(data.getPAPER().get(i).getName());
                    ans.add(data.getPAPER().get(i));
                }
            }
        }catch (UnsupportedEncodingException e){

        }

        return ans;

    }

    public List<QuestionBean> getQuestionBeanByPaperId(int id, int sortway){
        List<QuestionBean> ans = new ArrayList<>();
        JsonManger jsonManger =new JsonManger();
        int paperId = id;
        Data data;
        try {
            data = jsonManger.loadDataFormJson();
            for(int i=0;i<data.getQUESTION().size();i++){
                if (paperId==data.getQUESTION().get(i).getSource()){
                    ans.add(data.getQUESTION().get(i));
                }
            }
        }catch (UnsupportedEncodingException e){}


        return ans;
    }
}
