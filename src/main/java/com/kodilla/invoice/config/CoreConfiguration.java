package com.kodilla.invoice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;

@EnableScheduling
@EnableSwagger2
@Configuration
public class CoreConfiguration implements WebMvcConfigurer{

        @Bean
        public RestTemplate restTemplate() {

            return new RestTemplate();
        }
        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("my.gmail@gmail.com");
            mailSender.setPassword("password");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            return mailSender;
        }
        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    //.apis(RequestHandlerSelectors.any())
                    .apis(RequestHandlerSelectors.basePackage("com.crud.tasks.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }

        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            // Required by Swagger UI configuration
            registry.addResourceHandler("/lib/**").addResourceLocations("/lib/").setCachePeriod(0);
            registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCachePeriod(0);
            registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(0);
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }
