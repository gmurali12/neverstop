package com.tgi.neverstop.manager;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgi.neverstop.controller.UserController;
import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.Role;
import com.tgi.neverstop.model.RoleName;
import com.tgi.neverstop.model.User;
import com.tgi.neverstop.repository.RoleRepository;
import com.tgi.neverstop.repository.UserRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class UserManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CommonUtilities commonUtil;	

	public List<User> getAllUsers() {

		String METHOD_NAME = "getAllUsers()";
		logger.info(METHOD_NAME + "start : ");
		List<User> userList = null;

		try {

			userList = userRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return userList;
	}

	public User saveUser(User user) {
		String METHOD_NAME = "saveUser()";
		logger.info(METHOD_NAME + "start : ");

		try {

			setDefaultValues(user);
			user = userRepository.save(user);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "End: ");
		return user;
	}

	private void setDefaultValues(User user) {

		String METHOD_NAME = "setDefaultValues()";
		logger.info(METHOD_NAME + "start : ");
		if(user.getId()==null) {
		Set<Role> roles = new HashSet<>();
		if (user.getEmail().contains("neverstop")) {
			logger.info(METHOD_NAME + "...ADMIN user : ");
			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
					.orElseThrow(
							() -> new RuntimeException(
									"Fail! -> Cause: User Role not find."));
			roles.add(adminRole);
		} else {
			logger.info(METHOD_NAME + "normal user : ");
			Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
					.orElseThrow(
							() -> new RuntimeException(
									"Fail! -> Cause: User Role not find."));
			roles.add(userRole);
		}
			user.setId(commonUtil.generateRandomUUID());
			user.setActive(true);
			long millis = System.currentTimeMillis();
			Timestamp date = new Timestamp(millis);
			user.setRegisterDate(date);
		}
		user.setPassword(encoder.encode(user.getPassword()));
		logger.info(METHOD_NAME + "End: ");
	}

	public @Valid User updateUser(@Valid User user) {
		String METHOD_NAME = "updateUser()";
		logger.info(METHOD_NAME + "start : ");

		try {

			setDefaultValues(user);
			user = userRepository.save(user);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "End: ");
		return user;
	}
	
	public User forgetPassword(String userName) throws NeverStopExcpetion 
	{
		String resMsg=null;
		String METHOD_NAME = "forgetPassword()";
		logger.info(METHOD_NAME + "start : ");
		User user;
		Optional<User> userDetail = userRepository.findByUsername(userName);
		if (userDetail != null && userDetail.isPresent())
		{
			 user = userDetail.get();
			String password = new String(commonUtil.geek_Password(8));
			user.setPassword(password);
			updateUser(user);
			commonUtil.generateforgetPwdMail(user.getEmail(),password);
		} else {
			throw new NeverStopExcpetion("Invalid Username");
		}
		logger.info(METHOD_NAME + "END");
		
		return user;
	}

	public User updatePassword(String username, String newPassword) throws NeverStopExcpetion 
	
	{ User user=null;
		Optional<User> userDetail = userRepository.findByUsername(username);
		if (userDetail != null && userDetail.isPresent())
		{
			user = userDetail.get();
			user.setPassword(encoder.encode(newPassword));
			updateUser(user);
		} else {
			throw new NeverStopExcpetion("Invalid Username");
		}
		return user;
	}
	
}
