package com.tgi.neverstop.manager;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.exception.BusinessException;
import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.model.Country;
import com.tgi.neverstop.model.State;
import com.tgi.neverstop.repository.StateRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class StateManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(StateManagerImpl.class);

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CommonUtilities commonUtil;

	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.state.directory}")
	private String stateImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public State saveState(@Valid State state) throws BusinessException {

		String METHOD_NAME = "saveState()";
		logger.info(METHOD_NAME + "start : ");
		boolean isUploaded = false;
		try {

			if(state.getId() ==null ){
				state.setId(CommonUtilities.generateRandomUUID());
			}
			state = stateRepository.save(state);
			
		}catch (DataIntegrityViolationException e) {
			throw new BusinessException("State Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			//re.printStackTrace();
			throw new BusinessException(re.getMessage());

		} catch (Throwable e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return state;
	}
	
	public State saveStateImage(String stateId, MultipartFile stateImg) throws BusinessException {

		String METHOD_NAME = "saveStateImage()";
		logger.info(METHOD_NAME + "start : ");
		boolean isUploaded =false;
		State state = null;
		try {

			Optional<State> stateDetails = stateRepository.findById(stateId);
			if (stateDetails != null && stateDetails.isPresent()) {
				state = stateDetails.get();
				//country = countryRepository.save(country);
				if (stateImg != null ) {
					String urlPath=fileUrl+stateImgPath+ state.getId() + "/";
					String filePath = staticFilePath+stateImgPath + state.getId() + "/";
					isUploaded = commonUtil.writeImageFile(stateImg, filePath);
					if (isUploaded) {
							String fileName = stateImg.getOriginalFilename();
							state.setStateImg(urlPath+fileName);
						}else{
							state.setStateImg(fileUrl+defaultFilePath);
						}
					}else{
						state.setStateImg(fileUrl+defaultFilePath);
					}
					
				state = stateRepository.save(state);
			}else {
				throw new BusinessException("Invalid State id");
			}
		
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return state;
	}
	
public State deleteStateImage(String stateId) throws BusinessException, NeverStopExcpetion { 
		
		State state = null;
		Optional<State> stateDetails = stateRepository.findById(stateId);
		String filePath = staticFilePath+stateImgPath + stateId + "/";
		if (stateDetails != null && stateDetails.isPresent()) 
		{
			state = stateDetails.get();
			state.setStateImg(null);
			try{
			commonUtil.removeImageFile(filePath);
			}catch(Exception e){
				throw new BusinessException("Unable to Delete Image");
			}
			stateRepository.save(state);
			
		}else{
			throw new BusinessException("State Not Found");
		}
		return state;
	}
	
	public List<State> getAllState() {

		String METHOD_NAME = "getAllState()";
		logger.info(METHOD_NAME + "start : ");
		List<State> stateList = null;

		try {

			stateList = stateRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return stateList;
	}

	public List<State> getStateByCountryId(String countryId) {
		String METHOD_NAME = "getStateByContinentId()";
		logger.info(METHOD_NAME + "start : ");
		List<State> stateList = null;

		try {

			stateList = stateRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return stateList;
	}

	public  State updateState(@Valid State state) {
		String METHOD_NAME = "updateState()";
		logger.info(METHOD_NAME + "start : ");

		try {
			Optional<State> stateDetails = stateRepository.findById(state.getId());

		if (stateDetails != null && stateDetails.isPresent()) 
		{
			State exisitingState = stateDetails.get();
			state.setStateImg(exisitingState.getStateImg());
			state = stateRepository.save(state);
		}else {
			throw new BusinessException("State"
					+ " Not Found");
		}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return state;
	}

	public State findById(String stateId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		State state = null;
		try {

			state = stateRepository.getOne(stateId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return state;
	}

	public List<State> searchbyName(String stateName) throws BusinessException {
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<State> stateList = null;

		try {

			stateList = stateRepository.searchbyName(stateName);
			if (null == stateList || stateList.isEmpty()) {
				throw new BusinessException("State Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return stateList;
	}
	
}
