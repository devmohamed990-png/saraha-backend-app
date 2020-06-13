package com.saraha.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService {

	@Autowired
	private MessageService messageService;

	@Value("${spring.social.facebook.app-id}")
	private String appId;

	@Value("${spring.social.facebook.app-secret}")
	private String appSecret;

	private FacebookConnectionFactory factory;

	private OAuth2Operations oAuth2Operations;

	private OAuth2Parameters oAuth2Parameters;

	private Map<String, String> map;

	@Override
	public Map<String, String> producer(String message, long randomNumber, long key) {

		map = new HashMap<String, String>();

		factory = new FacebookConnectionFactory(appId, appSecret);

		oAuth2Operations = factory.getOAuthOperations();

		oAuth2Parameters = new OAuth2Parameters();

		oAuth2Parameters.setRedirectUri("https://saraa-app.herokuapp.com/api/send-message");
		oAuth2Parameters.setScope("email,public_profile");

		String url = oAuth2Operations.buildAuthenticateUrl(oAuth2Parameters);

		map.put("url", url);

		messageService.save(message, randomNumber, key);

		return map;
	}
}
