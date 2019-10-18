package com.tgi.neverstop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.neverstop.manager.ReviewManagerImpl;
import com.tgi.neverstop.model.Review;
import com.tgi.neverstop.model.ResponseVO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("review")
public class ReviewController extends BaseController {

	public static final Logger logger = LoggerFactory
			.getLogger(ReviewController.class);

	@Autowired
	ReviewManagerImpl reviewManager;


	@PostMapping("/getReviewByEntityId")
	public ResponseEntity<?> getReviewByEntityId(@RequestParam String entityId) {

		String METHOD_NAME = "getReviewByEntityId()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			List<Review> reviewList = reviewManager.getReviewByEntityId(entityId);
			
			responseObjectsMap.put("ReviewList", reviewList);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to Get Review.";
		} catch (Throwable e) {
			msg = "Unable to Get Review.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	@PostMapping("/saveReview")
	public ResponseEntity<?> saveReview(@Valid @RequestBody Review review) {
		String METHOD_NAME = "saveReview()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			review = reviewManager.saveReview(review);
			responseObjectsMap.put("Review", review);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			System.out.println("reException>>>"+re.getMessage());
			msg = "Unable to save Entity.";
		} catch (Throwable e) {
			
			msg = "Unable to save Entity.";
			System.out.println("Exception>>>"+e.getMessage());
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}
	
	@PostMapping("/updateReview")
	public ResponseEntity<?> updateReview(@Valid @RequestBody Review review) {

		String METHOD_NAME = "updateReview()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			review = reviewManager.saveReview(review);
			responseObjectsMap.put("ReviewVO", review);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to update Review.";
		} catch (Throwable e) {
			msg = "Unable to update Review.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}

	}

	@PostMapping("/getReviewById")
	public ResponseEntity<?> getReviewById(@RequestParam String reviewId) {

		String METHOD_NAME = "getReviewById()";
		logger.info(METHOD_NAME + "start : ");

		String msg = null;
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = new ResponseVO();

		try {
			Review review = reviewManager.findById(reviewId);
			responseObjectsMap.put("ReviewVO", review);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			msg = "Unable to get review Details.";
		} catch (Throwable e) {
			msg = "Unable to get review Details.";
			logger.error(e.getMessage());
		}

		logger.info(METHOD_NAME + "END");
		if (null == msg) {
			responseVO = createServiceResponse(responseObjectsMap);
			return ResponseEntity.ok().body(responseVO);
		} else {
			responseVO = createServiceResponseError(responseObjectsMap, msg);
			return ResponseEntity.ok().body(responseVO);
		}
	}
}
