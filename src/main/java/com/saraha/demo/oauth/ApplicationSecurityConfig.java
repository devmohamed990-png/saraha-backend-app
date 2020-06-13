package com.saraha.demo.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsServiceImpl;

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {

		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(getEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
				.antMatchers("/swagger-resources/**", "/v2/api-docs", "/configuration/**", "/swagger-ui.html/**",
						"/user/save", "/country/findAll", "/facebook/**", "/message/save", "/api/send-message",
						"/person/check-random-number", "/person/update", "/message/check-random-number")
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());

	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {

		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

		return converter;
	}

	@Bean
	public PasswordEncoder getEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public TokenStore tokenStore() {

		return new JwtTokenStore(accessTokenConverter());
	}

	// addtion

	@Bean
	public DefaultTokenServices defaultTokenServices() {

		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();

		defaultTokenServices.setTokenStore(tokenStore());

		defaultTokenServices.setSupportRefreshToken(true);

		return defaultTokenServices;
	}
}