package com.bc.consumer.configuration;

import com.bc.consumer.exception.SystemException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yds
 * @date: 2020/08/26
 */

/**
 * spring-retry 只针对异常会重试，不是根据某个变量value
 */
@Configuration
public class RetryConfiguration {

    @Bean
    public RetryTemplate retryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy());
        retryTemplate.setBackOffPolicy(backOffPolicy());
        return retryTemplate;
    }

    @Bean
    public RetryPolicy retryPolicy(){
        // 简单重试策略，针对所有异常
//        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
//        simpleRetryPolicy.setMaxAttempts(5);
//        return simpleRetryPolicy;

        // 根据不同异常设置不同重试策略
        ExceptionClassifierRetryPolicy exceptionClassifierRetryPolicy = new ExceptionClassifierRetryPolicy();
        Map<Class<? extends Throwable>, RetryPolicy> policyMap = new HashMap<>();
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(5);
        // 只有SystemException 才重试，且使用简单重试策略
        policyMap.put(SystemException.class, simpleRetryPolicy);
        exceptionClassifierRetryPolicy.setPolicyMap(policyMap);
        return exceptionClassifierRetryPolicy;
    }

    @Bean
    public BackOffPolicy backOffPolicy(){
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(2000);
        exponentialBackOffPolicy.setMultiplier(2);
        exponentialBackOffPolicy.setMaxInterval(60000);
        return exponentialBackOffPolicy;
    }
}
