package com.tgi.neverstop.manager;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgi.nerverstop.common.CommonUtilities;
import com.tgi.neverstop.model.EntityVO;
import com.tgi.neverstop.repository.EntityRepository;

@Service
public class EntityManagerImpl {

	@Autowired
	EntityRepository entityRepository;
	
	CommonUtilities utilities;

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

	public EntityVO saveEntity(@Valid EntityVO entity) {

		String METHOD_NAME = "saveEntity()";
		logger.info(METHOD_NAME + "start : ");

		try {
			utilities= new CommonUtilities();
			entity.setEntity_id(utilities.generateRandomUUID());
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

	public EntityVO findById(long entityId) {

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
