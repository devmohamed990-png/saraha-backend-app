package com.saraha.demo.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.PersonDAO;
import com.saraha.demo.dto.BackendResponsePersonDTO;
import com.saraha.demo.model.Message;
import com.saraha.demo.model.Person;
import com.saraha.demo.model.User;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private ModelMapper modelMapper;

	private BackendResponsePersonDTO backendResponsePersonDTO;

	private List<Person> personList;

	private List<BackendResponsePersonDTO> backendResponsePersonDTOList;

	@Override
	public List<BackendResponsePersonDTO> findAll() {

		personList = new ArrayList<Person>();

		personList = personDAO.findAll();

		if (personList.isEmpty()) {

			return null;

		} else {

			backendResponsePersonDTOList = new ArrayList<BackendResponsePersonDTO>();

			Type listType = new TypeToken<List<BackendResponsePersonDTO>>() {
			}.getType();

			backendResponsePersonDTOList = modelMapper.map(personList, listType);

			return backendResponsePersonDTOList;
		}
	}

	@Override
	public BackendResponsePersonDTO findById(long id) {

		Optional<Person> personOptional = personDAO.findById(id);

		if (personOptional.isPresent()) {

			backendResponsePersonDTO = new BackendResponsePersonDTO();

			backendResponsePersonDTO = modelMapper.map(personOptional.get(), BackendResponsePersonDTO.class);

			return backendResponsePersonDTO;

		} else {

			return null;
		}
	}

	@Override
	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(long randomNumber) {

		Map<String, Boolean> returnedMap = new HashMap<String, Boolean>();

		Person personModel = personDAO.findByPersonKey(randomNumber);

		if (personModel != null)
			returnedMap.put("status", true);
		else
			returnedMap.put("status", false);

		return returnedMap;
	}

	@Override
	public Map<String, String> updatePersonAndMessage(long randomNumberMessage, long key, String code) {

		Map<String, String> returnedMap = new HashMap<String, String>();

		User userModel = userService.findByKey(key);

		Message messageModel = messageService.findByUserIdAndKey(userModel, randomNumberMessage);

		if (messageModel != null) {

			Person personModel = personDAO.findByFacebookCode(code);

			if (personModel != null) {

				messageService.update(personModel, messageModel.getId());

				returnedMap.put("status", "1");
				returnedMap.put("message", "your message sent successfully");
				returnedMap.put("action", "You can go to Login page by click here.");
				returnedMap.put("page", "/login");

			} else {

				returnedMap.put("status", "0");
				returnedMap.put("message", "This message doesn't send,Please try again.");
				returnedMap.put("action", "You can go to back by click here.");
				returnedMap.put("page", "/message");
			}

		} else {

			returnedMap.put("status", "0");
			returnedMap.put("message", "This message doesn't send,Please try again.");
			returnedMap.put("action", "You can go to back by click here.");
			returnedMap.put("page", "/message");
		}

		return returnedMap;
	}
}