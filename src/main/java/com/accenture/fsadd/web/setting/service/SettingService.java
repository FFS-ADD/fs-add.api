package com.accenture.fsadd.web.setting.service;

import java.util.List;

import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.web.author.form.WebDashboardLoginForm;

public interface SettingService {
	
	List<User> findAllValidUsers();
}
