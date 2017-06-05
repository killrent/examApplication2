package com.entity;

import com.utills.JDBCUtils.SQLColumn;

/**
 * Created by 10388 on 2017/6/1.
 */
public class PaperBean {

    @SQLColumn("paper.id")
    private int id;

    @SQLColumn("title")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
