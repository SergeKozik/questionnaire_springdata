package by.kozik.quest.service.impl;

import by.kozik.quest.bean.*;
import by.kozik.quest.dao.AnswerDao;
import by.kozik.quest.dao.QuestDao;
import by.kozik.quest.dao.UserDao;
import by.kozik.quest.dao.UserResultDao;
import by.kozik.quest.entity.*;
import by.kozik.quest.service.EntityBeanConverter;
import by.kozik.quest.service.QuestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/13/2017.
 */
@Service
@Transactional
public class QuestResultServiceImpl implements QuestResultService {

    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private QuestDao questDao;

    @Autowired
    private UserResultDao userResultDao;

    @Autowired
    private EntityBeanConverter converter;

    @Autowired
    private UserDao userDao;

    @Override
    public List<AnswerDoubleResultBean> returnProcentChart(QuestionFormBean question) {
        List<AnswerDoubleResultBean> result = new ArrayList<>();
        int totalAnswered = 0;
        int[] givenAnswers = new int[question.getAnswers().size()];
        for (int ii=0; ii<question.getAnswers().size(); ii++) {
            givenAnswers[ii] = answerDao.returnNumUsersAnswered(question.getAnswers().get(ii).getId());
            totalAnswered+=givenAnswers[ii];
        }
        for (int ii=0; ii<question.getAnswers().size(); ii++) {
            double tmp = 100.0*givenAnswers[ii]/totalAnswered;
            result.add(new AnswerDoubleResultBean(question.getAnswers().get(ii).getFormulation(),tmp,String.format("%.1f%% (%d)",tmp,givenAnswers[ii])));
        }
        return result;
    }

    private List<AnswerDoubleResultBean> returnProcentChart(QuestionEntity question) {
        List<AnswerDoubleResultBean> result = new ArrayList<>();
        int totalAnswered = 0;
        int[] givenAnswers = new int[question.getAnswers().size()];
        for (int ii=0; ii<question.getAnswers().size(); ii++) {
            givenAnswers[ii] = answerDao.returnNumUsersAnswered(question.getAnswers().get(ii).getId());
            totalAnswered+=givenAnswers[ii];
        }
        for (int ii=0; ii<question.getAnswers().size(); ii++) {
            double tmp = 100.0*givenAnswers[ii]/totalAnswered;
            result.add(new AnswerDoubleResultBean(question.getAnswers().get(ii).getFormulation(),tmp,String.format("%.1f%% (%d)",tmp,givenAnswers[ii])));
        }
        return result;
    }

    @Override
    public List<AnswerDoubleResultBean> returnProcentChartVoteQuest(int questId) {
        QuestEntity questEntity = questDao.findOne(questId);
        if (questEntity!=null) {
            List<QuestionEntity> questions = questEntity.getQuestions();
            if (questions!=null) {
                return this.returnProcentChart(questions.get(questions.size()-1));
            }

        }
        return new ArrayList<>();
    }

    @Override
    public double calculateTotalMark(int questId) {
        QuestEntity questEntity = questDao.findOne(questId);
        double result=0;
        for (QuestionEntity question:questEntity.getQuestions()) {
            for (AnswerEntity answer:question.getAnswers()) {
                if (answer.getClass()== AnswerMarkEntity.class) {
                    AnswerMarkEntity markEntity = (AnswerMarkEntity) answer;
                    result+=markEntity.getMark();
                }
            }
        }
        return result;
    }

    @Override
    public double calculateUserMark(int mainResultId) {
        double result = 0;
        UserMainResultEntity mainResultEntity = userResultDao.findOne(mainResultId);
        for (UserAnswerResultEntity answerResult:mainResultEntity.getUserAnswers()) {
            AnswerEntity answerEntity = answerResult.getUserAnswer();
            if (answerEntity.getClass()==AnswerMarkEntity.class) {
                AnswerMarkEntity markEntity = (AnswerMarkEntity)answerEntity;
                result+=markEntity.getMark();
            }
        }
        return result;
    }

    @Override
    public UserQuestResultBean saveMainResult(UserQuestResultBean questResult) {
        UserMainResultEntity resultEntity = converter.convertToEntity(questResult,UserMainResultEntity.class);
        for(UserAnswerResultEntity answerResultEntity:resultEntity.getUserAnswers()) {
            answerResultEntity.setUserMainResult(resultEntity);
        }
        UserMainResultEntity savedEntity = userResultDao.save(resultEntity);
        if (savedEntity!=null) {
            return converter.convertToBean(savedEntity,UserQuestResultBean.class);
        } else {
            return null;
        }
    }

    @Override
    public List<UserQuestResultShowBean> showAllUsersResults(int questId) {
        List<UserMainResultEntity> listEntities = userResultDao.findAllByQuest_id(questId);
        if (listEntities==null) {
            return new ArrayList<>();        }
        List<UserQuestResultShowBean> result = converter.convertToBeanList(listEntities,UserQuestResultShowBean.class);

        return result;
    }

    @Override
    public List<UserQuestResultBean> showQuestResultsByUser(int questId, int userId) {
        List<UserMainResultEntity> listEntities = userResultDao.findAllByQuest_idAndUser_id(questId,userId);
        if (listEntities==null) {
            return new ArrayList<>();
        }
        List<UserQuestResultBean> result = converter.convertToBeanList(listEntities,UserQuestResultBean.class);
        for (UserQuestResultBean userQuestResultBean :result) {
            userQuestResultBean.setQuestName(questDao.findById(userQuestResultBean.getQuestId()).getTitle());
            userQuestResultBean.setUserName(userDao.findById(userQuestResultBean.getUserId()).getLogin());
        }
        return result;
    }

    @Override
    public List<UserQuestResultBean> showAllQuestsResults(int userId) {
        List<UserMainResultEntity> listEntities = userResultDao.findAllByUser_id(userId);
        if (listEntities==null) {
            return new ArrayList<>();
        }
        List<UserQuestResultBean> result = converter.convertToBeanList(listEntities,UserQuestResultBean.class);
        for (UserQuestResultBean userQuestResultBean :result) {
            userQuestResultBean.setQuestName(questDao.findById(userQuestResultBean.getQuestId()).getTitle());
            userQuestResultBean.setUserName(userDao.findById(userQuestResultBean.getUserId()).getLogin());
        }
        return result;
    }
}
