package com.qdbgame.newsplatform.tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/12 9:42
 * 来源：https://www.cnblogs.com/cjsblog/p/12425912.html
 */
public class JWTUtil {
    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;
    private static final String ISSUER = "QDB";

    /**
     * 生成Token
     *
     * @param userId
     * @param secretKey
     * @return
     */
    public static String generateToken(Integer userId, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim("userId", userId)
                .sign(algorithm);

        return token;
    }

    /**
     * 校验Token
     *
     * @param token
     * @param secretKey
     * @return
     */
    public static void verifyToken(String token, String secretKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            jwtVerifier.verify(token);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 从Token中提取用户信息
     *
     * @param token
     * @return
     */
    public static String getUserInfo(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        String userId = decodedJWT.getClaim("userId").asString();
        return userId;
    }
}
