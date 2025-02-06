package com.bhavana.oauth2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final ReactiveOAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/user/details")
    public Authentication getUserDetails(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/token")
    public Mono<String> getToken(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

            return authorizedClientService.loadAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName()
                )
                .map(authorizedClient -> "Access Token: " +
                        authorizedClient.getAccessToken().getTokenValue())
                .defaultIfEmpty("No authorized client found");
        }

        return Mono.just("Not authenticated");
    }
}
