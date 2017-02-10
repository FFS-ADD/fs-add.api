package com.hotpot.core.mvc.model;

import com.accenture.fsadd.common.mvc.model.CommonModel;

public class ApiModel<T>  extends CommonModel {

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
