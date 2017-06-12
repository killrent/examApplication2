package com.model;

import com.entity.PaperBean;
import com.entity.QuestionBean;
import com.model.Interfaces.SearchLogic;
import com.sun.org.apache.regexp.internal.RE;
import com.utills.JDBCUtils.GeneralRepository;
import com.utills.JDBCUtils.ResultSets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

            statement = connection.prepareStatement("SELECT COUNT(*) FROM enimaxe.paper_use WHERE which = ?");

            for (PaperBean paper : ans) {
                int id = paper.getId();
                statement.setInt(1, id);
                resultSet = statement.executeQuery();

                if (!ResultSets.isEmptySet(resultSet)) {
                    paper.setClick(resultSet.getInt(1));
                }

                resultSet.close();
            }

            statement.close();
        });


        return ans;
    }

    @Override
    public List<QuestionBean> getQuestionBeanByPaperId(int id, int sortway) {

        List<QuestionBean> ans = new ArrayList<>();

        doSQLAction(() -> {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM enimaxe.question WHERE source = ?");

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            ResultSets.addAllFromResultSet(resultSet, ans, QuestionBean.class);
            resultSet.close();
            statement.close();

            statement = connection.prepareStatement(
                    "SELECT * FROM enimaxe.question_choice WHERE from_question = ?");

            for (QuestionBean q : ans) {
                statement.setInt(1, q.getId());
                resultSet = statement.executeQuery();

                List<String> items = q.getItems();
                List<Integer> answers = q.getAnswer();

                for (int i = 0; resultSet.next(); ++i) {
                    String option = resultSet.getString("name");
                    boolean isCorrect = resultSet.getBoolean("is_correct");

                    items.add(option);
                    if (isCorrect) {
                        answers.add(i);
                    }
                }

                q.setType(answers.size() != 1 ? 2 : 1);

                resultSet.close();
            }
            statement.close();
        });

        return ans;
    }
}
