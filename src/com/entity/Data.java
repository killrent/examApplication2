package com.entity;

/**
 * Created by 10388 on 2017/6/1.
 */
public class Data {
    private UserBean[] USER;
    private PaperBean[] PAPER;
    private QuestionBean[] QUESTION;

    public UserBean[] getUSER() {
        return USER;
    }

    public void setUSER(UserBean[] USER) {
        this.USER = USER;
    }

    public PaperBean[] getPAPER() {
        return PAPER;
    }

    public void setPAPER(PaperBean[] PAPER) {
        this.PAPER = PAPER;
    }

    public QuestionBean[] getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(QuestionBean[] QUESTION) {
        this.QUESTION = QUESTION;
    }
}
