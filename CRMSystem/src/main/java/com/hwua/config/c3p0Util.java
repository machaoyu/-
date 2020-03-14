package com.hwua.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class c3p0Util {
    @Bean
    @ConfigurationProperties(prefix = "jdbc.datasource")
    public DataSource c3p0dataSource(){
        return new ComboPooledDataSource();
    }
}
