package com.tgi.nerverstop.common;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.uuid.Generators;

public class CommonUtilities {

	public static final Logger logger = LoggerFactory.getLogger(CommonUtilities.class);

	public String generateRandomUUID() {
		String METHOD_NAME = "generateRandomUUID";
		logger.info(METHOD_NAME + "start : ");
		UUID uuid = Generators.randomBasedGenerator().generate();
		logger.info(METHOD_NAME + "End: ");
		return uuid.toString();
	}
}
