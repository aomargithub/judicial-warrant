package com.informatique.gov.judicialwarrant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
<<<<<<< HEAD
=======
//@EnableSwagger2
>>>>>>> branch 'master' of https://github.com/aomargithub/judicial-warrant
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
