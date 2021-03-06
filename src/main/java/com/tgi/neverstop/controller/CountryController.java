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

		try {
			List<Country> countryList = countryManager.getAllCountry();
			responseObjectsMap.put("CountryList", countryList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select Country.";
		} catch (Throwable e) {
			msg = "Unable to select Country.";
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
	public ResponseEntity<?> searchbyName(@RequestParam String countryName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Country> countrytList = countryManager.searchbyName(countryName);
			responseObjectsMap.put("CountryList", countrytList);
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

	@PostMapping("/getCountryByContinentId")
	public ResponseEntity<?> getCountryByContinentId(
			@RequestParam String continentId) {

		String METHOD_NAME = "getCountryByContinentId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {

			if (continentId != null) {

				List<Country> countryList = countryManager
						.getCountryByContinentId(continentId);
				responseObjectsMap.put("CountryList", countryList);
			} else {
				throw new Exception("Invalid Request.");
			}
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select Country.";
		} catch (Throwable e) {
			msg = "Unable to select Country.";
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

	@PostMapping("/saveCountry")
	public ResponseEntity<?> saveCountry(@Valid @RequestBody Country country) {

		String METHOD_NAME = "saveCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			country = countryManager.saveCountry(country);
			
			responseObjectsMap.put("CountryVO", country);
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

	@PostMapping("/saveCountryImage")
	public ResponseEntity<?> saveCountryImage(@RequestPart String countryId,
			@RequestPart(value = "countryImg", required = false) MultipartFile countryImg) {

		String METHOD_NAME = "saveCountryImage()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		Country country;

		try {
			country = countryManager.saveCountryImage(countryId,countryImg);
			responseObjectsMap.put("CountryVO", country);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save country.";
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
	
	@PostMapping("/deleteCountryImage")
	public ResponseEntity<?> deleteCountryImage(@RequestPart String countryId) {

		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		Country country;

		try {
			country = countryManager.deleteCountryImage(countryId);
			responseObjectsMap.put("CountryVO", country);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to Delete country.";
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
	
	@PostMapping("/updateCountry")
	public ResponseEntity<?> updateCountry(@Valid @RequestBody Country country) {

		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			country = countryManager.updateCountry(country);
		//	country = countryManager.saveCountry(country);
			responseObjectsMap.put("CountryVO", country);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to Update Country-"+re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to Update Country."+e.getMessage();
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

	@PostMapping("/getCountryById")
	public ResponseEntity<?> getCountryById(@RequestParam String countryId) {

		String METHOD_NAME = "getCountryById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			Country country = countryManager.findById(countryId);
			responseObjectsMap.put("CountryVO", country);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save Country.";
		} catch (Throwable e) {
			msg = "Unable to save Country.";
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
