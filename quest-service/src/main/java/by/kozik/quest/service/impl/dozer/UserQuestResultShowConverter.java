package by.kozik.quest.service.impl.dozer;

import by.kozik.quest.bean.UserQuestResultShowBean;
import by.kozik.quest.entity.UserAnswerResultEntity;
import by.kozik.quest.entity.UserMainResultEntity;
import org.dozer.CustomConverter;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Serge_Kozik on 3/29/2017.
 */
public class UserQuestResultShowConverter implements CustomConverter {

    @Override
    public Object convert(Object dest, Object source, Class<?> aClass, Class<?> aClass1) {
        if (source==null) {
            return null;
        }
        if (source instanceof UserMainResultEntity) {
            UserMainResultEntity resultEntity = (UserMainResultEntity)source;
            UserQuestResultShowBean questResultShowBean = new UserQuestResultShowBean();
            questResultShowBean.setId(resultEntity.getId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
            dateFormat.setTimeZone(TimeZone.getDefault());
            questResultShowBean.setDate(dateFormat.format(resultEntity.getDate()));
            questResultShowBean.setQuestTitle(resultEntity.getQuest().getTitle());
            questResultShowBean.setUserName(resultEntity.getUser().getLogin());
            for (UserAnswerResultEntity answer:resultEntity.getUserAnswers()) {
                answer.getUserAnswer().getQuestion().getId()
            }
        }
    }
}
