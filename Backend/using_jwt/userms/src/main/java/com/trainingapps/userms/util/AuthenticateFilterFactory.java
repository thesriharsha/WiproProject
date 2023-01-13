package com.trainingapps.userms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateFilterFactory extends AbstractGatewayFilterFactory<AuthenticateFilter.Config> {

    public AuthenticateFilterFactory(){
        super(AuthenticateFilter.Config.class);
    }

    @Autowired
    private AuthenticateFilter filter;

    @Override
    public GatewayFilter apply(AuthenticateFilter.Config config) {
        return filter;
    }
}
