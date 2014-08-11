package br.com.afrcode.arquitetura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.afrcode.arquitetura.spring.config.util.Profiles;

@Configuration
@EnableAutoConfiguration(exclude = { HttpMessageConvertersAutoConfiguration.class,
        MessageSourceAutoConfiguration.class, PropertyPlaceholderAutoConfiguration.class, AopAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class, JpaBaseConfiguration.class, DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class, JmsTemplateAutoConfiguration.class,
        JmxAutoConfiguration.class, SecurityAutoConfiguration.class })
@ComponentScan(basePackages = { "br.com.afrcode" })
@Profile(Profiles.PROFILE_APLICACAO_BATCH)
public class BatchRunner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BatchRunner.class);
        springApplication.setWebEnvironment(false);
        springApplication.run("--debug", "--spring.profiles.active=batch");
    }

}
