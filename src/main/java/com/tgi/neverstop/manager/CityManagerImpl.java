package com.tgi.neverstop.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.City;
import com.tgi.neverstop.repository.CityRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class CityManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(CityManagerImpl.class);

	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	CommonUtilities commonUtil;
	
	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.city.directory}")
	private String cityImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public City saveCity(City city, MultipartFile cityImg) throws NeverStopExcpetion {

		String METHOD_NAME = "saveCity()";
		logger.info(METHOD_NAME + "start : ");
		boolean isUploaded=false;
		try {

			if(city.getId() ==null ){
				city.setId(CommonUtilities.generateRandomUUID());
			}
			city = cityRepository.save(city);
			if (cityImg != null ) {
				String urlPath=fileUrl+cityImgPath+ city.getId() + "/";
				String filePath = staticFilePath+cityImgPath + city.getId() + "/";
				isUploaded = commonUtil.writeImageFile(cityImg, filePath);
				if (isUploaded) {
						String fileName = cityImg.getOriginalFilename();
						city.setCityImg(urlPath+fileName);
					}else{
						city.setCityImg(fileUrl+defaultFilePath);
					}
				}else{
					city.setCityImg(fileUrl+defaultFilePath);
				}
				
			city = cityRepository.save(city);
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("City Name Already Exist");
		    
		
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return city;
	}

	public List<City> getAllCity() {

		String METHOD_NAME = "getAllCity()";
		logger.info(METHOD_NAME + "start : ");
		List<City> cityList = null;

		try {

			cityList = cityRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return cityList;
	}
	
	public List<City> getCityByStateId(String stateId) {
		String METHOD_NAME = "getCityByCountryId()";
		logger.info(METHOD_NAME + "start : ");
		List<City> cityList= null;

		try {

			cityList = cityRepository.findByStateId(stateId);
			
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return cityList;
	}

	public City findById(String cityId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		City city = null;
		try {

			city = cityRepository.getOne(cityId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return city;
	}

	public List<City> searchbyName(String cityName) throws NeverStopExcpetion {
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<City> cityList = null;

		try {

			cityList = cityRepository.searchbyName(cityName);
			if (null == cityList || cityList.isEmpty()) {
				throw new NeverStopExcpetion("City Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return cityList;
	}
}
