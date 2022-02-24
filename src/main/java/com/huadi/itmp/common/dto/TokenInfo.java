package com.huadi.itmp.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meteor
 */
@NoArgsConstructor
@Data
public class TokenInfo {

    public TokenInfo(String token, String userId, Integer role) {
        this.token = token;
        this.userId = userId;
        this.role = role;
    }

    private String token;
    private String userId;
    private Integer clientType;
    private Integer role;

    public static TokenInfo create(String token, String userId, Integer role) {
        return new TokenInfo(token, userId, role);
    }
}
