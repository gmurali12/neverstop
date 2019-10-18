package com.tgi.neverstop.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.model.EntityVO;
import com.tgi.neverstop.repository.EntityRepository;
import com.tgi.neverstop.util.CommonUtilities;
import com.tgi.neverstop.util.GoogleMapUtil;

@Service
public class EntityManagerImpl {

	@Autowired
	EntityRepository entityRepository;
	
	@Autowired
	CommonUtilities commonUtil;	
	
	@Autowired
	GoogleMapUtil googleMapUtil;
	
	@Value("${neverstop.save.images.entity}")
    private String entityImgPath;
	
	
	public static final Logger logger = LoggerFactory.getLogger(EntityManagerImpl.class);

	public List<EntityVO> getAllEntity() {

		String METHOD_NAME = "getAllEntity()";
		logger.info(METHOD_NAME + "start : ");
		List<EntityVO> entityList = null;

		try {

			entityList = entityRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return entityList;

	}

	public EntityVO saveEntity(@Valid EntityVO entity, MultipartFile entityImg, MultipartFile thumbImg) {

		String METHOD_NAME = "saveEntity()";
		logger.info(METHOD_NAME + "start : ");
		Map<String, Double> values = new HashMap<>();
		Boolean isUploaded=false;
		try {
			if(entity.getId() ==null)
			{
				entity.setId(CommonUtilities.generateRandomUUID());
			}
			String entityAddr=entity.getAddress1()+","+entity.getAddress2()+","+entity.getCity()+","+entity.getCountry();
			values= googleMapUtil.latLng(entityAddr.replace(" ", "%20"));
			if(values!=null)
			{
				entity.setLatitude(values.get("latitude").toString());
				entity.setLongitude(values.get("longitude").toString());
			}
			entity = entityRepository.save(entity);
			if(entityImg!=null || thumbImg!=null)
			{
				String filePath=entityImgPath+entity.getId()+"/";
				entity.setImagePath(filePath);
				if(entityImg!=null)
				{
					isUploaded=commonUtil.writeImageFile(entityImg,filePath);
					if(isUploaded)
					{
						entity.setProfileImage(entityImg.getOriginalFilename());
					}
				}
				if(thumbImg!=null)
				{
					isUploaded=commonUtil.writeImageFile(thumbImg,filePath);
					if(isUploaded)
					{
						entity.setThumbImage(thumbImg.getOriginalFilename());
					}
				}
				entity = entityRepository.save(entity);
			}
			
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return entity;

	}
	public EntityVO updateEntity(@Valid EntityVO entity, MultipartFile entityImg, MultipartFile thumbImg) {

		String METHOD_NAME = "saveEntity()";
		logger.info(METHOD_NAME + "start : ");
		Boolean isUploaded=false;
		try {
			
			if(entityImg!=null)
			{
				String filePath=entityImgPath+entity.getId()+"/";
				isUploaded=commonUtil.writeImageFile(entityImg,filePath);
				if(isUploaded)
				{
					entity.setImagePath(filePath+entityImg.getOriginalFilename());
				}
			}
			if(thumbImg!=null)
			{
				String filePath=entityImgPath+entity.getId()+"/";
				isUploaded=commonUtil.writeImageFile(thumbImg,filePath);
				if(isUploaded)
				{
					entity.setThumbImage(filePath+entityImg.getOriginalFilename());
				}
			}
			entity = entityRepository.save(entity);	
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return entity;

	}
	public EntityVO findById(String entityId) {

		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		EntityVO entity = null;
		try {

			entity = entityRepository.getOne(entityId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return entity;

	}

}
