package com.informatique.gov.judicialwarrant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.informatique.gov.judicialwarrant.support.security.JudicialWarrantAuthenticationProvider;
import com.informatique.gov.judicialwarrant.support.security.OnlyLoginBasicAuthenticationFilter;
import com.informatique.gov.judicialwarrant.support.security.RestAuthenticationEntryPoint;

@Profile("dev")
@EnableWebSecurity
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

		http.addFilterBefore(sessionRepositoryFilter, SecurityContextPersistenceFilter.class).cors()
				.configurationSource(corsConfigurationSource()).and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER).and().logout().logoutUrl("/api/logout");

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
				environment.getRequiredProperty("app.security.activedirectory.domain"),
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
		config.addAllowedHeader("X-Auth-Token");
		config.addAllowedHeader("xat");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
