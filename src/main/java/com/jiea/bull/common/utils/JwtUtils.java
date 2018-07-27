package com.jiea.bull.common.utils;

import com.jiea.bull.common.exception.BullException;
import io.jsonwebtoken.*;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JwtUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

    private static final String secret = "sdfsfssw323sgvdf00934234";

    /**
     * 过期时间（毫秒）
     */
    private static final int expire = 30 * 60 * 1000;

    /**
     * 生成 jwt token
     *
     * @param userId
     * @return
     */
    public static String generateToken(Long userId) {
        return Jwts.builder().setHeaderParam("alg", "HS384")
                .setHeaderParam("tye", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS384, secret)
                .compact();
    }

    /**
     * 校验 jwt token
     *
     * @param jwt
     * @return 1: 成功, 2: 过期, 3: 错误
     */
    public static void validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            throw new BullException("token invalid");
        } catch (Exception e) {
            throw new BullException("token error");
        }
    }

    public static Long getSubject(String jwt){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt).getBody();
            return Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException e) {
            throw new BullException("token invalid");
        } catch (Exception e) {
            throw new BullException("token error");
        }
    }

    public static void main(String[] args) {
        String jwt = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTMyNTE4NDY2LCJleHAiOjE1MzI1MjAyNjZ9.MgJPiCaSLTRl2XErADkpxyofgGB6rNqRPNv8X5IPYXH1mqSDL2NBzgixl9lC-YS3";
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt).getBody();
            System.out.println(claims.getSubject());
            System.out.println(claims.getExpiration());
        } catch (ExpiredJwtException e) {
            LOG.info("jwt: {}, jwt-token 过期", jwt);
        } catch (Exception e) {
            LOG.info("jwt: {}, jwt-token 出错", jwt);
        }


    }
}
