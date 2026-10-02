package com.footballapp.football_league_app.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;

@EnableWs
@Configuration
public class WebServiceConfig {

    // Register the MessageDispatcherServlet to handle SOAP requests
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();

        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // Define WSDL for teams
    @Bean(name = "league")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema teamsSchema) {

        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();

        wsdl.setPortTypeName("LeaguePort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://footballapp.com/league");
        wsdl.setSchema(teamsSchema);

        return wsdl;
    }

    // Load XSD schema for teams
    @Bean
    public XsdSchema teamsSchema() {

        return new SimpleXsdSchema(
                new org.springframework.core.io.ClassPathResource("xsd/league.xsd")
        );
    }
}
