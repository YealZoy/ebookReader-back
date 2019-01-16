package com.bksx.ebookreader.util;




import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JWTToken {


    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    /**
     * 创建jwt
     * @return
     * @throws Exception
     */
    public String createJWT() throws Exception {
        Long EXPIRATION = 36000000L;// token 为一小时

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date(System.currentTimeMillis());
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置header
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(now)     //设置iat
                .claim("uuid", UUID.randomUUID().toString().replaceAll("-",""))   //设置payload的键值对
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .setIssuer("zyy")
                .signWith(key,signatureAlgorithm);    //签名，需要算法和key
        String jwt = builder.compact();
        System.out.println("生成的jwt:" + jwt);
        return jwt;
    }

    public boolean volidateToken(String token)throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        try {


            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            if(claims != null){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }


}
