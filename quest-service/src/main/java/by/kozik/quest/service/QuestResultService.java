package by.kozik.quest.service;

import by.kozik.quest.bean.AnswerDoubleResultBean;
import by.kozik.quest.bean.QuestionFormBean;
import by.kozik.quest.bean.UserQuestResultBean;

import java.util.List;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
public interface QuestResultService {

    public List<AnswerDoubleResultBean> returnProcentChart(QuestionFormBean question);
    public double calculateTotalMark(int questId);
    public double calculateUserMark(int mainResultId);
    public UserQuestResultBean saveMainResult(UserQuestResultBean questResult);
    public List<AnswerDoubleResultBean> returnProcentChartVoteQuest(int questId);
    public List<UserQuestResultBean> showAllUsersResults(int questId);
    public List<UserQuestResultBean> showQuestResultsByUser(int questId, int userId);
    public List<UserQuestResultBean> showAllQuestsResults(int userId);
}
