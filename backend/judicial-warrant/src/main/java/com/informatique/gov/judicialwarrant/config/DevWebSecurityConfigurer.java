package com.informatique.gov.judicialwarrant.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.informatique.gov.judicialwarrant.support.security.Constants;
import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantAuthenticationProvider;
import com.informatique.gov.judicialwarrant.support.security.OnlyLoginBasicAuthenticationFilter;
import com.informatique.gov.judicialwarrant.support.security.RestAuthenticationEntryPoint;

@Profile("dev")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Configuration
public class DevWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Autowired
	private SessionRepositoryFilter<?> sessionRepositoryFilter;
	
	@Autowired
	private JudicialWarrantAuthenticationProvider judicialWarrantAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.addFilterBefore(sessionRepositoryFilter, SecurityContextPersistenceFilter.class).
		     cors().configurationSource(corsConfigurationSource()).and().
		     csrf().disable().
		     sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and().
		     logout().logoutUrl(Constants.LOGOUT_URL).logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));

	    http.addFilterBefore(onlyLoginBasicAuthenticationFilter(), RequestCacheAwareFilter.class).exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint());
		http.authorizeRequests().antMatchers("/api/**").authenticated();
	}

	@Bean
	public AuthenticationEntryPoint restAuthenticationEntryPoint() {
		AuthenticationEntryPoint authenticationEntryPoint = new RestAuthenticationEntryPoint();
		return authenticationEntryPoint;
	}

	@Bean
	public OnlyLoginBasicAuthenticationFilter onlyLoginBasicAuthenticationFilter() throws Exception {
		OnlyLoginBasicAuthenticationFilter onlyLoginBasicAuthenticationFilter = new OnlyLoginBasicAuthenticationFilter(
				authenticationManagerBean(), restAuthenticationEntryPoint());

		return onlyLoginBasicAuthenticationFilter;
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(judicialWarrantAuthenticationProvider);
	}

	

	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		return new ActiveDirectoryLdapAuthenticationProvider(
				formatDomain(environment.getRequiredProperty("app.security.activedirectory.domain")),
				environment.getRequiredProperty("app.security.activedirectory.url"));
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("Authorization");
		config.addAllowedHeader("Content-Type");
		config.addAllowedHeader(Constants.AUTHENTICATION_TOKEN_NAME);
		config.addAllowedHeader("xat");
		config.addAllowedMethod("*");
		config.addAllowedHeader("If-None-Match");
		config.addAllowedHeader("If-Match");
		config.addExposedHeader("ETag");
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public LdapContextSource contextSource() {
	    LdapContextSource contextSource = new LdapContextSource();
	     
	    contextSource.setUrl(environment.getRequiredProperty("app.security.activedirectory.url"));
	    contextSource.setBase(
	    		environment.getRequiredProperty("app.security.activedirectory.domain"));
	    contextSource.setUserDn(
	    		environment.getRequiredProperty("app.security.activedirectory.username"));
	    contextSource.setPassword(
	    		environment.getRequiredProperty("app.security.activedirectory.password"));
	     
	    return contextSource;
	}
	
	@Bean
	public LdapTemplate ldapTemplate() {
	    return new LdapTemplate(contextSource());
	}
	
	
	private static String formatDomain(String domain) {
		String domainStr=domain.substring(domain.indexOf("dc="), domain.length());
		String ds[]=domainStr.split(",");
		List<String> d=new ArrayList<>();
		String output="";
		for(int i=0;i<ds.length;i++)
		{
			d.add(ds[i].substring(ds[i].indexOf("=")+1));
		}
		for(String s:d) {
			output+=s+".";
		}
		output=output.substring(0, output.length()-1);
		return output;
	}	
	
	
	
}
