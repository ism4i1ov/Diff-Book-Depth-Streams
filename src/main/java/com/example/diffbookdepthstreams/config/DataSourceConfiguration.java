package com.example.diffbookdepthstreams.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.diffbookdepthstreams.repository",
        entityManagerFactoryRef = "mysql-em",
        transactionManagerRef = "mysql-tm")
public class DataSourceConfiguration {

    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;
    @Value("${datasource.hostname}")
    private String hostname;
    @Value("${datasource.driverClassName}")
    private String driverClassName;
    @Value("${datasource.entityPackage}")
    private String entityPackage;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .url(hostname)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean(name = "mysql-em")
    public LocalContainerEntityManagerFactoryBean entityManager() {
        var managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        managerFactoryBean.setDataSource(dataSource());
        managerFactoryBean.setPackagesToScan(entityPackage);
        return configProperties(managerFactoryBean);
    }

    public LocalContainerEntityManagerFactoryBean configProperties(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        var properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        entityManagerFactoryBean.setJpaPropertyMap(properties);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        return entityManagerFactoryBean;
    }

    @Bean(name = "mysql-tm")
    public PlatformTransactionManager transactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }
}
