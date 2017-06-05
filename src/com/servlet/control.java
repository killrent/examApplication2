package com.servlet;

import com.entity.UserTransferBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.utills.ModelHelper;
import com.model.Interfaces.LoginLogic;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "com.servlet.control")
public class control extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //进入方法后一定要优先设定request和response对象的编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if(request.getParameter("error") != null)
        if(request.getParameter("error").equals("noUser")){
            response.sendRedirect("loginPage.html");
            return;
        }

        JSONObject status = new JSONObject();

        //out对象一定要在request和response设定完编码个时候再获取
        PrintWriter out = response.getWriter();

        try {
            this.setJSON(status,request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        out.print(status);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request,response);
    }

    private void setJSON(JSONObject status, HttpServletRequest request) throws JSONException {

        LoginLogic loginLogic = ModelHelper.getLoginLogic();
        HttpSession session = request.getSession();
        UserTransferBean userTransferBean = null;

        switch (request.getParameter("type")){
            case "check": //验证注册邮箱是否存在
                if(loginLogic.checkEmail(request.getParameter("tempEmail"))){
                    status.put("success",false);
                }else{
                    status.put("success",true);
                }
                break;
            case "signIn":
                if((userTransferBean = loginLogic.signIn(request.getParameter("signInEmail"),request.getParameter("signInPassword")))!=null){
                    status.put("code",1000);
                    session.setAttribute("login","on");
                    session.setAttribute("loginEmail",request.getParameter("signInEmail"));
                    session.setAttribute("loginUser",userTransferBean);
                }else{
                    status.put("code",1001);
                }
                break;
            case "signUp":
                if((userTransferBean = loginLogic.signUp(request.getParameter("signUpEmail"),request.getParameter("signUpPassword")))!=null){
                    status.put("code",1010);
                    session.setAttribute("login","on");
                    session.setAttribute("loginEmail",request.getParameter("signUpEmail"));
                    session.setAttribute("loginUser",userTransferBean);
                }else {
                    status.put("code",1011);
                }
                break;
            case "getAccess":
                if(session.getAttribute("login") == null){
                    status.put("email",null);
                }else if(session.getAttribute("login").equals("on")){
                    status.put("email",session.getAttribute("loginEmail"));

                    userTransferBean = (UserTransferBean) session.getAttribute("loginUser");

                    status.put("userObj",userTransferBean);

                }else{
                    status.put("email",null);
                }
        }
    }
}
