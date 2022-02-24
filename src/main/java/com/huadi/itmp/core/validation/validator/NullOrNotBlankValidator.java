package com.huadi.itmp.core.validation.validator;

import com.huadi.itmp.core.validation.constraints.NullOrNotBlank;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * NullOrNotBlank参数校验器
 * @author meteor
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (null == value) {
            return true;
        }

        if (!StringUtils.isEmpty(value.trim())) {
            return true;
        }
        return false;
    }
}
