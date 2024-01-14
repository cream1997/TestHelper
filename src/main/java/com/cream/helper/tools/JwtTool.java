package com.cream.helper.tools;

import com.cream.helper.utils.NullUtil;
import com.cream.helper.utils.Times;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTool {
    /**
     * 签名加密的key
     */
    private final String TokeSignKey = "ComplexKeys";
    /**
     * 过期时间
     */
    private final long TokeExpiration = Times.ONE_DAY * 30;

    private final String AccountIdKey = "accountId";
    private final String AccountNameKey = "accountName";

    /**
     * JWT的token由三部分组成：头、载荷、签名hash
     */
    public String createToken(long accountId, String accountName) {
        return Jwts.builder()
                // 设置头
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // 设置载荷：自定义信息
                .claim(AccountIdKey, accountId)
                .claim(AccountNameKey, accountName)
                // 载荷：默认信息
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(Times.now() + TokeExpiration)) //设置过期时间
                // 设置签名hash
                .signWith(SignatureAlgorithm.HS256, TokeSignKey)
                .compact();
    }

    public long getAccountId(String token) {
        Claims claims = getClaims(token);
        return (Long) claims.get(AccountIdKey);
    }

    public boolean tokenInvalid(String token) {
        JwtParser parser = Jwts.parser();
        try {
            parser.setSigningKey(TokeSignKey)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    private Claims getClaims(String token) {
        if (NullUtil.isBlank(token)) {
            throw new RuntimeException("token无效");
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(TokeSignKey)
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (Exception e) {
            throw new RuntimeException("token解析错误");
        }
    }
}
