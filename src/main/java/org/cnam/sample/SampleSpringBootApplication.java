package org.cnam.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.HttpAccessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = { "org.cnam.*" })
@EntityScan("org.cnam.sample.model")
@EnableJpaRepositories("org.cnam.sample.repository")
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class SampleSpringBootApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleSpringBootApplication.class, args);
    }
}