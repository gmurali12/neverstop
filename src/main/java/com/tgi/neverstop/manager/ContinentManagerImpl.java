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
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.repository.ContinentRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class ContinentManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(ContinentManagerImpl.class);

	@Autowired
	ContinentRepository continentRepository;
	

	@Autowired
	CommonUtilities commonUtil;

	
	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.images.continent.directory}")
	private String continentImgPath;
	
	@Value("${neverstop.static.url}")
	private String fileUrl;
	
	@Value("${neverstop.defaultimages.directory}")
	private String defaultFilePath;

	public Continent saveContinent(Continent continent, MultipartFile continentImg) throws NeverStopExcpetion {

		String METHOD_NAME = "saveContinent()";
		logger.info(METHOD_NAME + "start : ");
		boolean isUploaded =false;
		try {

			if(continent.getId() ==null ){
				continent.setId(CommonUtilities.generateRandomUUID());
			}
			continent = continentRepository.save(continent);
			if (continentImg != null ) {
				String urlPath=fileUrl+continentImgPath+ continent.getId() + "/";
				String filePath = staticFilePath+continentImgPath + continent.getId() + "/";
				isUploaded = commonUtil.writeImageFile(continentImg, filePath);
				if (isUploaded) {
						String fileName = continentImg.getOriginalFilename();
						continent.setContinentImg(urlPath+fileName);
					}else{
						continent.setContinentImg(fileUrl+defaultFilePath);
					}
				}else{
					continent.setContinentImg(fileUrl+defaultFilePath);
				}
				
			continent = continentRepository.save(continent);
			
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("Continent Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return continent;
	}

	public List<Continent> getAllContinent() {

		String METHOD_NAME = "getAllContinent()";
		logger.info(METHOD_NAME + "start : ");
		List<Continent> contList = null;

		try {

			contList = continentRepository.findAll();

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return contList;
	}

	public Continent findById(String continentId) {
		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		Continent continent = null;
		try {

			continent = continentRepository.getOne(continentId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return continent;
	}

	public List<Continent> searchbyName(String continentName) throws NeverStopExcpetion 
	{
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<Continent> contList = null;

		try {

			contList = continentRepository.searchbyName(continentName);
			if (null == contList || contList.isEmpty()) {
				throw new NeverStopExcpetion("Continent Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return contList;
	}
}
