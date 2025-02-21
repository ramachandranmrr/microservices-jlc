package com.jlcindia.apigatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyLogFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(MyLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
        logger.info("---1. MyLogFilter---run()");

        // Logging the incoming request URI
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        logger.info("Request From => {}", uri);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1; // Order of execution
    }
    
}

