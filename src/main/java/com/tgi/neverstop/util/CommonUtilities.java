package com.tgi.neverstop.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.uuid.Generators;
import com.tgi.neverstop.exception.NeverStopExcpetion;

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

	public void generateforgetPwdMail(String mailId, String pwd) {
		try {
			System.out.println("Mail Subject>>" + mailSubject);
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(mailId);
			msg.setSubject(mailSubject + "-Forget Password");
			msg.setText("Password for the Account: " + pwd);
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public char[] geek_Password(int len) {
		logger.info("Generating password using random() : ");
		logger.info("Your new password is : ");

		// A strong password has Cap_chars, Lower_chars,
		// numeric value and symbols. So we are using all of
		// them to generate our password
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		// String symbols = "!@$%*";

		String values = Capital_chars + Small_chars + numbers;

		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		return password;
	}

	@SuppressWarnings("finally")
	public boolean writeImageFile(MultipartFile file, String filePath)
			throws NeverStopExcpetion {
		BufferedOutputStream stream = null; 
		Boolean isUploaded=false;
		String fileName=file.getOriginalFilename();
		 
		try {
			File directory = new File(filePath);
		    if (! directory.exists())
		    {
		        directory.mkdir();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		    }
		    File newFile = new File(filePath+fileName);
			byte[] bytes = file.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(newFile));
			stream.write(bytes);
			stream.flush();
			isUploaded=true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NeverStopExcpetion("Failed to upload Image");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new NeverStopExcpetion("File Close method fails");
				}
			}
			return isUploaded;
		}
	}
}
