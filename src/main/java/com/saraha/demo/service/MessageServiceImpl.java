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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.MessageDAO;
import com.saraha.demo.dto.BackendResponseMessageDTO;
import com.saraha.demo.dto.BackendResponsePersonDTO;
import com.saraha.demo.dto.BackendResponseUserDTO;
import com.saraha.demo.model.Message;
import com.saraha.demo.model.Person;
import com.saraha.demo.model.User;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDAO;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	private Message messageModel;

	private User userModel;

	private BackendResponseMessageDTO backendResponseMessageDTO;

	List<Message> messageList;

	List<BackendResponseMessageDTO> backendResponseMessageDTOList;

	@Override
	public List<BackendResponseMessageDTO> findAll() {

		messageList = new ArrayList<Message>();

		messageList = messageDAO.findAll();

		if (messageList.isEmpty()) {

			return null;

		} else {

			backendResponseMessageDTOList = new ArrayList<BackendResponseMessageDTO>();

			Type listType = new TypeToken<List<BackendResponseMessageDTO>>() {
			}.getType();

			backendResponseMessageDTOList = modelMapper.map(messageList, listType);

			return backendResponseMessageDTOList;
		}
	}

	@Override
	public BackendResponseMessageDTO findById(long id) {

		Optional<Message> messageOptional = messageDAO.findById(id);

		if (messageOptional.isPresent()) {

			backendResponseMessageDTO = new BackendResponseMessageDTO();

			backendResponseMessageDTO = modelMapper.map(messageOptional.get(), BackendResponseMessageDTO.class);

			return backendResponseMessageDTO;

		} else {

			return null;
		}
	}

	@Override
	public List<BackendResponseMessageDTO> findByUsername() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		messageList = new ArrayList<Message>();

		messageList = messageDAO.findByUserUsernameAndActive(authentication.getName(), true);

		if (messageList.isEmpty()) {

			return null;

		} else {

			backendResponseMessageDTOList = new ArrayList<BackendResponseMessageDTO>();

			Type listType = new TypeToken<List<BackendResponseMessageDTO>>() {
			}.getType();

			backendResponseMessageDTOList = modelMapper.map(messageList, listType);

			int i = 0;

			for (Message message : messageList) {

				backendResponseMessageDTOList.get(i).setBackendResponseUserDTO(new BackendResponseUserDTO());
				backendResponseMessageDTOList.get(i).setBackendResponsePersonDTO(new BackendResponsePersonDTO());

				modelMapper.map(message.getUser(), backendResponseMessageDTOList.get(i).getBackendResponseUserDTO());
				modelMapper.map(message.getPerson(),
						backendResponseMessageDTOList.get(i).getBackendResponsePersonDTO());

				i++;
			}

			return backendResponseMessageDTOList;
		}
	}

	public void deleteById(long id) {

		if (id > 0) {

			messageDAO.updateStatus(false, id);
		}
	}

	@Override
	public void save(String message, long randomNumber, long key) {

		if (key > 0) {

			userModel = userService.findByKey(key);

			messageModel = new Message();

			messageModel.setMessage(message);
			messageModel.setActive(true);
			messageModel.setUser(userModel);
			messageModel.setMessageKey(randomNumber);

			messageDAO.save(messageModel);
		}
	}

	@Override
	public void update(Person person, long id) {

		if (person != null && id > 0) {

			messageDAO.update(person, id);
		}
	}

	@Override
	public void delete(long id) {

		if (id > 0) {

			messageDAO.deleteById(id);
		}
	}

	@Override
	public Map<String, Boolean> checkIfNumberInDatabaseOrNot(long randomNumber) {

		Map<String, Boolean> returnedMap = new HashMap<String, Boolean>();

		Message messageModel = messageDAO.findByMessageKey(randomNumber);

		if (messageModel != null)
			returnedMap.put("status", true);
		else
			returnedMap.put("status", false);

		return returnedMap;
	}

	@Override
	public Message findByUserIdAndKey(User user, long key) {

		return messageDAO.findByUserAndMessageKey(user, key);
	}
}