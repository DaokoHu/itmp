package com.huadi.itmp.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author 胡学良
 * @date 2021-08-26 14:18
 **/
public class UUIDUtils {
    private static final Random rand = new Random();

    /**
     * 生成随机UUID
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().toLowerCase();
    }


    /**
     * 生成随机UUID
     * @return
     */
    public static String randomToken() {
        String uuid1 = randomUUID().replace("-", "");
        String uuid2 = randomUUID().replace("-", "");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < uuid1.length(); i++) {
            int random = rand.nextInt(2);
            builder.append(random == 0 ? Character.toUpperCase(uuid1.charAt(i)) : Character.toLowerCase(uuid1.charAt(i)));
        }
        for (int i = 0; i < uuid2.length(); i++) {
            int random = rand.nextInt(2);
            builder.append(random == 0 ? Character.toUpperCase(uuid2.charAt(i)) : Character.toLowerCase(uuid2.charAt(i)));
        }
        return builder.toString();
    }

    /**
     * 生成随机验证码
     * @return
     */
    public static String randomVerifyCode() {
        return String.format("%d%d%d%d%d%d", randomNumber(), randomNumber(), randomNumber(), randomNumber(), randomNumber(), randomNumber());
    }

    private static int randomNumber() {
        return rand.nextInt(9);
    }


}
