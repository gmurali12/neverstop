package com.tgi.neverstop.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.fasterxml.uuid.Generators;
import com.tgi.neverstop.exception.NeverStopExcpetion;
import com.tgi.neverstop.model.EntityVO;

@Component
public class CommonUtilities {

	public static final Logger logger = LoggerFactory
			.getLogger(CommonUtilities.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${neverstop.app.mail.forgetMailSubject}")
	private String mailSubject;
	
	@Value("${neverstop.static.filepath}")
	private String staticFilePath;

	@Value("${neverstop.geojson.directory}")
	private String geojsonFile;

	@Value("${neverstop.geoJson.format}")
	private String fileFormat;
	
	@Value("${neverstop.static.url}")
	private String downloadFileUrl;

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine templateEngine;

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

	public boolean writeImageFile(MultipartFile file, String filePath)
			throws NeverStopExcpetion, IOException {
		BufferedOutputStream stream = null;
		boolean isUploaded = false;
		String fileName = file.getOriginalFilename();

		try {
			File directory = new File(filePath);
			if (!directory.exists()) {
				directory.mkdir();
				// If you require it to make the entire directory path including
				// parents,
				// use directory.mkdirs(); here instead.
			}
			File newFile = new File(filePath + fileName);
			byte[] bytes = file.getBytes();
			stream = new BufferedOutputStream(new FileOutputStream(newFile));
			stream.write(bytes);
			stream.flush();
			isUploaded = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NeverStopExcpetion("Failed to upload Image");
		} finally {
			stream.close();
		}
		return isUploaded;
	}

	public JSONObject generateGeoJSON(EntityVO entity) {

		JSONObject featureCollection = new JSONObject();
		try {
			featureCollection.put("type", "FeatureCollection");
			JSONObject properties = new JSONObject();
			properties.put("name", entity.getEntityName());
			properties.put("hours", "6am - 5pm");
			properties.put("phone", entity.getPhone());
			properties.put("description", entity.getDescription());
			properties.put("rating", entity.getRatingCount());
			JSONArray features = new JSONArray();

			JSONObject feature = new JSONObject();
			feature.put("type", "Feature");
			feature.put("properties", properties);
			JSONObject geometry = new JSONObject();
			JSONArray JSONArrayCoord = new JSONArray("[" + entity.getLatitude()
					+ "," + entity.getLongitude() + "]");

			geometry.put("type", "Point");
			geometry.put("coordinates", JSONArrayCoord);

			feature.put("geometry", geometry);
			features.put(feature);
			featureCollection.put("features", features);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return featureCollection;
	}

	public JSONObject generateGeoJSONList(List<EntityVO> entityList)
			throws JSONException {
		JSONObject featureCollection = new JSONObject();
		featureCollection.put("type", "FeatureCollection");
		JSONArray features = new JSONArray();
		try {
			for (EntityVO entity : entityList) {
				System.out.println("Entity Name::" + entity.getEntityName());

				JSONObject properties = new JSONObject();
				properties.put("name", entity.getEntityName());
				properties.put("hours", entity.getHours());
				properties.put("phone", entity.getPhone());
				properties.put("description", entity.getDescription());

				JSONObject geometry = new JSONObject();
				JSONArray JSONArrayCoord = new JSONArray("["
						+ entity.getLatitude() + "," + entity.getLongitude()
						+ "]");

				geometry.put("type", "Point");
				geometry.put("coordinates", JSONArrayCoord);

				JSONObject feature = new JSONObject();
				feature.put("type", "Feature");
				feature.put("properties", properties);
				feature.put("geometry", geometry);
				features.put(feature);
			}
			featureCollection.put("features", features);
			System.out.println(featureCollection.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return featureCollection;
	}

	public void sendSimpleMessage(String mailId, String pwd)
			throws MessagingException, IOException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		// helper.addAttachment("logo.png", new
		// ClassPathResource("memorynotfound-logo.png"));

		Context context = new Context();
		// context.setVariables(mail.getModel());
		context.setVariable("Password", pwd);
		String html = templateEngine.process("template.html", context);

		helper.setTo(mailId);
		helper.setText(html, true);
		helper.setSubject(mailSubject);
		emailSender.send(message);
	}

	public String downloadGeoJSONList(String cityId, List<EntityVO> entityList)
			throws JSONException, NeverStopExcpetion {
		JSONObject featureCollection = new JSONObject();
		featureCollection.put("type", "FeatureCollection");
		JSONArray features = new JSONArray();
		String geoFilePath = geojsonFile+cityId+"/";
		String filePath = geoFilePath+cityId+"_entity"
				+ fileFormat;
		System.out.println("File Path>>>"+filePath);
		String downloadFilePath=null;
		try {
			for (EntityVO entity : entityList) {
				System.out.println("Entity Name::" + entity.getEntityName());

				JSONObject properties = new JSONObject();
				properties.put("name", entity.getEntityName());
				properties.put("hours", entity.getHours());
				properties.put("phone", entity.getPhone());
				properties.put("description", entity.getDescription());

				JSONObject geometry = new JSONObject();
				JSONArray JSONArrayCoord = new JSONArray("["
						+ entity.getLatitude() + "," + entity.getLongitude()
						+ "]");

				geometry.put("type", "Point");
				geometry.put("coordinates", JSONArrayCoord);
				JSONObject feature = new JSONObject();
				feature.put("type", "Feature");
				feature.put("properties", properties);
				feature.put("geometry", geometry);
				features.put(feature);
			}
			featureCollection.put("features", features);
			System.out.println(featureCollection.toString());
			
			File directory = new File(staticFilePath+geoFilePath);
			if (!directory.exists()) {
				directory.mkdir();

			} else {
				FileUtils.cleanDirectory(directory);
			}
			FileWriter fw = null;
			try {
				System.out.println("File Path....>>>"+staticFilePath+filePath);
				fw = new FileWriter(staticFilePath+filePath);
				fw.write(featureCollection.toString());
				downloadFilePath=downloadFileUrl+filePath;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				fw.close();
			}
			return downloadFilePath;
		} catch (Exception e) {
			e.printStackTrace();
			throw new NeverStopExcpetion("Unable to Write File");
		}
		
	}
}
