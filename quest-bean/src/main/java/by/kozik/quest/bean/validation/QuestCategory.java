package by.kozik.quest.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Serge_Kozik on 3/3/2017.
 */

@Target({ElementType.TYPE,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = by.kozik.quest.bean.validation.QuestCategoryValidator.class)
@Documented
public @interface QuestCategory {
    String message() default "Category is not defined.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
