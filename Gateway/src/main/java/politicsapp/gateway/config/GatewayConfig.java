package politicsapp.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(
            RouteLocatorBuilder builder,
            @Value("${politicsapp.members.url}") String membersUrl,
            @Value("${politicsapp.political-parties.url}") String partiesUrl
    ) {
        return builder.routes()
                .route("members-route", r -> r
                        .path("/api/political-parties/*/members/**", "/api/members/**")
                        .uri(membersUrl)
                )
                .route("parties-route", r -> r
                        .path("/api/political-parties/**")
                        .uri(partiesUrl)
                )
                .build();
    }
}