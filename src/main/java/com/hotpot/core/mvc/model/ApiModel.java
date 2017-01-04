package com.hotpot.core.mvc.model;

public class ApiModel<T> {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public ApiModel(){
		
	}
	
	public ApiModel(T data){
		this.data = data;
		
	}
	
	

}
