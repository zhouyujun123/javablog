package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author mintaoyu
 * Date on 2020-03-12  10:23
 */
@Slf4j
public class JwtUtil {
    /**
     * TODO 正式上线更换为15分钟
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000L;
//    private static final long EXPIRE_TIME = 1000L;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名,15分钟后过期
     *
     * @param userId 用户Id
     * @return
     */
    public static String sign(String userId,String role) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header)
                .withClaim("userId", userId).withClaim("role", role).withExpiresAt(date).sign(algorithm);
    }


    public static DecodedJWT verity(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {

    }

}
