package com.entity;

import com.entity.relation.UserRPaper;


public class Data {
    private UserBean[] USER;
    private PaperBean[] PAPER;
    private QuestionBean[] QUESTION;
    private UserRPaper[] USER_PAPER;

    public UserRPaper[] getUSER_PAPER() {
        return USER_PAPER;
    }

    public void setUSER_PAPER(UserRPaper[] USER_PAPER) {
        this.USER_PAPER = USER_PAPER;
    }

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
