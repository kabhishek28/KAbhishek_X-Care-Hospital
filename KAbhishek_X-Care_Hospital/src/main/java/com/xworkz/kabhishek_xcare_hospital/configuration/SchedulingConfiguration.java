package com.xworkz.kabhishek_xcare_hospital.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(basePackages = "com.xworkz.kabhishek_xcare_hospital")
@EnableScheduling
public class SchedulingConfiguration {


}
