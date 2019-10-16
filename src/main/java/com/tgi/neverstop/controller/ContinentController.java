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

import com.tgi.neverstop.manager.ContinentManagerImpl;
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.model.ResponseVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/continent")
public class ContinentController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(ContinentController.class);

	@Autowired
	ContinentManagerImpl continentManager;

	@GetMapping("/getAllContinent")
	public ResponseEntity<?> getAllUsers() {

		String METHOD_NAME = "getAllContinent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Continent> continentList = continentManager.getAllContinent();
			responseObjectsMap.put("ContinentList", continentList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to select Continent.";
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

	@PostMapping("/saveContinent")
	public ResponseEntity<?> saveContinent(@Valid @RequestBody Continent continent) {

		String METHOD_NAME = "saveContinent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			continent = continentManager.saveContinent(continent);
			responseObjectsMap.put("ContinentVO", continent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save continent.";
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
	
	@PostMapping("/updateContinent")
	public ResponseEntity<?> updateContinent(@Valid @RequestBody Continent continent) {

		String METHOD_NAME = "updateContinent()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			continent = continentManager.saveContinent(continent);
			responseObjectsMap.put("ContinentVO", continent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save continent.";
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

	@PostMapping("/getContinentById")
	public ResponseEntity<?> getContinentById(@RequestParam String id) {

		String METHOD_NAME = "getContinentById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			Continent continent = continentManager.findById(id);
			responseObjectsMap.put("ContinentVO", continent);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select Continent.";
		} catch (Throwable e) {
			msg = "Unable to select Continent.";
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