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
            @Value("${rpg.members.url}") String membersUrl,
            @Value("${rpg.political-parties.url}") String partiesUrl,
            @Value("${rpg.gateway.host}") String host
    ) {
        return builder
                .routes()
                .route("political-parties", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/political-parties/{id}",
                                "/political-parties"
                        )
                        .uri(partiesUrl)
                )
                .route("members", route -> route
                        .host(host)
                        .and()
                        .path(
                                "/political-parties/{party_id}/members",
                                "/political-parties/{party_id}/members/{id}",
                                "/members"
                        )
                        .uri(membersUrl)
                )
                .build();

    }
}
