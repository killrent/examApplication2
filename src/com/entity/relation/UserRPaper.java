package com.entity.relation;

import com.utills.JDBCUtils.SQLColumn;
import com.utills.JDBCUtils.SQLIgnore;

/**
 * Created by 10388 on 2017/6/5.
 */
public class UserRPaper {

    private int id;

    @SQLColumn("who")
    private int userId;

    @SQLColumn("which")
    private int paperId;

    @SQLColumn("submit_time")
    private String time;

    @SQLColumn("sec_used")
    private int timeUsed;

    @SQLColumn("grade")
    private int score;

    public UserRPaper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
