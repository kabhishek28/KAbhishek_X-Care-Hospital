package com.xworkz.kabhishek_xcare_hospital.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Slf4j
public class HospitalConfigurationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public HospitalConfigurationInitializer(){
        log.info("HospitalConfigurationInitializer.....................");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                HospitalConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
