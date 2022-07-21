package eu.codeacademy.vteshop.api.product.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OperationStationValidator.class)
public @interface OperationStationValid {
    String message() default "{validate.operationStation.notChosen}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
