package com.huadi.itmp.core.enums;

/**
 * 系统访问者类型
 * @author DAOKO
 */
public enum SubjectType {

    /**
     * 匿名用户
     */
    ANONYMOUS(0),

    /**
     * 教学管理主管
     */
    TEACHING_MANAGEMENT_SUPERVISOR(1),

    /**
     * 专业教研室负责人
     */
    TEACHING_AND_RESEARCH_SECTION_PRINCIPAL(2),

    /**
     * 实施工程师
     */
    IMPLEMENTATION_ENGINEER(3),

    /**
     * 市场人员
     */
    MARKETING_PRINCIPAL(4),

    /**
     * 就业辅导专员
     */
    CAREER_GUIDANCE_PRINCIPAL(5),

    /**
     * 学生辅导专员
     */
    STUDENT_MANAGEMENT_PRINCIPAL(6),

    /**
     * 学生
     */
    STUDENT(7);

    private Integer type;

    SubjectType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static SubjectType valueOf(Integer type) {
        for (SubjectType subjectType : values()) {
            if (subjectType.getType().equals(type)) {
                return subjectType;
            }
        }
        return STUDENT;
    }
}
