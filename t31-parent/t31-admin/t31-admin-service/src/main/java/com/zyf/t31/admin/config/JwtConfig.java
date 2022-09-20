package com.zyf.t31.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

@Configuration
public class JwtConfig {

    public static final String PUBLIC_CERT = "public.key";
    public static final String SIGNING_KEY = "kaikeba";

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource(PUBLIC_CERT);

        String publicKey;
        try{
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        //设置校验公钥
        converter.setVerifierKey(publicKey);
        //设置校验密码证书
        converter.setSigningKey(SIGNING_KEY);

        return converter;
    }
}
