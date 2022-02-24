package com.huadi.itmp.core.validation.constraints;

import com.huadi.itmp.core.validation.validator.ElementNotBlankValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 被验证的参数集合的元素不能为空
 * @author meteor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Documented
@Constraint(validatedBy = ElementNotBlankValidator.class)
public @interface ElementNotBlank {

    String message() default "element cannot blank";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ElementNotBlank[] value();
    }
}
