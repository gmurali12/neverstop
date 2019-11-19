package com.tgi.neverstop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.tgi.neverstop.manager.EntityManagerImpl;
import com.tgi.neverstop.model.EntityVO;
import com.tgi.neverstop.model.ResponseVO;
import com.tgi.neverstop.util.CommonUtilities;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/entity")
public class EntityController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(EntityController.class);

	@Autowired
	EntityManagerImpl entityManager;

	@Autowired
	CommonUtilities commonUtil;

	@Value("${neverstop.geoJson.format}")
	private String fileFormat;

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
	public ResponseEntity<?> saveEntity(
			@RequestPart EntityVO entity,
			@RequestPart(value = "entityImg", required = false) MultipartFile entityImg,
			@RequestPart(value = "thumbImg", required = false) MultipartFile thumbImg) {

		String METHOD_NAME = "saveEntity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			entity = entityManager.saveEntity(entity, entityImg, thumbImg);
			responseObjectsMap.put("EntityVO", entity);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			System.out.println("reException>>>" + re.getMessage());
			msg = "Unable to save Entity.";
		} catch (Throwable e) {

			msg = "Unable to save Entity.";
			System.out.println("Exception>>>" + e.getMessage());
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
	public ResponseEntity<?> updateEntity(
			@RequestPart EntityVO entity,
			@RequestPart(value = "entityImg", required = false) MultipartFile entityImg,
			@RequestPart(value = "thumbImg", required = false) MultipartFile thumbImg) {

		String METHOD_NAME = "updateEntity()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			entity = entityManager.updateEntity(entity, entityImg, thumbImg);
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
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}
	}

	@PostMapping("/getEntityByCityId")
	public ResponseEntity<?> getEntity(@RequestParam String cityId)
			throws Exception {
		String METHOD_NAME = "getEntityByCityId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		try {
			List<EntityVO> entityList = entityManager.getByCityId(cityId);
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

	@PostMapping("/downloadEntityById")
	public ResponseEntity<?> downloadEntityById(@RequestParam String entityId) {

		String METHOD_NAME = "downloadEntityById()";
		logger.info(METHOD_NAME + "start : ");

		String msg;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		JSONObject geoJson = null;

		try {
			EntityVO entity = entityManager.findById(entityId);
			if (entity != null) {
				geoJson = commonUtil.generateGeoJSON(entity);
				byte[] isr = geoJson.toString().getBytes();
				String fileName = entity.getCity() + "_entity" + fileFormat;
				HttpHeaders respHeaders = new HttpHeaders();
				respHeaders.setContentLength(isr.length);
				respHeaders.setContentType(new MediaType("text", "json"));
				respHeaders
						.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				respHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=" + fileName);
				return new ResponseEntity<byte[]>(isr, respHeaders,
						HttpStatus.OK);
			}
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to get Entity Details.";
		} catch (Throwable e) {
			msg = "Unable to get Entity Details.";
			logger.error(e.getMessage());
		}
		logger.info(METHOD_NAME + "END");
		responseVO = createServiceResponseError(responseObjectsMap,
				"Unable to get Entity Details.");
		return ResponseEntity.ok().body(responseVO);
	}

	@PostMapping("/downloadEntityByCityId")
	public ResponseEntity<?> downloadEntityByCityId(@RequestParam String cityId)
			throws Exception {
		String METHOD_NAME = "downloadEntityByCityId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();
		try {
			String filePath = entityManager.downloadByCityId(cityId);
			if(filePath!=null){
				
				responseObjectsMap.put("FilePath", filePath);
			}else{
				msg="Unable to Get Entity Detail.";	
			}
			
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to Get Entity Detail.";
		} catch (Throwable e) {
			msg = "Unable to Get Entity Detail.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
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
