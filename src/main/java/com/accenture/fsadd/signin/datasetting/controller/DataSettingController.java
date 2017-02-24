package com.accenture.fsadd.signin.datasetting.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.signin.datasetting.business.entity.DataSettingEntity;
import com.accenture.fsadd.signin.datasetting.business.service.DataSettingService;
import com.accenture.fsadd.signin.datasetting.controller.model.DataSettingEntryModel;

@RestController
@RequestMapping("/datasetting")
public class DataSettingController {

	@Autowired
	private DataSettingService dataSettingService;

	@RequestMapping("/save")
	public ApiModel<String> save(@RequestBody DataSettingEntryModel model) {
		dataSettingService.saveData(model);
		return new ApiModel<>("");
	}

	@RequestMapping("/get")
	public ApiModel<DataSettingEntryModel> getData(@RequestParam(value = "email") String email) {
		DataSettingEntity result = dataSettingService.getData(email);
		DataSettingEntryModel retModel = new DataSettingEntryModel();
		BeanUtils.copyProperties(result, retModel);
		return new ApiModel<>(retModel);
	}
}
