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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.manager.CityManagerImpl;
import com.tgi.neverstop.model.City;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.model.State;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/city")
public class CityController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(CityController.class);

	@Autowired
	CityManagerImpl cityManager;

	@GetMapping("/getAllCity")
	public ResponseEntity<?> getAllCity() {

		String METHOD_NAME = "getAllCity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<City> cityList = cityManager.getAllCity();
			responseObjectsMap.put("CityList", cityList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select City.";
		} catch (Throwable e) {
			msg = "Unable to select City.";
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
	
	@PostMapping("/searchByName")
	public ResponseEntity<?> searchbyName(@RequestParam String cityName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<City> cityList = cityManager.searchbyName(cityName);
			responseObjectsMap.put("CityList", cityList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
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

	@PostMapping("/getCityByStateId")
	public ResponseEntity<?> getCityByStateId(@RequestParam String stateId) {

		String METHOD_NAME = "getCityByStateId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<City> cityList = cityManager.getCityByStateId(stateId);
			
			responseObjectsMap.put("CityList", cityList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select City.";
		} catch (Throwable e) {
			msg = "Unable to select City.";
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

	@PostMapping("/saveCity")
	public ResponseEntity<?> saveCity(@Valid @RequestBody City city) {

		String METHOD_NAME = "saveCity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			city = cityManager.saveCity(city);
			responseObjectsMap.put("CityVO", city);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save City.";
		} catch (Throwable e) {
			msg = "Unable to save City.";
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
	
	@PostMapping("/saveCityImage")
	public ResponseEntity<?> saveCityImage(@RequestPart String cityId,
			@RequestPart(value = "cityImg", required = false) MultipartFile cityImg) {

		String METHOD_NAME = "saveCityImage()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		City city;

		try {
			city = cityManager.saveCityImage(cityId,cityImg);
			responseObjectsMap.put("CityVO", city);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save city.";
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

	
	@PostMapping("/updateCity")
	public ResponseEntity<?> updateCity(@Valid @RequestBody City city) {

		String METHOD_NAME = "updateCity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			city = cityManager.saveCity(city);
			responseObjectsMap.put("CityVO", city);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save City.";
		} catch (Throwable e) {
			msg = "Unable to save City.";
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

	@PostMapping("/getCityById")
	public ResponseEntity<?> getCityById(@RequestParam String cityId) {

		String METHOD_NAME = "getCityById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			City city = cityManager.findById(cityId);
			responseObjectsMap.put("CityVO", city);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save City.";
		} catch (Throwable e) {
			msg = "Unable to save City.";
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
