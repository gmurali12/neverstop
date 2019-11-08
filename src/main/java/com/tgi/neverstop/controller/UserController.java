package com.tgi.neverstop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.neverstop.manager.UserManagerImpl;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.model.User;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserManagerImpl userManager;

	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {

		String METHOD_NAME = "getAllUsers()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<User> userList = userManager.getAllUsers();
			responseObjectsMap.put("UserList", userList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to get userList.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

	@PostMapping("/updateProfile")
	public ResponseEntity<?> updateProfile(@Valid @RequestBody User user) {

		String METHOD_NAME = "updateProfile()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			user = userManager.updateUser(user);
			responseObjectsMap.put("userVO", user);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to get userList.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestParam String username,@RequestParam String newPassword) {

		String METHOD_NAME = "registerUser()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			User user=userManager.updatePassword(username,newPassword);
			responseObjectsMap.put("UserVO", user);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to register user.";
		} catch (Throwable e) {
			msg = "Unable to register user.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

}
