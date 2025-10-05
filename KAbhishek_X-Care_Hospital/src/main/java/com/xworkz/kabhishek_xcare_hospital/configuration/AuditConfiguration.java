package com.xworkz.kabhishek_xcare_hospital.configuration;

import com.xworkz.kabhishek_xcare_hospital.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.servlet.http.HttpSession;

@Configuration
@ComponentScan(basePackages = "com.xworkz.kabhishek_xcare_hospital")
@EnableJpaAuditing
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware(HttpSession session){
        return new AuditorAwareImpl(session);
    }

}
