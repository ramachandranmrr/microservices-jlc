package com.jlcindia.apigatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MySecurityFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(MySecurityFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        logger.info("---2. MySecurityFilter---run()");
        logger.info("Authentication Done Successfully");

        // Continue with the request
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2; // Executed after MyLogFilter
    }
    
}

