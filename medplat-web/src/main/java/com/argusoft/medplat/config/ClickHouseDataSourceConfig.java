package com.argusoft.medplat.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;

@Configuration
@PropertySource(value = "classpath:clickhouse.properties", ignoreResourceNotFound = false)
@EnableTransactionManagement
public class ClickHouseDataSourceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${spring.clickhouse.datasource.url}")
    private String url;

    @Value("${spring.clickhouse.datasource.username}")
    private String username;

    @Value("${spring.clickhouse.datasource.password}")
    private String password;

    @Value("${spring.clickhouse.datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "clickhouseDataSource")
    public DataSource clickhouseDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

}