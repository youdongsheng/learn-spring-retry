package com.bc.consumer.web;

import com.bc.consumer.exception.ConsumerException;
import com.bc.consumer.exception.SystemException;
import com.bc.consumer.service.HelloService;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalTime;

@RestController
@RequestMapping("/consumer")
public class HelloController {

    @Resource
    HelloService helloService;

    @Resource
    private RetryTemplate retryTemplate;

    @GetMapping("/getPort/{name}")
    public String getPort(@PathVariable String name) {
        String result = null;
        try {
            result = retryTemplate.execute(new RetryCallback<String, ConsumerException>() {
                                               @Override
                                               public String doWithRetry(RetryContext context) throws ConsumerException {
                                                   return helloService.getPort(name);
                                               }
                                           }, new RecoveryCallback<String>() {
                                               @Override
                                               public String recover(RetryContext context) throws Exception {
                                                   System.out.println("结束了--"+name+"--"+ LocalTime.now());
                                                   return "结束了";
                                               }
                                           }
            );
        } catch (ConsumerException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }

    @GetMapping("/getPort2/{name}")
    public String getPort2(@PathVariable String name) {
        String result = null;
        try {
            result = helloService.getPort2(name);
        } catch (ConsumerException e) {
            e.printStackTrace();
            result = e.getMessage();
        }
        return result;
    }
}
