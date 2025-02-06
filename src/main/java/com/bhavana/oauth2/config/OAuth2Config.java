/*package com.bhavana.oauth2.config;

*//*
* If you’re using OAuth2 login, and Spring Boot is auto-configuring OAuth2
* (as long as spring-boot-starter-oauth2-client is included and configured correctly in application.yml),
* you don’t need to define OAuth2Config or manually define OAuth2AuthorizedClientService.
*
* *//*

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
public class OAuth2Config {

    // Use InMemoryOAuth2AuthorizedClientService for in-memory storage of authorized clients
    @Primary // Indicates that this is the default bean when multiple beans are available.
    @Bean(name = "customAuthorizedClientService")
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {

        // Create and return the OAuth2AuthorizedClientService using the in-memory map
        return new InMemoryOAuth2AuthorizedClientService(
            clientRegistrationRepository  // ClientRegistrationRepository
        );
    }
}*/
