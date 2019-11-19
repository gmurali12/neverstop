package com.tgi.neverstop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.neverstop.manager.LoginManagerImpl;
import com.tgi.neverstop.manager.UserManagerImpl;
import com.tgi.neverstop.model.JwtResponse;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.model.User;
import com.tgi.neverstop.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class LoginController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	
	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
    JwtProvider jwtProvider;
	
	@Autowired
	LoginManagerImpl loginManager;
	
	@Autowired
	UserManagerImpl userManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {

		String METHOD_NAME = "login()";
		logger.info(METHOD_NAME + "start : ");
		
		UserDetails userDetails = null;
		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			userDetails = loginManager.login(username, password);
			
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = e.getMessage();
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseObjectsMap.put("UserDetails", userDetails);
			responseObjectsMap.put("accessToken", populateToken(username,password));
			responseObjectsMap.put("tokenType", "Bearer");

			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	
	@PostMapping("/adminLogin")
	public ResponseEntity<?> adminLogin(@RequestParam String username, @RequestParam String password) {

		String METHOD_NAME = "login()";
		logger.info(METHOD_NAME + "start : ");
		
		UserDetails userDetails = null;
		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			userDetails = loginManager.adminLogin(username, password);
			
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = e.getMessage();
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseObjectsMap.put("UserDetails", userDetails);
			responseObjectsMap.put("accessToken", populateToken(username,password));
			responseObjectsMap.put("tokenType", "Bearer");

			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

	@PostMapping("/generateToken")
    public ResponseEntity<?> getUserToken(@RequestParam String username, @RequestParam String password) {
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
       
        SecurityContextHolder.getContext().setAuthentication(authentication);
      
        String jwt = jwtProvider.generateJwtToken(authentication);
        
        System.out.println("jwt-->"+jwt);
      
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public String populateToken(String username, String password) {
    	
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        
        return jwt;
    }

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

		String METHOD_NAME = "registerUser()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			user = userManager.saveUser(user);
			responseObjectsMap.put("UserVO", user);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to register user.";
		} catch (Throwable e) {
			msg = e.getMessage();
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
	
	@PostMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestParam String username) {

		String METHOD_NAME = "registerUser()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			User user=userManager.forgetPassword(username);
			responseObjectsMap.put("UserVO", user);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = ""
					+ ".";
		} catch (Throwable e) {
			msg = "Error while setting password.";
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
