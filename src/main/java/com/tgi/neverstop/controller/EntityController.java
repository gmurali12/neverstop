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

import com.tgi.neverstop.manager.EntityManagerImpl;
import com.tgi.neverstop.model.EntityVO;
import com.tgi.neverstop.model.ResponseVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/entity")
public class EntityController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(EntityController.class);

	@Autowired
	EntityManagerImpl entityManager;


	@GetMapping("/getAllEntity")
	public ResponseEntity<?> getAllEntity() {

		String METHOD_NAME = "getAllEntity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<EntityVO> entityList = entityManager.getAllEntity();
			responseObjectsMap.put("EntityList", entityList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to Get Entity Detail.";
		} catch (Throwable e) {
			msg = "Unable to Get Entity Detail.";
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

	@PostMapping("/saveEntity")
	public ResponseEntity<?> saveEntity(@Valid @RequestBody EntityVO entity) {
		System.out.println("EntityVO>>>"+entity.toString());
		String METHOD_NAME = "saveEntity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			entity = entityManager.saveEntity(entity);
			responseObjectsMap.put("EntityVO", entity);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			System.out.println("reException>>>"+re.getMessage());
			msg = "Unable to save Entity.";
		} catch (Throwable e) {
			
			msg = "Unable to save Entity.";
			System.out.println("Exception>>>"+e.getMessage());
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
	
	@PostMapping("/updateEntity")
	public ResponseEntity<?> updateEntity(@Valid @RequestBody EntityVO entity) {

		String METHOD_NAME = "updateEntity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			entity = entityManager.saveEntity(entity);
			responseObjectsMap.put("EntityVO", entity);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to update Entity.";
		} catch (Throwable e) {
			msg = "Unable to update Entity.";
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

	@PostMapping("/getEntityById")
	public ResponseEntity<?> getEntityById(@RequestParam String entityId) {

		String METHOD_NAME = "getEntityById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			EntityVO entity = entityManager.findById(entityId);
			responseObjectsMap.put("EntityVO", entity);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to get Entity Details.";
		} catch (Throwable e) {
			msg = "Unable to get Entity Details.";
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
