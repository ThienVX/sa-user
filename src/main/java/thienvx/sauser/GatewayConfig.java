package thienvx.sauser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        var locator = builder.routes()
                .route(p -> p
                        .path("/health")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8080"))
                .route(p -> p
                        .path("/user/*")
                        .filters(f -> f.filter(filter))
                        .uri("http://localhost:8080"))
                .build();

        return locator;
    }
}
