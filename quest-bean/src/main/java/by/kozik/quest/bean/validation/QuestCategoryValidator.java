package by.kozik.quest.bean.validation;

import by.kozik.quest.bean.QuestCreateFormBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Serge_Kozik on 3/3/2017.
 */
public class QuestCategoryValidator implements ConstraintValidator<QuestCategory,Object> {

    public void initialize(QuestCategory questCategory) {

    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        QuestCreateFormBean quest = (QuestCreateFormBean) o;
        if (quest.isOwnCategory()) {
            if ((quest.getOwnCategoryName()!=null)&&(!quest.getOwnCategoryName().trim().isEmpty())) {
                quest.setCategory(quest.getOwnCategoryName().trim());
                return true;
            } else {
                return false;
            }
        } else {
            if ((quest.getCategory()!=null)&&(!quest.getCategory().trim().isEmpty())) {
                quest.setCategory(quest.getCategory().trim());
                return true;
            } else {
                return false;
            }
        }
    }
}
