package com.tgi.neverstop.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tgi.neverstop.controller.BaseController;
import com.tgi.neverstop.model.ResponseVO;


@SuppressWarnings({ "unchecked", "rawtypes" })
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<Object>
	 * handleAllExceptions(Exception ex, WebRequest request) { CustomException
	 * exceptionResponse = new CustomException(Constant.SERVER_ERROR, "500"); return
	 * new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<Object> handleAllExceptions(BusinessException ex) {
		CustomException exceptionResponse = new CustomException(ex.getErrorcode(), ex.getErrorMessage());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		List<String> value = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		List<String> key = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getField())
				.collect(Collectors.toList());
		body.put("key", key);
		body.put("errors", value);

		return new ResponseEntity<>(body, headers, status);

	}
	
	  @ExceptionHandler(value = Exception.class)  
	    public ResponseEntity<?> handleException(Exception e){
		  Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		  ResponseVO responseVO = new ResponseVO();
		  BaseController baseCtrl = new BaseController();
		  responseVO = baseCtrl.createServiceResponseError(responseObjectsMap, e.getMessage());
		  return ResponseEntity.ok().body(responseVO);
		  }  

}
