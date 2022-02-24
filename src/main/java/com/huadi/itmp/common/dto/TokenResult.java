package com.huadi.itmp.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author meteor
 */
@NoArgsConstructor
@Data
public class TokenResult {

    public TokenResult(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    private String accessToken;
    private String refreshToken;

}
