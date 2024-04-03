package org.bigevent.validated;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.bigevent.anno.State;

public class StateValidated implements ConstraintValidator<State, String> {

    /**
     * 初始化方法
     *
     * @param s                          // 注解的属性值
     * @param constraintValidatorContext // 校验器上下文
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // TODO 实现自定义校验逻辑
        if (s == null) return false;
        return s.equals("已发布") || s.equals("草稿");
    }
}
