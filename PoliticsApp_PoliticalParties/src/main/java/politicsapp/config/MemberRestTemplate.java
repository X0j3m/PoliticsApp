package politicsapp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MemberRestTemplate {
    @Bean
    @Qualifier("member")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8081")
                .build();
    }
}
