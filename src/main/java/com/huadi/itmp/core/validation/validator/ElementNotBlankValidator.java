package com.huadi.itmp.core.validation.validator;

import com.huadi.itmp.core.validation.constraints.ElementNotBlank;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * ElementNotBlank参数校验器
 * @author meteor
 */
public class ElementNotBlankValidator implements ConstraintValidator<ElementNotBlank, Collection<String>> {

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        if (null == value) {
            return true;
        }
        for (String str : value) {
            if (StringUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }
}
