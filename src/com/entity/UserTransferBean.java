package com.entity;

import com.entity.relation.UserRPaper;

import java.util.List;

/**
 * Created by 10388 on 2017/6/4.
 */
public class UserTransferBean {

    private int id;
    private String name;
    private String email;
    private String signature;
    private List<UserRPaper> userRPapers;
    private List<PaperBean> paperBeans;

    public UserTransferBean(int id, String name, String email, String signature, List<UserRPaper> userRPapers, List<PaperBean> paperBeans) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.signature = signature;
        this.userRPapers = userRPapers;
        this.paperBeans = paperBeans;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRPaper> getUserRPapers() {
        return userRPapers;
    }

    public void setUserRPapers(List<UserRPaper> userRPapers) {
        this.userRPapers = userRPapers;
    }

    public List<PaperBean> getPaperBeans() {
        return paperBeans;
    }

    public void setPaperBeans(List<PaperBean> paperBeans) {
        this.paperBeans = paperBeans;
    }
}
