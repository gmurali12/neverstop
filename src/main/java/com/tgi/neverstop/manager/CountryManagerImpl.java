package com.tgi.neverstop.manager;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgi.nerverstop.util.CommonUtilities;
import com.tgi.neverstop.model.Country;
import com.tgi.neverstop.repository.CountryRepository;

@Service
public class CountryManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(CountryManagerImpl.class);

	@Autowired
	CountryRepository countryRepository;

	public Country saveCountry(Country country) {

		String METHOD_NAME = "saveContinent()";
		logger.info(METHOD_NAME + "start : ");

		try {

			if(country.getId() !=null ){
				country.setId(CommonUtilities.generateRandomUUID());
			}
			country = countryRepository.save(country);

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

			countryList = countryRepository.findByContinentId(continentId);

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

	public @Valid Country updateCountry(@Valid Country country) {
		String METHOD_NAME = "updateCountry()";
		logger.info(METHOD_NAME + "start : ");

		try {

			country = countryRepository.save(country);

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
}