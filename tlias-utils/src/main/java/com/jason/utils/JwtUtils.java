package com.jason.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@SuppressWarnings("UnnecessaryLocalVariable")
public class JwtUtils {
    private static final String SECRET = "S4AN9IsSRUC0c4yF2XbV8a14vawnKrD3TFp1shY9k7yrR";
    private static final long expire = 1000 * 60 * 60 * 12L;//12h
    //创建一个jwt密钥
    private static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return jwt
     */
    public static String generateJwt(Map<String, Object> claims) {
        Date now = new Date();
        // 生成token
        String jwt = Jwts.builder()
                .header().add("JWT", "JSpWdhuPGblNZApVclmX").and()
                .id("rQRk$yN:7%*Bw}A_A-]M~4#;yGa:a_F{") //id 这个可以不填，但是建议填
                .issuer("Jason") //签发者
                .claims(claims) //数据
                .subject((String) claims.get("username")) //主题
                .issuedAt(now) //签发时间
                .expiration(new Date(now.getTime() + expire)) //过期时间
                .signWith(key) //签名方式
                .compact();

        System.out.println(jwt);
        return jwt;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        return claims;
    }
}
