package org.bigevent.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.bigevent.validated.StateValidated;

import java.lang.annotation.*;

@Documented // 元注解
@Target({ElementType.FIELD})    //元注解
@Retention(RetentionPolicy.RUNTIME)   //元注解
@Constraint(validatedBy = {StateValidated.class}) //自定义校验器

public @interface State {

    // 校验不通过时的提示信息
    String message() default "state参数只能是“草稿”或者“已发布”";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
