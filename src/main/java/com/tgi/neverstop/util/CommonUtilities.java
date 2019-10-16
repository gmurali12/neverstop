package com.tgi.neverstop.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.fasterxml.uuid.Generators;

@Component
public class CommonUtilities {

	public static final Logger logger = LoggerFactory
			.getLogger(CommonUtilities.class);
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${neverstop.app.mail.forgetMailSubject}")
    private String mailSubject;
	
	public static String generateRandomUUID() {
		String METHOD_NAME = "generateRandomUUID";
		logger.info(METHOD_NAME + "start : ");
		UUID uuid = Generators.randomBasedGenerator().generate();
		logger.info(METHOD_NAME + "End: ");
		return uuid.toString();
	}


	public static Date getCurrentDateTime() {

		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		return date;
	}
	
	public void generateforgetPwdMail(String mailId, String pwd) 
	{
		try {
			System.out.println("Mail Subject>>"+mailSubject);
		    SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo(mailId);
	        msg.setSubject(mailSubject+"-Forget Password");
	        msg.setText("Password for the Account: "+pwd);
	        javaMailSender.send(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
