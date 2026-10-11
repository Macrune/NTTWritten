package com.example.cekalamat.config;

import org.springframework.boot.autoconfigure.web.client.RestClientSsl;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CertumTrustConfig {

    @Bean
    RestClientCustomizer certumTrust(RestClientSsl ssl) {
        return builder -> builder.apply(ssl.fromBundle("certum"));
    }
}
