package com.tgi.neverstop.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgi.nerverstop.util.CommonUtilities;
import com.tgi.neverstop.model.Continent;
import com.tgi.neverstop.model.Country;
import com.tgi.neverstop.repository.ContinentRepository;

@Service
public class ContinentManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(ContinentManagerImpl.class);

	@Autowired
	ContinentRepository continentRepository;

	public Continent saveContinent(Continent continent) {

		String METHOD_NAME = "saveContinent()";
		logger.info(METHOD_NAME + "start : ");

		try {

			if(continent.getId() !=null ){
				continent.setId(CommonUtilities.generateRandomUUID());
			}
			continent = continentRepository.save(continent);

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
}
