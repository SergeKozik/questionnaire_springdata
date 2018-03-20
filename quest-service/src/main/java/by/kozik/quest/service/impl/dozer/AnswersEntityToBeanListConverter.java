package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.bean.AnswerMarkBean;
import by.kozik.quest.bean.AnswerParentBean;
import by.kozik.quest.bean.AnswerUserTextBean;
import by.kozik.quest.entity.AnswerEntity;
import by.kozik.quest.entity.AnswerMarkEntity;
import by.kozik.quest.entity.AnswerUserTextEntity;
import org.dozer.DozerConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serge_Kozik on 3/1/2017.
 */
@Service
public class AnswersEntityToBeanListConverter extends DozerConverter<List,List> {

    public AnswersEntityToBeanListConverter() {
        super(List.class, List.class);
    }

    //from entity to bean
    @Override
    public List convertTo(List list, List list2) {
        if (list==null) {
            return null;
        }
        List<AnswerParentBean> result = new ArrayList<>();
        for (Object entityObj:list) {
            if (entityObj.getClass()==AnswerEntity.class) {
                AnswerEntity answerEntity = (AnswerEntity)entityObj;
                result.add(new AnswerParentBean(answerEntity.getFormulation()));
            }
            if (entityObj.getClass()== AnswerMarkEntity.class) {
                AnswerMarkEntity answerMarkEntity = (AnswerMarkEntity)entityObj;
                result.add(new AnswerMarkBean(answerMarkEntity.getFormulation(),answerMarkEntity.getMark()));
            }
            if (entityObj.getClass()== AnswerUserTextEntity.class) {
                AnswerUserTextEntity answerUserTextEntity = (AnswerUserTextEntity)entityObj;
                result.add(new AnswerUserTextBean(answerUserTextEntity.getFormulation()));
            }
        }
        return result;
    }

    //from bean to entity
    @Override
    public List convertFrom(List list, List list2) {
        if (list==null) {
            return null;
        }
        List<AnswerEntity> result = new ArrayList<>();
        for (Object beanObj:list) {
            if (beanObj.getClass()==AnswerParentBean.class) {
                AnswerParentBean answerParentBean = (AnswerParentBean)beanObj;
                AnswerEntity answerEntity = new AnswerEntity();
                answerEntity.setFormulation(answerParentBean.getFormulation());
                result.add(answerEntity);
            }
            if (beanObj.getClass()==AnswerMarkBean.class) {
                AnswerMarkBean answerMarkBean = (AnswerMarkBean)beanObj;
                AnswerMarkEntity answerMarkEntity = new AnswerMarkEntity();
                answerMarkEntity.setFormulation(answerMarkBean.getFormulation());
                answerMarkEntity.setMark(answerMarkBean.getMark());
                result.add(answerMarkEntity);
            }
            if (beanObj.getClass()==AnswerUserTextBean.class) {
                AnswerUserTextBean answerUserTextBean = (AnswerUserTextBean)beanObj;
                AnswerUserTextEntity answerUserTextEntity = new AnswerUserTextEntity();
                answerUserTextEntity.setFormulation(answerUserTextBean.getFormulation());
                result.add(answerUserTextEntity);
            }
        }
        return result;
    }
}
