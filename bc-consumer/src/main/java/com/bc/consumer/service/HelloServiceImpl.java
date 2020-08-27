package com.bc.consumer.service;

import com.bc.consumer.exception.BusinessException;
import com.bc.consumer.exception.ConsumerException;
import com.bc.consumer.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalTime;

@Service("helloService")
public class HelloServiceImpl implements HelloService {


    @Override
    public String getPort(String name) throws ConsumerException {

        System.out.println(LocalTime.now() + "----getPort----");
        if ("a".equals(name)) {
            return "正常";
        } else if ("b".equals(name)) {
            throw new BusinessException("10001", "业务异常");
        }

        throw new SystemException("10002", "系统异常");

    }

    // 需要开启 @EnableRetry
    @Override
    @Retryable(value = SystemException.class, maxAttempts = 4, backoff = @Backoff(delay = 2000, multiplier = 2))
    public String getPort2(String name) throws ConsumerException {
        System.out.println(LocalTime.now() + "----getPort----"+name);
        if ("a".equals(name)) {
            return "正常";
        } else if ("b".equals(name)) {
            throw new BusinessException("10001", "业务异常");
        }

        throw new SystemException("10002", "系统异常");
    }

}
