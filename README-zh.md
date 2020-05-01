## 介绍

对Spring-Cloud-Gateway CircuitBreaker 的一个扩展，实现了对单个实例的熔断，不影响其他的同一应用

## Spring-Cloud-Gateway CircuitBreaker 区别

- Spring-Cloud-Gateway CircuitBreaker 会对 相同的args.name 使用同一熔断器，只要其中一个实例出现问题，整个熔断器下服务全都无法访问。
**特别是当使用的“LoadBalancerClientFilter” 进行负载均衡的时候， 配置熔断器，当负载均衡下边的一个实例出现问题，触发熔断，就会造成其他实例也无法访问。**

- InstancesCircuitBreaker 实现的对具体访问的每一个实例进行单独熔断计算，当同一应用中的其中一个实例出现问题，不会影响其他实例的访问。


## 名词解释

    为了防止概念冲突，特别解释
- 同一应用：表示在注册中心相同名称的应用

- 实例：应用的每一启动都是一个实例，相同的IP 不同的端口 是不同的实例


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
- 例子 application.yml
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
- 其他使用方式可看[官方文档](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.2.RELEASE/reference/html/#spring-cloud-circuitbreaker-filter-factory)
只需要把 filters.name  “CircuitBreaker” 换成 “InstancesCircuitBreaker” 即可
