package by.kozik.quest.bean.validation;

import by.kozik.quest.bean.RoleBeanFromForm;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Serge on 16.02.2017.
 */
@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleEditStrategyValidator.class)
@Documented
public @interface FlagCreateRoleMatches {
    String message() default "Role name is not specified correctly";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
