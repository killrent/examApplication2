package com.servlet;

import com.entity.relation.UserRPaper;
import com.model.Interfaces.InfoLogic;
import com.utills.ModelHelper;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 10388 on 2017/6/13.
 */
@WebServlet(name = "infoServlet")
public class infoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        if(request.getParameter("userId") == null){
            response.sendRedirect("loginPage.html");
            return;
        }

        PrintWriter writer = response.getWriter();

        int userId = Integer.parseInt(request.getParameter("userId"));

        int paperId = Integer.parseInt(request.getParameter("paperId"));
        int timeUsed = Integer.parseInt(request.getParameter("timeUsed"));
        String finishTime = request.getParameter("finishTime");
        float accuracy = Float.parseFloat(request.getParameter("accuracy"));

        InfoLogic infoLogic = ModelHelper.getInforgic();
        infoLogic.addRecord(new UserRPaper(userId,paperId,finishTime,timeUsed,accuracy));

        JSONObject status = new JSONObject();

        status.put("success",true);

        writer.print(status);
        writer.flush();
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
