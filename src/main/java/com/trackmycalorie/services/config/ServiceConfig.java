package com.trackmycalorie.services.config;

import com.trackmycalorie.dao.config.DaoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.trackmycalorie.services")
@Import(DaoConfig.class)
public class ServiceConfig {

}