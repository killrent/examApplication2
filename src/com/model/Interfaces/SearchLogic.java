package com.model.Interfaces;

import com.entity.PaperBean;
import com.entity.QuestionBean;

/**
 * Created by Tnecesoc on 2017/6/5.
 */
public interface SearchLogic {

    PaperBean getPaperBean(String keyword, @Deprecated int sortway);

    QuestionBean getQuestionBeanByPaperName(String keyword, int sortway);

    QuestionBean getQuestionBeanByPaperId(int id, int sortway);

}
