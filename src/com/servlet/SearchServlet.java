package com.servlet;

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

public class SearchServlet extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String searchResult = null;
        PrintWriter out = response.getWriter();
        searchResult = getSearchResult(request);
        out.print(searchResult);
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    private String getSearchResult(javax.servlet.http.HttpServletRequest request)  {
        String type = request.getParameter("type");
        String keyword = request.getParameter("keyword");
        int sortWay=0;

        SearchLogic searcher = new SearchTest();
        Gson gson = new Gson();
        String jsonString = null;
        if(type.equals("paper")){
            jsonString = gson.toJson(searcher.getPaperBean(keyword,sortWay));
        }
        else if(type.equals("question")){
            int paperId = Integer.parseInt(request.getParameter("paperId"));
            jsonString=gson.toJson(searcher.getQuestionBeanByPaperId(paperId,sortWay));
        }

        return jsonString;
    }
}
