package com.bc.consumer.service;

import com.bc.consumer.exception.ConsumerException;

public interface HelloService {

    String getPort(String name) throws ConsumerException;
    String getPort2(String name) throws ConsumerException;
}
