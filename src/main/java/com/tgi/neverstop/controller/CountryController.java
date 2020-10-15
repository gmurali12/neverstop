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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

import com.tgi.neverstop.exception.BusinessException;
import com.tgi.neverstop.manager.CountryManagerImpl;
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.model.Country;
import com.tgi.neverstop.model.ResponseVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/country")
public class CountryController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(CountryController.class);

	@Autowired
	CountryManagerImpl countryManager;

	@GetMapping("/getAllCountry")
	public ResponseEntity<?> getAllCountry() {

		String METHOD_NAME = "getAllCountry()";
		logger.info(METHOD_NAME + "start : ");
		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
        List<Country> countryList = countryManager.getAllCountry();
		responseObjectsMap.put("CountryList", countryList);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
	
	}
	
	@PostMapping("/searchByName")
	public ResponseEntity<?> searchbyName(@RequestParam String countryName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		List<Country> countrytList = countryManager.searchbyName(countryName);
		responseObjectsMap.put("CountryList", countrytList);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
		
	}

	@PostMapping("/getCountryByContinentId")
	public ResponseEntity<?> getCountryByContinentId(
			@RequestParam String continentId) throws Exception {

		String METHOD_NAME = "getCountryByContinentId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		if (continentId != null) {
				List<Country> countryList = countryManager
						.getCountryByContinentId(continentId);
				responseObjectsMap.put("CountryList", countryList);
			} else {
				throw new Exception("Invalid Request.");
			}
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);


	}

	@PostMapping("/saveCountry")
	public ResponseEntity<?> saveCountry(@Valid @RequestBody Country country) {

		String METHOD_NAME = "saveCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		country = countryManager.saveCountry(country);
		responseObjectsMap.put("CountryVO", country);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
		

	}

	@PostMapping("/saveCountryImage")
	public ResponseEntity<?> saveCountryImage(@RequestPart String countryId,
			@RequestPart(value = "countryImg", required = false) MultipartFile countryImg) {

		String METHOD_NAME = "saveCountryImage()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		Country country;
		country = countryManager.saveCountryImage(countryId,countryImg);
		responseObjectsMap.put("CountryVO", country);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);

	}
	
	@PostMapping("/deleteCountryImage")
	public ResponseEntity<?> deleteCountryImage(@RequestPart String countryId) throws BusinessException, Throwable {

		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		Country country;
		country = countryManager.deleteCountryImage(countryId);
		responseObjectsMap.put("CountryVO", country);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
		
	}
	
	@PostMapping("/updateCountry")
	public ResponseEntity<?> updateCountry(@Valid @RequestBody Country country) {

		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		country = countryManager.updateCountry(country);
		responseObjectsMap.put("CountryVO", country);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
		
	}

	@PostMapping("/getCountryById")
	public ResponseEntity<?> getCountryById(@RequestParam String countryId) {

		String METHOD_NAME = "getCountryById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		Country country = countryManager.findById(countryId);
		responseObjectsMap.put("CountryVO", country);
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponse(responseObjectsMap);
		return ResponseEntity.ok().body(responseVO);
		
	}

}
