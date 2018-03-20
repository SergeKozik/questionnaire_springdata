package by.kozik.quest.bean.validation;

import by.kozik.quest.bean.UserBeanRegister;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Serge on 11.02.2017.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches,Object> {

    public void initialize(PasswordMatches passwordMatches) {

    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserBeanRegister user = (UserBeanRegister)o;
        return user.getPassword().equals(user.getPasswordConfirm());
    }
}
