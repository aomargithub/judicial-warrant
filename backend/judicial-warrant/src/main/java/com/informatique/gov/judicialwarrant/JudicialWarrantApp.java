package com.informatique.gov.judicialwarrant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;


@SpringBootApplication
public class JudicialWarrantApp extends SpringBootServletInitializer  implements WebApplicationInitializer 
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
