package com.informatique.gov.judicialwarrant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
<<<<<<< HEAD
=======
//@EnableSwagger2
>>>>>>> 1e7b74a701838d19ecc154723c867b1cf803701b
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
