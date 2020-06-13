package com.saraha.demo.oauth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

		configurer.inMemory().withClient(Constants.CLIENT_APPLICATION_NAME)
				.secret(Constants.CLIENT_APPLICATION_PASSWORD).authorizedGrantTypes("password", "refresh_token")
				.scopes("write");
	}

	/**
	 * .withClient(Constants.CLIENT_APPLICATION_NAME)
	 * 
	 * Provides an opportunity for customization of an access token (e.g. through
	 * its additional information map) during the process of creating a new token
	 * for use by a client.
	 * 
	 * @param accessToken    the current access token with its expiration and
	 *                       refresh token
	 * @param authentication the current authentication including client and user
	 *                       details
	 * @return a new token enhanced with additional information
	 */

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));

		endpoints.tokenStore(tokenStore).tokenEnhancer(enhancerChain).authenticationManager(authenticationManager)
				.reuseRefreshTokens(true).userDetailsService(userDetailsService);
	}
}