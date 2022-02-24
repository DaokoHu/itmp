package com.huadi.itmp.core.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huadi.itmp.core.constant.SysConstants;
import com.huadi.itmp.core.enums.SubjectType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 对本系统访问者的抽象
 * @author meteor
 */
@Data
@NoArgsConstructor
public class Subject implements Serializable {

    /**
     * 访问者唯一标识
     */
    private Serializable id;

    /**
     * 访问者IP地址
     */
    private String ipAddress;

    /**
     * 访问者类型
     */
    private SubjectType type;

    private Subject(Serializable id, SubjectType type, String ipAddress) {
        this.id = id;
        this.type = type;
        this.ipAddress = ipAddress;
    }


    public static Subject anonymous(String ipAddress) {
        return new Subject(null, SubjectType.ANONYMOUS, ipAddress);
    }

    public static Subject anonymous() {
        return new Subject(SysConstants.SYS_ID, SubjectType.ANONYMOUS, SysConstants.SYS_ID);
    }

    public static Subject authenticated(Serializable id, String ipAddress, Integer role) {
        return new Subject(id, SubjectType.valueOf(role), ipAddress);
    }

    /**
     * 判断访问者是否经过认证
     * @return
     */
    @JsonIgnore
    public boolean isAuthenticated() {
        return !type.equals(SubjectType.ANONYMOUS);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Subject(");
        if (isAuthenticated()) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        builder.append("ipAddress=");
        builder.append(ipAddress);
        builder.append(", ");
        builder.append("type=");
        builder.append(type.name());
        builder.append(")");
        return builder.toString();
    }

    @JsonIgnore
    public String getIdentification() {
        StringBuilder builder = new StringBuilder();
        builder.append("Subject:");
        if (isAuthenticated()) {
            builder.append(id);
            builder.append(":");
        }
        builder.append(ipAddress);
        builder.append(":");
        builder.append(type.name());
        return builder.toString();
    }
}
