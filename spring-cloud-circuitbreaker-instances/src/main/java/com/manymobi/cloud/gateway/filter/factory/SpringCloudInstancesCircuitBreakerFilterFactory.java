/*
 * Copyright 2013-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manymobi.cloud.gateway.filter.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author liang jianjun
 */
public class SpringCloudInstancesCircuitBreakerFilterFactory
        extends AbstractGatewayFilterFactory<SpringCloudCircuitBreakerFilterFactory.Config> {


    public static final String CIRCUIT_BREAKER_CONFIG = "instancesCircuitBreakerConfig";

    public SpringCloudInstancesCircuitBreakerFilterFactory() {
        super(SpringCloudCircuitBreakerFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return singletonList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(SpringCloudCircuitBreakerFilterFactory.Config config) {
        return (exchange, chain) -> {
            SpringCloudCircuitBreakerFilterFactory.Config configTemp = new SpringCloudCircuitBreakerFilterFactory.Config();
            configTemp.setFallbackUri(config.getFallbackUri());
            configTemp.setName(config.getName());
            configTemp.setRouteId(config.getRouteId());
            exchange.getAttributes().put(CIRCUIT_BREAKER_CONFIG, configTemp);
            return chain.filter(exchange);
        };
    }

    @Override
    public String name() {
        return "InstancesCircuitBreaker";
    }
}
