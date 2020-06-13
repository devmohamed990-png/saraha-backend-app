package com.saraha.demo.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saraha.demo.dao.RoleDAO;
import com.saraha.demo.dao.UserDAO;
import com.saraha.demo.dto.BackendReciveUserDTO;
import com.saraha.demo.dto.BackendResponseUserDTO;
import com.saraha.demo.model.Country;
import com.saraha.demo.model.Role;
import com.saraha.demo.model.User;
import com.saraha.demo.utils.GenerationRandomKey;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Value("${message.url}")
	private String messageUrl;

	private User userModel;

	private Country countryModel;

	private Role roleModel;

	private Optional<Role> roleOptional;

	private BackendResponseUserDTO backendResponseUserDTO;

	private List<User> userList;

	private List<BackendResponseUserDTO> backendResponseUserDTOList;

	@Override
	public List<BackendResponseUserDTO> findAll() {

		userList = new ArrayList<User>();

		userList = userDAO.findAll();

		if (userList.isEmpty()) {

			return null;

		} else {

			backendResponseUserDTOList = new ArrayList<BackendResponseUserDTO>();

			Type listType = new TypeToken<List<BackendResponseUserDTO>>() {
			}.getType();

			backendResponseUserDTOList = modelMapper.map(userList, listType);

			return backendResponseUserDTOList;
		}
	}

	@Override
	public List<BackendResponseUserDTO> findAllWithoutThisUser(long id) {

		userList = new ArrayList<User>();

//		userList = userDAO.findAllWithoutID(id);

		if (userList.isEmpty()) {

			return null;

		} else {

			backendResponseUserDTOList = new ArrayList<BackendResponseUserDTO>();

			Type listType = new TypeToken<List<BackendResponseUserDTO>>() {
			}.getType();

			backendResponseUserDTOList = modelMapper.map(userList, listType);

			return backendResponseUserDTOList;
		}
	}

	@Override
	public BackendResponseUserDTO findById(long id) {

		Optional<User> userOptional = userDAO.findById(id);

		if (userOptional.isPresent()) {

			backendResponseUserDTO = new BackendResponseUserDTO();

			backendResponseUserDTO = modelMapper.map(userOptional.get(), BackendResponseUserDTO.class);

			return backendResponseUserDTO;

		} else {

			return null;
		}
	}

	@Override
	public User findByKey(long key) {

		User userModel = userDAO.findByUserKey(key);

		if (userModel != null)
			return userModel;
		else
			return null;
	}

	@Override
	public BackendResponseUserDTO findByUsername(String username) {

		userModel = new User();

		userModel = userDAO.findByUsername(username);

		if (userModel != null) {

			backendResponseUserDTO = new BackendResponseUserDTO();

			backendResponseUserDTO = modelMapper.map(userModel, BackendResponseUserDTO.class);

			backendResponseUserDTO.setUrl(messageUrl + userModel.getUserKey());

			return backendResponseUserDTO;

		} else {

			return null;
		}
	}

	@Override
	public void save(BackendReciveUserDTO backendReciveUserDTO) {

		if (backendReciveUserDTO != null) {

			if (findById(backendReciveUserDTO.getUserID()) == null) {

				long randomKey = generateRandomKey();
				randomKey = Long.parseLong(String.valueOf(randomKey).replaceAll("-", ""));

				userModel = new User();

				userModel.setFirstName(backendReciveUserDTO.getUserFirstName());
				userModel.setLastName(backendReciveUserDTO.getUserLastName());
				userModel.setEmail(backendReciveUserDTO.getUserEmail());
				userModel.setUsername(backendReciveUserDTO.getUserUsername());
				userModel.setPassword(bCryptPasswordEncoder.encode(backendReciveUserDTO.getUserPassword()));
				userModel.setIsMale(backendReciveUserDTO.getUserGeneder());
				userModel.setPhone(backendReciveUserDTO.getUserPhone());
				userModel.setBirthDay(backendReciveUserDTO.getUserBirthDay());
				userModel.setUserKey(randomKey);
				userModel.setEnabled(true);
				userModel.setAccountNonExpired(true);
				userModel.setAccountNonLocked(true);
				userModel.setCredentialsNonExpired(true);
				countryModel = new Country();
				countryModel.setId(backendReciveUserDTO.getUserCountry().getCountryID());
				countryModel.setCountry(backendReciveUserDTO.getUserCountry().getCountry());
				userModel.setCountry(countryModel);

				if (backendReciveUserDTO.getUserRole() == null) {

					roleModel = new Role();
					roleOptional = roleDAO.findById(2L);
					if (roleOptional.isPresent()) {

						userModel.setRole(roleOptional.get());
						System.out.println("userModel : " + userModel);

						userDAO.save(userModel);
					}

				} else {

					roleModel = new Role();
					roleModel.setId(backendReciveUserDTO.getUserRole().getRoleID());
					roleModel.setRole(backendReciveUserDTO.getUserRole().getRole());
					userModel.setRole(roleModel);

					userDAO.save(userModel);
				}
			}
		}
	}

	private long generateRandomKey() {

		long randomKey = GenerationRandomKey.generateRandomKey();

		if (findByKey(randomKey) != null) {

			generateRandomKey();
		}

		return randomKey;
	}

	@Override
	public void update(BackendReciveUserDTO backendReciveUserDTO) {

		if (backendReciveUserDTO.getUserRole() == null) {

			userDAO.update(backendReciveUserDTO.getUserFirstName(), backendReciveUserDTO.getUserLastName(),
					backendReciveUserDTO.getUserPhone(), backendReciveUserDTO.getUserGeneder(),
					backendReciveUserDTO.getUserBirthDay(), backendReciveUserDTO.getUserEmail(),
					backendReciveUserDTO.getUserUsername(),
					bCryptPasswordEncoder.encode(backendReciveUserDTO.getUserPassword()), true, true, true, true,
					backendReciveUserDTO.getUserCountry().getCountryID(), backendReciveUserDTO.getUserID());
		} else {

			userDAO.update(backendReciveUserDTO.getUserFirstName(), backendReciveUserDTO.getUserLastName(),
					backendReciveUserDTO.getUserPhone(), backendReciveUserDTO.getUserGeneder(),
					backendReciveUserDTO.getUserBirthDay(), backendReciveUserDTO.getUserEmail(),
					backendReciveUserDTO.getUserUsername(), backendReciveUserDTO.getUserCountry().getCountryID(),
					backendReciveUserDTO.getUserRole().getRoleID(), backendReciveUserDTO.getUserID());
		}
	}

	@Override
	public void delete(long id) {

		userDAO.deleteById(id);
	}
}