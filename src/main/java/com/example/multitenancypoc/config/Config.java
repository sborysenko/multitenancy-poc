package com.example.multitenancypoc.config;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class Config {
    @Bean(name = "tenantSessionFactory")
    public LocalSessionFactoryBean tenantSessionFactory(
            DataSource dataSource,
            @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
            @Value("${spring.jpa.properties.hibernate.dialect}") String dialect) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        hibernateProperties.setProperty("hibernate.dialect", dialect);

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.multitenancypoc.multitenancy.model");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean(name = "tenantTransactionManager")
    public PlatformTransactionManager tenantTransactionManager(@Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(
            DataSource dataSource,
            SchemaBasedMultiTenantConnectionProvider schemaBasedMultiTenantConnectionProvider,
            CurrentTenantIdentifierResolver currentTenantIdentifierResolver,
            @Value("${spring.jpa.hibernate.ddl-auto}") String ddlAuto,
            @Value("${spring.jpa.properties.hibernate.dialect}") String dialect) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA.name());

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.multitenancypoc.multitenancy.model");
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.setMultiTenantConnectionProvider(schemaBasedMultiTenantConnectionProvider);
        sessionFactory.setCurrentTenantIdentifierResolver(currentTenantIdentifierResolver);

        return sessionFactory;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
