package com.ta.coremolde.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtConfig {
    @Value("${security.jwt.uri:/molde/api/v1/login}")
    private String uri;

    @Value("${tcc.security.secret:tccoursesecret}")
    private String secretKey;

    @Value("${tcc.security.expiration:#{24*60*60}}")
    private long expiration;

    @Value("${tcc.security.header:Authorization}")
    private String header;

    @Value("${tcc.security.prefix:Bearer }")
    private String prefix;
}
