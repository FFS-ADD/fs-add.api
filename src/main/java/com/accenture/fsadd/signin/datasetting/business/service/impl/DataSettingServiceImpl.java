package com.accenture.fsadd.signin.datasetting.business.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.fsadd.signin.datasetting.business.entity.DataSettingEntity;
import com.accenture.fsadd.signin.datasetting.business.repository.DataSettingRepository;
import com.accenture.fsadd.signin.datasetting.business.service.DataSettingService;
import com.accenture.fsadd.signin.datasetting.controller.model.DataSettingEntryModel;

@Service
public class DataSettingServiceImpl implements DataSettingService {

	@Autowired
	private DataSettingRepository dataSettingRepository;
	
	@Override
	public DataSettingEntity getData(String email) {
		return dataSettingRepository.findSettingData(email);
	}
	
	@Override
	public void saveData(DataSettingEntryModel data) {
		DataSettingEntity entity = new DataSettingEntity();
		BeanUtils.copyProperties(data, entity);
		dataSettingRepository.save(entity);
	}
}
