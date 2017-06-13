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

<<<<<<< HEAD

/**
 * Created by ssHss on 2017/5/30.
 */
public class SearchTest implements SearchLogic {


=======
public class SearchTest implements SearchLogic {

>>>>>>> refs/remotes/EmiyaYang/master
    @Override
    public List<PaperBean> getPaperBean(String keyword, int sortway)  {
        List<PaperBean> ans = new ArrayList<>();
        JsonManger jsonManger =new JsonManger();
        Data data;
        try {
            data = jsonManger.loadDataFormJson();
<<<<<<< HEAD
            Pattern pattern =Pattern.compile(keyword,Pattern.CASE_INSENSITIVE);
=======
            Pattern pattern = Pattern.compile(keyword,Pattern.CASE_INSENSITIVE);
>>>>>>> refs/remotes/EmiyaYang/master
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
<<<<<<< HEAD
        }catch (UnsupportedEncodingException e){}

=======
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
>>>>>>> refs/remotes/EmiyaYang/master

        return ans;
    }
}
