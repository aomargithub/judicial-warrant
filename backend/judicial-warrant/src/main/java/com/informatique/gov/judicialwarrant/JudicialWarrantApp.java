package com.informatique.gov.judicialwarrant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class JudicialWarrantApp extends SpringBootServletInitializer 
{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JudicialWarrantApp.class);
    }
	
    public static void main( String[] args )
    {
        SpringApplication.run(JudicialWarrantApp.class, args);
    }
}
