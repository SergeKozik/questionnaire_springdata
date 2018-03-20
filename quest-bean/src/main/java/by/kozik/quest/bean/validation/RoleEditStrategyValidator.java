package by.kozik.quest.bean.validation;

import by.kozik.quest.bean.RoleBeanFromForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Serge on 16.02.2017.
 */
public class RoleEditStrategyValidator implements ConstraintValidator<FlagCreateRoleMatches,Object> {

    @Override
    public void initialize(FlagCreateRoleMatches flagCreateRoleMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        RoleBeanFromForm roleForm = (RoleBeanFromForm)o;
        if ("create_own".equals(roleForm.getFlagCreate())) {
            if ((roleForm.getOwnRoleName()==null)||(roleForm.getOwnRoleName().trim().isEmpty())) {
                return false;
            }
            roleForm.setRoleName(roleForm.getOwnRoleName().trim());
            return true;
        }
        if ("edit_existed".equals(roleForm.getFlagCreate())) {
            if ((roleForm.getRoleName()==null)||(roleForm.getRoleName().trim().isEmpty())) {
                return false;
            }
            roleForm.setRoleName(roleForm.getRoleName().trim());
            return true;
        }
        return false;
    }
}
