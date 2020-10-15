package com.tgi.neverstop.manager;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tgi.neverstop.controller.UserController;
import com.tgi.neverstop.exception.BusinessException;
import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.Role;
import com.tgi.neverstop.model.RoleName;
import com.tgi.neverstop.model.User;
import com.tgi.neverstop.repository.RoleRepository;
import com.tgi.neverstop.repository.UserRepository;
import com.tgi.neverstop.security.services.UserPrinciple;

@Service
public class LoginManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	public UserDetails login(String userName, String password) throws BusinessException {

		String METHOD_NAME = "login()";
		logger.info(METHOD_NAME + "start : ");
		UserDetails userDetails = null;

		Optional<User> userDetail = Optional.ofNullable(userRepository.findByUsername(userName).orElseThrow(BusinessException::new));

		if (userDetail != null && userDetail.isPresent()) {

			User user = userDetail.get();
			boolean status = validatePassword(user, password);

			if (!status) {
				throw new BusinessException("Invalid Username Or Password.");
			} else {
				userDetails = UserPrinciple.build(user);
			}

		} else {
			throw new BusinessException("Invalid Username Or Password.");
		}

		logger.info(METHOD_NAME + "END");
		return userDetails;
	}
	
	public UserDetails adminLogin(String username, String password) throws BusinessException {
		String METHOD_NAME = "adminLogin()";
		logger.info(METHOD_NAME + "start : ");
		UserDetails userDetails = null;
		
		Optional<User> userDetail = Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(BusinessException::new));
		if (userDetail != null && userDetail.isPresent()) 
		{

			User user = userDetail.get();
			System.out.println(user.getRoles());
			for(Role role :user.getRoles())
			{
				System.out.println("Role>>>"+role.getName());
				if(!role.getName().equals(RoleName.ROLE_ADMIN))
				{
					throw new BusinessException("Invalid Username Or Password.");
				}
			}
			boolean status = validatePassword(user, password);
			if (!status) 
			{
				throw new BusinessException("Invalid Username Or Password.");
			} else {
				userDetails = UserPrinciple.build(user);
			}

		} else {
			throw new BusinessException("Invalid Username Or Password.");
		}

		logger.info(METHOD_NAME + "END");
		return userDetails;
	}

	public boolean validatePassword(User user, String password) throws BusinessException
			 {

		boolean status = false;

		try {

			logger.info("validatePassword:" + password);
			if (!encoder.matches(password, user.getPassword())) {
				throw new BusinessException("Invalid Username Or Password.");
			} else {
				status = true;
			}

			logger.info("validatePassword:" + status);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return status;
	}

	

}
