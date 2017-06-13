package com.servlet;

<<<<<<< HEAD

=======
>>>>>>> refs/remotes/EmiyaYang/master
import com.google.gson.Gson;
import com.model.Interfaces.SearchLogic;
import com.test.SearchTest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

<<<<<<< HEAD
//import javax.servlet.*;


/*
 * Created by ssHss on 2017/5/30.
 */
=======
>>>>>>> refs/remotes/EmiyaYang/master
public class SearchServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/EmiyaYang/master
        String searchResult = null;
        PrintWriter out = response.getWriter();
        searchResult = getSearchResult(request);
        out.print(searchResult);
        out.flush();
        out.close();
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/EmiyaYang/master
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    private String getSearchResult(javax.servlet.http.HttpServletRequest request)  {
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
<<<<<<< HEAD
//        String subject = request.getParameter("subject");
//        int sortWay = Integer.parseInt(request.getParameter("sortWay")) ;
=======
>>>>>>> refs/remotes/EmiyaYang/master
        int sortWay=0;

        SearchLogic searcher = new SearchTest();
        Gson gson = new Gson();
<<<<<<< HEAD
        String jsonString=null;
        if(type.equals("paper")){
            jsonString=gson.toJson(searcher.getPaperBean(keyword,sortWay));

=======
        String jsonString = null;
        if(type.equals("paper")){
            jsonString = gson.toJson(searcher.getPaperBean(keyword,sortWay));
>>>>>>> refs/remotes/EmiyaYang/master
        }
        else if(type.equals("question")){
            int paperId = Integer.parseInt(request.getParameter("paperId"));
            jsonString=gson.toJson(searcher.getQuestionBeanByPaperId(paperId,sortWay));
        }

        return jsonString;
    }
}
