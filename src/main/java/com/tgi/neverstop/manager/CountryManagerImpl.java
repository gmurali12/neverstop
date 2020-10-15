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
import com.tgi.neverstop.repository.CountryRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class CountryManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(CountryManagerImpl.class);

	@Autowired
	CountryRepository countryRepository;
	
	@Autowired
	CommonUtilities commonUtil;

	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.country.directory}")
	private String countryImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public Country saveCountry(@Valid Country country) throws BusinessException {

		String METHOD_NAME = "saveCountry()";
		logger.info(METHOD_NAME + "start : ");
		try {
			if(country.getId() ==null ){
			country.setId(CommonUtilities.generateRandomUUID());
			}
			country = countryRepository.save(country);
			
		}catch (DataIntegrityViolationException e) {
			throw new BusinessException("Country Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return country;
	}
	
	public Country saveCountryImage(String countryId, MultipartFile countryImg) throws BusinessException {

		String METHOD_NAME = "saveCountryImage()";
		logger.info(METHOD_NAME + "start : ");
		boolean isUploaded =false;
		Country country = null;
		try {

			Optional<Country> countryDetails = countryRepository.findById(countryId);
			if (countryDetails != null && countryDetails.isPresent()) {
				country = countryDetails.get();
				//country = countryRepository.save(country);
				if (countryImg != null ) {
					String urlPath=fileUrl+countryImgPath+ country.getId() + "/";
					String filePath = staticFilePath+countryImgPath + country.getId() + "/";
					isUploaded = commonUtil.writeImageFile(countryImg, filePath);
					if (isUploaded) {
							String fileName = countryImg.getOriginalFilename();
							country.setCountryImg(urlPath+fileName);
						}else{
							country.setCountryImg(fileUrl+defaultFilePath);
						}
					}else{
						country.setCountryImg(fileUrl+defaultFilePath);
					}
					
				country = countryRepository.save(country);
			}else {
				throw new BusinessException("Invalid Country id");
			}
		
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return country;
	}
	
public Country deleteCountryImage(String countryId) throws BusinessException, Throwable { 
		
		Country coutnry = null;
		Optional<Country> countryDetails = countryRepository.findById(countryId);
		String filePath = staticFilePath+countryImgPath + countryId + "/";
		if (countryDetails != null && countryDetails.isPresent()) 
		{
			coutnry = countryDetails.get();
			coutnry.setCountryImg(null);
			try{
			commonUtil.removeImageFile(filePath);
			}catch(Exception e){
				throw new BusinessException("Unable to Delete Image");
			}
			countryRepository.save(coutnry);
			
		}else{
			throw new BusinessException("Country Not Found");
		}
		return coutnry;
	}
	
	public List<Country> getAllCountry() {

		String METHOD_NAME = "getAllCountry()";
		logger.info(METHOD_NAME + "start : ");
		List<Country> countryList = null;

		try {

			countryList = countryRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return countryList;
	}

	public List<Country> getCountryByContinentId(String continentId) {
		String METHOD_NAME = "getCountryByContinentId()";
		logger.info(METHOD_NAME + "start : ");
		List<Country> countryList = null;

		try {

			countryList = countryRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return countryList;
	}

	public Country updateCountry(@Valid Country country) throws BusinessException {
		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		try {
			Optional<Country> countryDetails = countryRepository.findById(country.getId());

			if (countryDetails != null && countryDetails.isPresent()) 
			{
				Country exisitingCountry = countryDetails.get();
				country.setCountryImg(exisitingCountry.getCountryImg());
				country = countryRepository.save(country);
			}else {
				throw new BusinessException("Country Not Found");
			}

		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("Country Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			//re.printStackTrace();
			throw new BusinessException(re.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return country;
	}

	public Country findById(String countryId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		Country country = null;
		try {

			country = countryRepository.getOne(countryId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return country;
	}

	public List<Country> searchbyName(String countryName) throws BusinessException {
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<Country> contList = null;

		try {

			contList = countryRepository.searchbyName(countryName);
			if (null == contList || contList.isEmpty()) {
				throw new BusinessException("Country Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return contList;
	}
	}
