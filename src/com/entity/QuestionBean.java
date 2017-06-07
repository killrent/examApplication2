package com.entity;

import com.utills.JDBCUtils.SQLColumn;
import com.utills.JDBCUtils.SQLIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10388 on 2017/6/1.
 */
public class QuestionBean {

    private int id;

    @SQLIgnore
    private int type;

    private String content;

    @SQLIgnore
    private List<String> items;

    @SQLIgnore
    private List<Integer> answer;

    public QuestionBean() {
        items = new ArrayList<>();
        answer = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
