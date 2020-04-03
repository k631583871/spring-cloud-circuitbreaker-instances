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

package com.manymobi.cloud.gateway.config;

import com.manymobi.cloud.gateway.filter.SpringCloudInstancesCircuitBreakerFilter;
import com.manymobi.cloud.gateway.filter.factory.SpringCloudInstancesCircuitBreakerFilterFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liang jianjun
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "spring.cloud.gateway.enabled", matchIfMissing = true)
public class GatewayInstancesCircuitBreakerAutoConfiguration {

    @Bean
    public SpringCloudInstancesCircuitBreakerFilter springCloudInstancesCircuitBreakerFilter(
            SpringCloudCircuitBreakerFilterFactory springCloudCircuitBreakerFilterFactory) {
        return new SpringCloudInstancesCircuitBreakerFilter(springCloudCircuitBreakerFilterFactory);
    }

    @Bean
    @ConditionalOnMissingBean(SpringCloudInstancesCircuitBreakerFilterFactory.class)
    public SpringCloudInstancesCircuitBreakerFilterFactory springCloudInstancesCircuitBreakerFilterFactory() {
        return new SpringCloudInstancesCircuitBreakerFilterFactory();
    }

}
