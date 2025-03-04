package com.marubi.security.jwt;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;

public class Main {
    public static void main(String[] args) {
// 密钥
        byte[] key = "1234567890".getBytes();
     /*   byte[] key = "1234567890".getBytes();

        String token = JWT.create()
                .setPayload("sub", "1234567890")
                .setPayload("name", "looly")
                .setPayload("admin", true)
                .setKey(key)
                .sign();
        System.out.println(token);*/
     String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwiYWRtaW4iOnRydWUsIm5hbWUiOiJsb29seSJ9.U2aQkC2THYV9L0fTN-yBBI7gmo5xhmvMhATtu8v0zEA";
        JWT jwt = JWT.of(token).setKey(key);
        System.out.println(jwt.verify());
// JWT
        System.out.println(jwt.getHeader(JWTHeader.TYPE));
// HS256
        System.out.println(jwt.getHeader(JWTHeader.ALGORITHM));

// 1234567890
        System.out.println(jwt.getPayload("sub"));
// looly
        System.out.println(jwt.getPayload("name"));
// true
        System.out.println(jwt.getPayload("admin"));
    }
}
