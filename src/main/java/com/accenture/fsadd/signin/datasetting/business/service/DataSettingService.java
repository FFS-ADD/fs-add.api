package com.accenture.fsadd.signin.datasetting.business.service;

import com.accenture.fsadd.signin.datasetting.business.entity.DataSettingEntity;
import com.accenture.fsadd.signin.datasetting.controller.model.DataSettingEntryModel;

/**
 * 
 * DataSetting Service
 *
 */
public interface DataSettingService {

	/**
	 * save data setting data
	 * @param data
	 */
	void saveData(DataSettingEntryModel data);

	/**
	 * Get data setting data
	 * 
	 * @param email
	 * @return data setting data 
	 */
	DataSettingEntity getData(String email);

}
