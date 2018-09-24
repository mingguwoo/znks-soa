package com.sh.znks.service.business;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.dto.AnswerParam;
import com.sh.znks.domain.dto.QuestionCondition;
import com.sh.znks.domain.dto.QuestionParam;

import java.util.List;

/**
 * Created by wuminggu on 2018/7/5.
 */
public interface QuestionService {
    /**
     * ������Ŀ
     * @param param
     * @return
     */
    public ResultResponse deployQuestion(QuestionParam param);

    /**
     * ��ѯ��Ŀ�б�
     * ���������⡢δ����ɸѡ
     * @param condition
     * @return
     */
    public ResultResponse getQuestionList(QuestionCondition condition);

    /**
     * ȡ��ָ����Ŀ������
     * @param questionId
     * @return
     */
    public ResultResponse questionDetail(String questionId);

    /**
     * �ύ��
     * @param params
     * @return
     */
    public ResultResponse submitAnswer(List<AnswerParam> params);


}
