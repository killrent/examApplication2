package com.entity;

/**
 * Created by 10388 on 2017/6/4.
 */
public class UserTransferBean {
    private String name;
    private String email;

    public UserTransferBean(String name, String email, String signature) {
        this.name = name;
        this.email = email;
        this.signature = signature;
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
