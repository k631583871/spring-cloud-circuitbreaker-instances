## Introduction



An extension of spring cloud gateway circuit breaker realizes the fusing of a single instance without affecting other same applications



## Spring cloud gateway circuit breaker difference



-Spring cloud gateway circuit breaker will use the same fuse for the same args.name. As long as there is a problem with one instance, the service under the fuse cannot be accessed.

**In particular, when the "loadbalancerclientfilter" is used for load balancing, configure the fuse. When an instance under load balancing has a problem, triggering the fuse will cause other instances to be inaccessible. * *



-The instance circuit breaker implements separate fuse calculation for each instance of specific access. When there is a problem in one instance of the same application, the access of other instances will not be affected.
## Explanation of terms
In order to prevent conceptual conflicts, special interpretation
-Same application: indicates an application with the same name in the registration center
-Instance: each startup of the application is an instance, and different ports of the same IP are different instances


## 使用方法

-   Add spring-cloud-circuitbreaker-instances dependency
    -   Maven:
        ```xml
        <dependency>
            <groupId>com.manymobi</groupId>
            <artifactId>spring-cloud-circuitbreaker-instances</artifactId>
            <version>1.0</version>
        </dependency>
        ```
    -   Gradle
        ```groovy
        compile group: 'com.manymobi', name: 'spring-cloud-circuitbreaker-instances', version: '1.0'
        ```
- Example application.yml
    ``` 
    spring:
      cloud:
        gateway:
          routes:
          - id: instancescircuitbreaker_route
            uri: https://example.org
            filters:
            - InstancesCircuitBreaker=myCircuitBreaker
    ```

- See [official documents](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html/#spring-cloud-circuitbreaker-filter-factory) for other ways of use Just replace filters.name "circuitbreaker" with "instancescircuitbreaker"
