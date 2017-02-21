package com.accenture.fsadd.common.mvc.model;

public class ApiModel<T> extends CommonModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Response Data
	 */
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ApiModel() {

	}

	public ApiModel(T data) {
		this.data = data;

	}

}
