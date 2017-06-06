package com.model;

import com.entity.PaperBean;
import com.entity.QuestionBean;
import com.model.Interfaces.SearchLogic;
import com.utills.JDBCUtils.GeneralRepository;
import com.utills.JDBCUtils.ResultSets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tnecesoc on 2017/6/6.
 */
public class SearchLogicImpl extends GeneralRepository implements SearchLogic {

    @Override
    public List<PaperBean> getPaperBean(String keyword, int sortway) {

        List<PaperBean> ans = new ArrayList<>();


        doSQLAction(() -> {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM enimaxe.paper WHERE title LIKE ?");

            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();

            ResultSets.addAllFromResultSet(resultSet, ans, PaperBean.class);

            resultSet.close();
            statement.close();
        });


        return ans;
    }

    @Override
    public List<QuestionBean> getQuestionBeanByPaperId(int id, int sortway) {

        List<QuestionBean> ans = new ArrayList<>();

//        doSQLAction();

        return ans;
    }
}
