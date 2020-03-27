package ru.igor.SpringConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import ru.igor.system.Security.Authorities;
import ru.igor.system.Security.User;

import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@EntityScan(basePackageClasses = ru.igor.system.model.User.class)
@ComponentScans(value = { @ComponentScan("ru.igor.system.dao"),
    @ComponentScan("ru.igor") })
public class AppConfig {

  @Autowired
  private Environment env;

  @Bean
  public Properties props(){
    Properties props = new Properties();
    props.put(DRIVER, env.getProperty("mysql.driver"));
    props.put(URL, env.getProperty("mysql.jdbcUrl"));
    props.put(USER, env.getProperty("mysql.username"));
    props.put(PASS, env.getProperty("mysql.password"));

    // Setting Hibernate properties
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

    // Setting C3P0 properties
    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
    props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
    props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
    props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
    return props;
  }
  @Bean
  @Autowired
  //@Qualifier("security")
  public LocalSessionFactoryBean getSessionFactory(Properties props) {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
    factoryBean.setHibernateProperties(props);
    factoryBean.setAnnotatedClasses(User.class, Authorities.class, ru.igor.system.model.User.class);
    return factoryBean;
  }

  @Bean
  public HibernateTransactionManager getTransactionManager() {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(getSessionFactory(props()).getObject());
    return transactionManager;
  }
  @Bean
  public Validator validatorFactory () {
    return new LocalValidatorFactoryBean();
  }
}
