package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.bean.UserAnswerResult;
import by.kozik.quest.bean.UserAnswerResultMark;
import by.kozik.quest.bean.UserAnswerResultText;
import by.kozik.quest.dao.AnswerDao;
import by.kozik.quest.dao.UserResultDao;
import by.kozik.quest.entity.*;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Serge on 17.03.2017.
 */
public class UserAnswerResultConverter implements CustomConverter {

    @Autowired
    private UserResultDao userResultDao;

    @Autowired
    private AnswerDao answerDao;

    @Override
    public Object convert(Object dest, Object source, Class<?> aClass, Class<?> aClass1) {
        if (source==null) {
            return null;
        }
        if (source instanceof UserAnswerResultEntity) {
            UserAnswerResultEntity sourceEntity = (UserAnswerResultEntity)source;
            AnswerEntity answerEntity = sourceEntity.getUserAnswer();
            UserAnswerResult result = null;
            if (answerEntity.getClass()== AnswerMarkEntity.class) {
                UserAnswerResultMark tmpResult = new UserAnswerResultMark();
                AnswerMarkEntity markEntity = (AnswerMarkEntity)answerEntity;
                tmpResult.setMark(markEntity.getMark());
                result=tmpResult;
            }
            if (source.getClass()== UserAnswerResultTextEntity.class) {
                UserAnswerResultText tmpResult = new UserAnswerResultText();
                UserAnswerResultTextEntity textEntity = (UserAnswerResultTextEntity)source;
                tmpResult.setUserText(textEntity.getText());
                result=tmpResult;
            }
            if (result==null) {
                result = new UserAnswerResult();
            }
            result.setId(sourceEntity.getId());
            result.setAnswerId(answerEntity.getId());
            return result;
        }
        if (source instanceof UserAnswerResult) {
            UserAnswerResult sourceBean = (UserAnswerResult)source;
            UserAnswerResultEntity result = null;
            AnswerEntity answerEntity = answerDao.findOne(sourceBean.getAnswerId());
            if (source.getClass()==UserAnswerResultText.class) {
                UserAnswerResultText answerText = (UserAnswerResultText)source;
                UserAnswerResultTextEntity tmpResult = new UserAnswerResultTextEntity();
                tmpResult.setText(answerText.getUserText());
                result = tmpResult;
            }
            if (result==null) {
                result = new UserAnswerResultEntity();
            }
            result.setUserAnswer(answerEntity);
            return result;
        }
        return null;
    }
}
