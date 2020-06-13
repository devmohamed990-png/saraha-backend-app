package com.saraha.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.PersonDAO;
import com.saraha.demo.model.Person;

@Service
public class ThankingServiceImpl implements ThankingService {

	@Autowired
	private PersonDAO personDAO;

	@Value("${spring.social.facebook.app-id}")
	private String appId;

	@Value("${spring.social.facebook.app-secret}")
	private String appSecret;

	private OAuth2Operations oAuth2Operations;

	private AccessGrant accessToken;

	private Connection<Facebook> connection;

	private Facebook facebook;

	private FacebookConnectionFactory factory;

	private Person person;

	@Override
	public String saveDataForMessageWithPerson(String authorizationCode) {

		factory = new FacebookConnectionFactory(appId, appSecret);

		oAuth2Operations = factory.getOAuthOperations();

		accessToken = oAuth2Operations.exchangeForAccess(authorizationCode,
				"https://saraa-app.herokuapp.com/api/send-message", null);

		connection = factory.createConnection(accessToken);

		facebook = connection.getApi();

		String[] fields = { "id", "email", "first_name", "last_name" };

		User userProfile = facebook.fetchObject("me", User.class, fields);

		String imageUrl = "http://graph.facebook.com/" + userProfile.getId() + "/picture?type=square";

		String email = userProfile.getEmail();

		person = new Person();

		person = personDAO.findByEmail(email);

		if (person != null) {

			System.out.println(imageUrl);

			personDAO.update(userProfile.getFirstName() + " " + userProfile.getLastName(), userProfile.getEmail(),
					Boolean.parseBoolean(userProfile.getGender()), imageUrl, authorizationCode, person.getId());

		} else {

			person = new Person();

			person.setName(userProfile.getFirstName() + " " + userProfile.getLastName());
			person.setEmail(userProfile.getEmail());
			person.setIsMale(Boolean.parseBoolean(userProfile.getGender()));
			person.setProfilePictureUrl(imageUrl);
			person.setFacebookCode(authorizationCode);
			person.setPersonKey(0);
			personDAO.save(person);
		}

		return "welcome";
	}
}