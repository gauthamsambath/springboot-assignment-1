package com.stackroute.MuzixApplication.JavaConfig;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WebConfiguration
{
    @Bean
    ServletRegistrationBean h2ServletRegistration()
        {
            ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebServlet());
            registrationBean.addUrlMappings("/console/*");
            return registrationBean;
        }
}
