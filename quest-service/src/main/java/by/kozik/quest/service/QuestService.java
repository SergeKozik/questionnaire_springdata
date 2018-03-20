package by.kozik.quest.service;

import by.kozik.quest.bean.*;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Created by Serge on 28.02.2017.
 */
public interface QuestService {

    public List<String> returnCategoriesByLanguage(String language);
    public List<String> returnAvailableLanguages(Locale locale);
    public List<String> returnAvailableCategories();
    public boolean saveQuest(QuestCreateFormBean quest);
    public List<QuestShowBean> returnQuestShowBeans(Collection<String> permissions,List<String> languages, List<String> types, List<String> categoies,String username, FormActionBean startButton, FormActionBean deleteButton, FormActionBean viewResultsButton,Locale locale);
    public QuestShowBean returnQuestById(int questId);
    public List<QuestTypeEnumBean> returnAvailableTypes(Locale locale);
    public List<QuestionFormBean> returnQuestionsFromQuest(int questId);
}
