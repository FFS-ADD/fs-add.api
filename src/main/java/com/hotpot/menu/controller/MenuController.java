package com.hotpot.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.hotpot.menu.controller.model.MenuModel;

@RestController
@RequestMapping("/api")
public class MenuController {
	
	@RequestMapping("/menu")
	public ApiModel<List<MenuModel>> getAllMenuAction(){
		
		List<MenuModel> result = new ArrayList<>();
		MenuModel menuModel1 = new MenuModel();
		menuModel1.setName("Test1");
		
		result.add(menuModel1);
		
		 menuModel1 = new MenuModel();
		menuModel1.setName("Test1");
		
		result.add(menuModel1);
		
		return new ApiModel<>(result);
		
		
		
	}

}
