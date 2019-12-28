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

import com.tgi.neverstop.manager.StateManagerImpl;
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.model.Country;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.model.State;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/state")
public class StateController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(StateController.class);

	@Autowired
	StateManagerImpl stateManager;

	@GetMapping("/getAllState")
	public ResponseEntity<?> getAllState() {

		String METHOD_NAME = "getAllState()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<State> stateList = stateManager.getAllState();
			responseObjectsMap.put("StateList", stateList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select State.";
		} catch (Throwable e) {
			msg = "Unable to select State.";
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
	public ResponseEntity<?> searchbyName(@RequestParam String stateName) {

		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<State> stateList = stateManager.searchbyName(stateName);
			responseObjectsMap.put("StateList", stateList);
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

	@PostMapping("/getStateByCountryId")
	public ResponseEntity<?> getStateByCountryId(
			@RequestParam String countryId) {

		String METHOD_NAME = "getStateByCountryId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {

			if (countryId != null) {

				List<State> stateList = stateManager
						.getStateByCountryId(countryId);
				responseObjectsMap.put("StateList", stateList);
			} else {
				throw new Exception("Invalid Request.");
			}
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to select State.";
		} catch (Throwable e) {
			msg = "Unable to select State.";
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

	@PostMapping("/saveState")
	public ResponseEntity<?> saveState(@Valid @RequestBody State state) {

		String METHOD_NAME = "saveState()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			state = stateManager.saveState(state);
			responseObjectsMap.put("StateVO", state);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save State-"+re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save State-"+e.getMessage();
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
	
	@PostMapping("/saveStateImage")
	public ResponseEntity<?> saveStateImage(@RequestPart String stateId,
			@RequestPart(value = "stateImg", required = false) MultipartFile stateImg) {

		String METHOD_NAME = "saveStateImage()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		State state;

		try {
			state = stateManager.saveStateImage(stateId,stateImg);
			responseObjectsMap.put("StateVO", state);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to save state.";
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
	
	@PostMapping("/deleteStateImage")
	public ResponseEntity<?> deleteStateImage(@RequestPart String stateId) {

		String METHOD_NAME = "updateState()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		State state;

		try {
			state = stateManager.deleteStateImage(stateId);
			responseObjectsMap.put("StateVO", state);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = re.getMessage();
		} catch (Throwable e) {
			msg = "Unable to Delete state.";
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

	@PostMapping("/updateState")
	public ResponseEntity<?> updateState(@Valid @RequestBody State state) {

		String METHOD_NAME = "updateState()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			state = stateManager.updateState(state);
			//state = stateManager.saveState(state);
			responseObjectsMap.put("StateVO", state);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to save State.";
		} catch (Throwable e) {
			msg = "Unable to save State.";
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

	@PostMapping("/getStateById")
	public ResponseEntity<?> getStateById(@RequestParam String stateId) {

		String METHOD_NAME = "getStateById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			State state = stateManager.findById(stateId);
			responseObjectsMap.put("StateVO", state);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to update State.";
		} catch (Throwable e) {
			msg = "Unable to update State.";
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
