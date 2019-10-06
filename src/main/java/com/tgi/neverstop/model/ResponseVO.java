package com.tgi.neverstop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static final String Ok = "Ok";

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static final String Error = "Error";
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ResponseErrors> errors;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String statusFlag;

	private boolean status;


	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Map<String, Object> paramObjectsMap = new HashMap<String, Object>();

	
	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	public Map<String, Object> getParamObjectsMap() {
		return paramObjectsMap;
	}

	public void setParamObjectsMap(Map<String, Object> paramObjectsMap) {
		this.paramObjectsMap = paramObjectsMap;
	}
	
	
	public final void addObject(final Object obj, final String key) {
		paramObjectsMap.put(key, obj);
	}

	public final Object getObject(final String key) {
		return this.paramObjectsMap.get(key);
	}

	public void addAllObject(Map<String, Object> mapObj) {
		this.paramObjectsMap.putAll(mapObj);
	}

	public final void clearParamMap(final String key) {
		paramObjectsMap.remove(key);
	}

	public final void setErrors(final List<ResponseErrors> errors) {
		this.errors = errors;
	}

	public final List<ResponseErrors> getErrors() {
		if (errors == null) {
			errors = new ArrayList<ResponseErrors>();
		}

		return errors;
	}	

}
