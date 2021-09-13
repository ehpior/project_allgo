package com.jhk.allgo.gateway.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import lombok.Data;
import reactor.core.publisher.Mono;

@Component
public class PortfolioFilter extends AbstractGatewayFilterFactory<PortfolioFilter.Config> {
	
	private static final Logger logger = LogManager.getLogger(PortfolioFilter.class);
    public PortfolioFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            logger.info("PortfolioFilter baseMessage>>>>>>" + config.baseMessage);
            if (config.preLogger) {
                logger.info("PortfolioFilter Start>>>>>>" + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if (config.postLogger) {
                    logger.info("PortfolioFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
