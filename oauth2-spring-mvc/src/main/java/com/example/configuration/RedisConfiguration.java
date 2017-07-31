package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.io.IOException;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/31 10:25
 * Description:
 */
@Configuration
public class RedisConfiguration {

    @Bean(name = "curAppId")
    public String getAppId() {
        return "mainAppId";
    }


    @Bean(name = "mcdRedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() throws IOException {


        String ip ="";
        int port =1234;
        int dbIndex = 0;
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setDatabase(dbIndex);
        connectionFactory.setHostName(ip);
        connectionFactory.setPort(port);
        connectionFactory.setUsePool(true);
        connectionFactory.setTimeout(1000 * 60 * 30);
        return connectionFactory;

    }
}
