/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miumg.wstickets.config;

/**
 *
 * @author Steve Ortiz
 */
import com.zaxxer.hikari.HikariDataSource;
import com.miumg.wstickets.config.filters.CORSFilter;
import com.miumg.wstickets.config.util.Constant;
import java.util.Properties;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.Filter;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * 
 * @author wilver
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager",
        basePackages = {"gt.umg.ventaonline"}
)
@EnableTransactionManagement
public class RootContext {

    private static final Logger LOG = LoggerFactory.getLogger(RootContext.class);

    @Resource
    private Environment environment;

    /**
     *
     * @return
     */
    @Bean(name = "mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @Primary
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        LOG.debug(dataSource.toString());
        dataSource.setDriverClassName(environment.getRequiredProperty(Constant.PROPERTY_NAME_DATABASE_DRIVER));
        dataSource.setJdbcUrl(environment.getRequiredProperty(Constant.PROPERTY_NAME_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(Constant.PROPERTY_NAME_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(Constant.PROPERTY_NAME_DATABASE_PASSWORD));
        dataSource.setMaximumPoolSize(Integer.parseInt(environment.getRequiredProperty(Constant.PROPERTY_NAME_DATA_SOURCER_MAXIMUN_POOLSIZE)));
        return dataSource;
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     */
    @Bean(name = "mysqlEntityManager")
    @Qualifier(value = "mysqlEntityManager")
    public EntityManager entityManager() throws ClassNotFoundException {
        return entityManagerFactory().createEntityManager();
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     */
    @Bean(name = "mysqlEntityManagerFactory")
    @Qualifier("mysqlEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        LOG.debug("urlToScan1 [{}]", environment.getRequiredProperty(Constant.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(Constant.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProterties = new Properties();
        jpaProterties.put("hibernate.dialect", environment.getRequiredProperty(Constant.PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProterties.put("hibernate.format_sql", environment.getRequiredProperty(Constant.PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProterties.put("hibernate.show_sql", environment.getRequiredProperty(Constant.PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        jpaProterties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty(Constant.PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
        
         entityManagerFactoryBean.setJpaProperties(jpaProterties);
        entityManagerFactoryBean.afterPropertiesSet();
        LOG.debug(entityManagerFactoryBean.toString());
        return entityManagerFactoryBean.getObject();
    }

    /**
     *
     * @return
     * @throws ClassNotFoundException
     */
    @Bean(name = "mysqlTransactionManager")
    @Qualifier("mysqlTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() throws ClassNotFoundException {
        return new JpaTransactionManager(entityManagerFactory());
    }

    /**
     *
     * @return
     */
    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    /**
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(Constant.VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(Constant.VIEW_RESOLVER_SUFFIX);
        LOG.debug(viewResolver.toString());
        return viewResolver;
    }

    /**
     *
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    /*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
    */
    @Bean()
    public FilterRegistrationBean corsFilter(){
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
        
    }

}
