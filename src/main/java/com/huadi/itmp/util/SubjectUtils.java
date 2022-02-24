package com.huadi.itmp.util;

import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.constant.SysConstants;

/**
 * @author meteor
 */
public class SubjectUtils {
    public static Subject getSubject() {
        Subject subject = (Subject) ThreadLocalMap.get(SysConstants.HTTP_ATTRIBUTE_SUBJECT);
        if (null == subject) {
            return Subject.anonymous();
        }
        return subject;
    }

    public static boolean isAuthenticated() {
        Subject subject = (Subject) ThreadLocalMap.get(SysConstants.HTTP_ATTRIBUTE_SUBJECT);
        return null != subject && subject.isAuthenticated();
    }
}
