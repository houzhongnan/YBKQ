package com.kqxt.springboot.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class jwtUtils {
    /**
     * 签名秘钥(唯一秘钥，可以用密码做为秘钥)
     */
    public static final String SECRET="admin";

    /**
     * 生成token
     * @param a_password
     * @return
     * */
   public static final String createJwtToken(String a_password){
       String issuer="tdoor";
       String subject="kqxt";
       long ttlMillis=36000000;
       return createJwtToken(a_password,issuer,subject,ttlMillis);
   }


    /**
     * 签发JWT
     * @param a_password
     * @param subject 可以是JSON数据 尽可能少
     * @param ttlMillis
     * @return  String
     *
     */
    private static String createJwtToken(String a_password, String issuer, String subject, long ttlMillis) {
        //签名算法，将token进行签名
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //生成签发时间
        long nowMills=System.currentTimeMillis();
        Date now=new Date(nowMills);
        //通过秘钥签名JWT
        byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey=new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //创建token
        JwtBuilder builder= Jwts.builder().setId(a_password)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,signingKey);
        //添加过期时间
        if(ttlMillis>=0){
            long expMillis=nowMills+ttlMillis;
            Date exp=new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    //验证和读取JWT的示例方法
    public static Claims parseJWT(String jwt){
        Claims claims=Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
    public static void main(String[] args){
        System.out.println(jwtUtils.createJwtToken("2822871075@qq.com"));
    }

}
