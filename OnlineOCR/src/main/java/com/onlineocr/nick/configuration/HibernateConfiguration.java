package com.onlineocr.nick.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by GrIfOn on 10.01.2017.
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:OCR.properties" })
@ComponentScan({ "com.onlineocr.nick" })
public class HibernateConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        try {
            sessionFactory.setDataSource(restDataSource());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        sessionFactory.setPackagesToScan(new String[] { "com.onlineocr.nick.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource ();
        dataSource.setDriverClass(env.getProperty("ocr.jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("ocr.jdbc.url"));
        dataSource.setUser(env.getProperty("ocr.jdbc.username"));
        dataSource.setPassword(env.getProperty("ocr.jdbc.password"));
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setMaxPoolSize(100);
        dataSource.setMaxStatements(50);
        dataSource.setMinPoolSize(10);

        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.dialect", env.getProperty("ocr.hibernate.dialect"));
                setProperty("hibernate.globally_quoted_identifiers", "false");
                setProperty("hibernate.enable_lazy_load_no_trans", "true");
                setProperty("hibernate.show_sql", "true");
            }
        };
    }
}