package com.tgi.neverstop.manager;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.State;
import com.tgi.neverstop.repository.StateRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class StateManagerImpl {

	public static final Logger logger = LoggerFactory
			.getLogger(StateManagerImpl.class);

	@Autowired
	StateRepository stateRepository;

	public State saveState(State state) throws NeverStopExcpetion {

		String METHOD_NAME = "saveState()";
		logger.info(METHOD_NAME + "start : ");

		try {

			if(state.getId() ==null ){
				state.setId(CommonUtilities.generateRandomUUID());
			}
			state = stateRepository.save(state);
		}catch (DataIntegrityViolationException e) {
			throw new NeverStopExcpetion("State Name Already Exist");
		    
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			//re.printStackTrace();
			throw new NeverStopExcpetion(re.getMessage());

		} catch (Throwable e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
			throw new NeverStopExcpetion(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
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

			stateList = stateRepository.findByCountryId(countryId);

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

	public @Valid State updateState(@Valid State state) {
		String METHOD_NAME = "updateState()";
		logger.info(METHOD_NAME + "start : ");

		try {

			state = stateRepository.save(state);

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

	public List<State> searchbyName(String stateName) throws NeverStopExcpetion {
		String METHOD_NAME = "searchbyName()";
		logger.info(METHOD_NAME + "start : ");
		List<State> stateList = null;

		try {

			stateList = stateRepository.searchbyName(stateName);
			if (null == stateList || stateList.isEmpty()) {
				throw new NeverStopExcpetion("State Not Found");
			}

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} 
		logger.info(METHOD_NAME + "END");
		return stateList;
	}
}
