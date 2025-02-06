Spring WebFlux OAuth2 Client

This project is a Spring WebFlux-based OAuth2 client that allows users to log in using GitHub OAuth2 authentication. It leverages Spring Security for authentication and authorization, using reactive components compatible with WebFlux.

📌 Features

Implements OAuth2 login with GitHub.

Uses Spring WebFlux for non-blocking, reactive processing.

Retrieves OAuth2 access tokens for authenticated users.

Secures API endpoints using Spring Security.

🛠 Tech Stack

Spring Boot 2.x+

Spring WebFlux (reactive alternative to Spring MVC)

Spring Security OAuth2 Client

GitHub OAuth2 Provider

Java 8

🚀 Getting Started

1️⃣ Prerequisites

Ensure you have the following installed:

Java 11+

Maven

A GitHub OAuth2 Client ID & Client Secret

2️⃣ Clone the Repository

 git clone https://github.com/Bhavana13/oauth2
 cd your-repo

3️⃣ Configure OAuth2 in application.yml

Replace YOUR_GITHUB_CLIENT_ID and YOUR_GITHUB_CLIENT_SECRET with your GitHub OAuth2 credentials.

spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: YOUR_GITHUB_CLIENT_ID
            client-secret: YOUR_GITHUB_CLIENT_SECRET
            scope: read:user, user:email
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/github"

4️⃣ Build and Run the Application

 mvn clean spring-boot:run

5️⃣ Access the Application

Open a browser and visit:

 http://localhost:8080/token

When accessing a protected route, you will be redirected to GitHub for login.

🔑 Security Configuration (SecurityConfig.java)

Since this is a Spring WebFlux application, security is configured using ServerHttpSecurity instead of HttpSecurity.

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange()
                .pathMatchers("/login", "/oauth2/**").permitAll()  // Public endpoints
                .anyExchange().authenticated()  // Secure all other endpoints
            .and()
            .oauth2Login(); // Enable OAuth2 login

        return http.build();
    }
}

📜 Controller (AuthController.java)

The AuthController fetches the OAuth2 access token for the authenticated user.

@RestController
@RequestMapping("/token")
public class AuthController {

    private final ReactiveOAuth2AuthorizedClientService authorizedClientService;

    public AuthController(ReactiveOAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping
    public Mono<String> getUser(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;

            return authorizedClientService.loadAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName()
                )
                .map(authorizedClient -> "Access Token: " + authorizedClient.getAccessToken().getTokenValue())
                .defaultIfEmpty("No authorized client found");
        }
        return Mono.just("Not authenticated");
    }
}

📦 Dependencies (pom.xml)

Ensure the project has the correct dependencies for Spring WebFlux and OAuth2 Client.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-client</artifactId>
</dependency>

🛠 Troubleshooting

Issue: OAuth2AuthorizedClientService Bean Not Found

✅ Solution: WebFlux uses ReactiveOAuth2AuthorizedClientService. Ensure you use it instead of OAuth2AuthorizedClientService.

Issue: 403 Forbidden After Login

✅ Solution: Ensure your SecurityConfig allows access to necessary routes (/login, /oauth2/**).

Issue: Redirect Loop to OAuth Provider

✅ Solution: Double-check your GitHub OAuth2 Client ID, Secret, and Redirect URI.


🎯 Conclusion

This project demonstrates a Spring WebFlux OAuth2 client that uses GitHub authentication. It showcases reactive security, OAuth2 login, and secure API access. 🚀

Happy coding! 😊
