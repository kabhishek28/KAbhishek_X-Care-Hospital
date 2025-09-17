package com.xworkz.kabhishek_xcare_hospital.configuration;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Property;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.xworkz.kabhishek_xcare_hospital")
@EnableWebMvc
@Slf4j
public class HospitalConfiguration implements WebMvcConfigurer {
    public HospitalConfiguration(){
        log.info("HospitalConfiguration................");
    }

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver (){
//        return new InternalResourceViewResolver("/",".jsp");
//    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/",".jsp");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.xworkz.kabhishek_xcare_hospital.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(properties());
        return factoryBean;
    }



    @Bean
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
       // properties.setProperty("hibernate.hbm2ddl.auto", "update");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
//        properties.setProperty("hibernate.cache.use_second_level_cache", "false");
//        properties.setProperty("hibernate.cache.use_query_cache", "false");
//        properties.setProperty("hibernate.jdbc.batch_size", "50");
        return properties;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/x_care_hospital");
        dataSource.setUsername("root");
        dataSource.setPassword("Abhi@2003");
        return dataSource;
    }

    @Bean("multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver(){
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(1048576);
        multipartResolver.setMaxUploadSize(1048576);
        return multipartResolver;
    }


}
