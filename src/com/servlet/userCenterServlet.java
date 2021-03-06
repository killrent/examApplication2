﻿package com.servlet;

import com.entity.UserTransferBean;
<<<<<<< HEAD
import com.model.Interfaces.InforLogic;
=======
import com.model.Interfaces.InfoLogic;
>>>>>>> refs/remotes/EmiyaYang/master
import com.utills.ModelHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "userCenterServlet")
public class userCenterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String newName = request.getParameter("newName");
        String newSignature = request.getParameter("newSignature");

        HttpSession session = request.getSession();

        //重新加载用户信息
        UserTransferBean userTransferBean = (UserTransferBean)session.getAttribute("loginUser");

        int id = userTransferBean.getId();
        InfoLogic infoLogic = ModelHelper.getInforgic();
        infoLogic.updateInfor(id,newName,newSignature);

        userTransferBean.setName(newName);
        userTransferBean.setSignature(newSignature);

        session.setAttribute("loginUser",userTransferBean);

        response.sendRedirect("user-center.html");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
