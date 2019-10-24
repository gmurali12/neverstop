package com.tgi.neverstop.manager;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgi.neverstop.model.Review;
import com.tgi.neverstop.repository.ReviewRepository;
import com.tgi.neverstop.util.CommonUtilities;

@Service
public class ReviewManagerImpl {

	@Autowired
	ReviewRepository reviewRepository;

	CommonUtilities utilities;

	public static final Logger logger = LoggerFactory.getLogger(ReviewManagerImpl.class);
	
	public Review saveReview(@Valid Review review) {

		String METHOD_NAME = "saveReview()";
		logger.info(METHOD_NAME + "start : ");

		try {
			if (review.getId() == null) {
				review.setId(CommonUtilities.generateRandomUUID());
			}
			review.setPostedOn(CommonUtilities.getCurrentDateTime());
			System.out.println("review:"+review.getComments());
			System.out.println("EntityId:"+review.getEntityId());

			System.out.println("Like:"+review.getIsLike());

			System.out.println("Rating:"+review.getRating());

			System.out.println("Userid:"+review.getUserId());
			System.out.println("Date:"+review.getPostedOn());

			review = reviewRepository.save(review);
		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return review;

	}

	public Review findById(String reviewId) {

		String METHOD_NAME = "findById()";
		logger.info(METHOD_NAME + "start : ");

		Review review = null;
		try {

			review = reviewRepository.getOne(reviewId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return review;

	}

	public List<Review> getReviewByEntityId(String entityId) {

		String METHOD_NAME = "getReviewByEntityId()";
		logger.info(METHOD_NAME + "start : ");
		List<Review> reviewList = null;

		try {

			reviewList = reviewRepository.findByEntityId(entityId);

		} catch (RuntimeException re) {
			logger.error(re.getMessage());
			re.printStackTrace();

		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage());

		}
		logger.info(METHOD_NAME + "END");
		return reviewList;
	}

}
