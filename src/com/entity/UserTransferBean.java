package com.entity;

import com.entity.relation.UserRPaper;

/**
 * Created by 10388 on 2017/6/4.
 */
public class UserTransferBean {

    private int id;
    private String name;
    private String email;
    private UserRPaper[] userRPapers;
    private PaperBean[] paperBean;

    public PaperBean[] getPaperBean() {
        return paperBean;
    }

    public void setPaperBean(PaperBean[] paperBean) {
        this.paperBean = paperBean;
    }

    public UserRPaper[] getUserRPapers() {
        return userRPapers;
    }

    public void setUserRPapers(UserRPaper[] userRPapers) {
        this.userRPapers = userRPapers;
    }

    public UserTransferBean(int id, String name, String email, String signature,UserRPaper[] userRPapers,PaperBean[] paperBean) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.signature = signature;
        this.userRPapers = userRPapers;
        this.paperBean = paperBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String signature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
