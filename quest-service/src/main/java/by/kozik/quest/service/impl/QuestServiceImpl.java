package by.kozik.quest.service.impl;

import by.kozik.quest.bean.*;
import by.kozik.quest.dao.QuestDao;
import by.kozik.quest.dao.QuestionDao;
import by.kozik.quest.entity.AnswerEntity;
import by.kozik.quest.entity.QuestEntity;
import by.kozik.quest.entity.QuestionEntity;
import by.kozik.quest.entity.UserMainResultEntity;
import by.kozik.quest.projection.QuestCategoryProjection;
import by.kozik.quest.projection.QuestTypeProjection;
import by.kozik.quest.service.EntityBeanConverter;
import by.kozik.quest.service.QuestService;
import by.kozik.quest.service.QuestTypeEnum;
import by.kozik.quest.service.SupportedLanguageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Serge on 28.02.2017.
 */
@Service
@Transactional
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestDao questDao;

    @Autowired
    private EntityBeanConverter converter;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private QuestionDao questionDao;

    private QuestShowBean putFieldStringNames(QuestShowBean bean, Locale locale) {
        bean.setLanguageName(messageSource.getMessage("message.label.quest-language",null,locale));
        bean.setCategoryName(messageSource.getMessage("message.label.quest-category",null,locale));
        bean.setTypeResultName(messageSource.getMessage("message.label.quest-type",null,locale));
        bean.setAuthorName(messageSource.getMessage("message.label.quest-author",null,locale));
        bean.setDateName(messageSource.getMessage("message.label.quest-date",null,locale));
        bean.setNumQuestionsName(messageSource.getMessage("message.label.quest-questions",null,locale));
        bean.setPassedName(messageSource.getMessage("message.label.quest-passed",null,locale));
        bean.setTypeResultNative(messageSource.getMessage(QuestTypeEnum.getTypeByName(bean.getTypeResultNative()).getNameCode(),null,locale));
        return bean;
    }

    private List<FormActionBean> returnUserActionButtons(Collection<String> permissions,String username, QuestEntity entity, FormActionBean startButton, FormActionBean deleteButton, FormActionBean viewResultsButton) {
        List<FormActionBean> concreteButtons = new ArrayList<>();
        if (permissions.contains("user-quest-start")) {
            concreteButtons.add(startButton);
        }
        if (permissions.contains("admin-quest-delete")||(permissions.contains("author-quest-delete")&&entity.getAuthor().equals(username))) {
            concreteButtons.add(deleteButton);
        }
        if (permissions.contains("admin-quest-result")||(permissions.contains("author-quest-result")&&entity.getAuthor().equals(username))) {
            concreteButtons.add(viewResultsButton);
        } else {
            if (permissions.contains("user-view-result")&&(entity.getUserResults()!=null)) {
                for (UserMainResultEntity resultEntity:entity.getUserResults()) {
                    if (username.equals(resultEntity.getUser().getLogin())) {
                        concreteButtons.add(viewResultsButton);
                        break;
                    }
                }
            }
        }
        return concreteButtons;
    }

    @Override
    public List<String> returnCategoriesByLanguage(String language) {
        List<QuestEntity> questEntities = null;
        List<String> result = new ArrayList<>();
        if ((language!=null)&&(!language.isEmpty())) {
            questEntities = questDao.findDistinctByLanguage(language);
            if (questEntities!=null) {
                for (QuestEntity projection:questEntities) {
                    result.add(projection.getCategory());
                }
            }
        }
        return result;
    }

    @Override
    public List<String> returnAvailableLanguages(Locale locale) {

        List<String> result = new ArrayList<>();
        for (SupportedLanguageEnum languageEnum:SupportedLanguageEnum.values()) {
            result.add(messageSource.getMessage(languageEnum.getCodeNative(),null,locale));
        }
        return result;

        /*List<QuestLanguageAndCategoryProjection> projections = null;
        List<String> result = new ArrayList<>();
        projections = questDao.findDistinctAllProjectedBy();
        if (projections!=null) {
            for (QuestLanguageAndCategoryProjection projection:projections) {
                result.add(projection.getLanguage());
            }
        }
        return result;*/
    }

    @Override
    public List<String> returnAvailableCategories() {
        List<QuestCategoryProjection> projections = null;
        List<String> result = new ArrayList<>();
        projections = questDao.findCategoryDistinctAllProjectedBy();
        if (projections!=null) {
            for (QuestCategoryProjection projection:projections) {
                result.add(projection.getCategory());
            }
        }
        return result;
    }

    @Override
    public List<QuestTypeEnumBean> returnAvailableTypes(Locale locale) {
        List<QuestTypeProjection> projections = null;
        List<QuestTypeEnumBean> result = new ArrayList<>();
        projections = questDao.findTypeDistinctAllProjectedBy();
        if (projections!=null) {
            for (QuestTypeProjection projection:projections) {
                QuestTypeEnum typeEnum = QuestTypeEnum.getTypeByName(projection.getType());
                if (typeEnum!=null) {
                    String localeName = messageSource.getMessage(typeEnum.getNameCode(),null,locale);
                    String enName = typeEnum.getNameEn();
                    String description = messageSource.getMessage(typeEnum.getDescriptionCode(),null,locale);
                    result.add(new QuestTypeEnumBean(enName,localeName,description));
                }
            }
        }
        return result;
    }

    @Override
    public boolean saveQuest(QuestCreateFormBean quest) {
        if (quest==null) {
            return false;
        }
        QuestEntity questEntity = converter.convertToEntity(quest,QuestEntity.class);
        List<QuestionEntity> questions = questEntity.getQuestions();
        for (QuestionEntity question:questions) {
            question.setQuest(questEntity);
            List<AnswerEntity> answers = question.getAnswers();
            for (AnswerEntity answer:answers) {
                answer.setQuestion(question);
            }
        }
        QuestEntity savedEntity = questDao.save(questEntity);
        if ((savedEntity!=null)||savedEntity.getId()>0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<QuestShowBean> returnQuestShowBeans(Collection<String> permissions,List<String> languages, List<String> types, List<String> categoies,String username, FormActionBean startButton, FormActionBean deleteButton, FormActionBean viewResultsButton,Locale locale) {
        Iterable<QuestEntity> questEntities = null;
        if ((languages==null)||(languages.isEmpty())||(types==null)||(types.isEmpty())||(categoies==null)||(categoies.isEmpty())) {
            questEntities = questDao.findAll();
        } else {
            questEntities = questDao.findByLanguageInAndCategoryInAndTypeIn(languages,categoies,types);
        }
        List<QuestShowBean> result = new ArrayList<>();
        if (questEntities!=null) {
            for (QuestEntity entity:questEntities) {
                QuestShowBean bean = converter.convertToBean(entity,QuestShowBean.class);
                bean = putFieldStringNames(bean,locale);
                List<FormActionBean> concreteButtons = returnUserActionButtons(permissions, username, entity, startButton, deleteButton, viewResultsButton);
                bean.setButtons(concreteButtons);
                result.add(bean);
            }
        }
        return result;
    }

    @Override
    public QuestShowBean returnQuestById(int questId) {
        QuestEntity entity = questDao.findOne(questId);
        if (entity!=null) {
            QuestShowBean bean = converter.convertToBean(entity,QuestShowBean.class);
            return bean;
        }
        return null;
    }

    @Override
    public List<QuestionFormBean> returnQuestionsFromQuest(int questId) {
        List<QuestionEntity> listEntity = questionDao.returnQuestionsFromQuest(questId);
        if (listEntity!=null) {
            List<QuestionFormBean> listBean = converter.convertToBeanList(listEntity,QuestionFormBean.class);
            return listBean;
        }
        return null;
    }
}
