package com.accenture.fsadd.web.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.accenture.fsadd.common.APIExecutedStatusType;
import com.accenture.fsadd.common.mvc.model.ApiModel;
import com.accenture.fsadd.user.entity.User;
import com.accenture.fsadd.user.service.UserService;
import com.accenture.fsadd.web.management.controller.model.PhotoModel;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/webmanagement")
public class ManagementDashboardController {

	@Autowired
	private UserService userService;
	
	@Value("${fsadd.sonarqube.project}")
	private String projectKey;

   /**
     * get qualityDate data from sonar dashboard
     * @param form
     * @return ApiModel<QualityGateModel>
     */
	@RequestMapping(value = "/getUserListInfo", method = RequestMethod.GET)
	public ApiModel<List<User>> getUserListInfo(){
		List<User> entity = userService.findAllValidUsers();
		ApiModel<List<User>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ApiModel<List<User>> createUser(@RequestBody User createUser){
	    createUser.setId(null);
		userService.registerUser(createUser);
		List<User> entity = userService.findAllValidUsers();
		ApiModel<List<User>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ApiModel<List<User>> updateUser(@RequestBody User createUser){
		userService.saveUser(createUser);
		List<User> entity = userService.findAllValidUsers();
		ApiModel<List<User>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiModel<List<User>> deleteUser(@RequestBody User createUser){
		userService.deleteUser(createUser);
		List<User> entity = userService.findAllValidUsers();
		ApiModel<List<User>> apiMdole = new ApiModel<>(entity); 
		apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
		return apiMdole;
	}

    @RequestMapping(value = "/photoupload", headers = "Content-Type= multipart/form-data", method = RequestMethod.POST)
    public ApiModel<PhotoModel> fileUpload(@RequestParam("name") String name,
            @RequestParam(value = "file", required = true) MultipartFile file) {
	    PhotoModel model = new PhotoModel();

        ApiModel<PhotoModel> apiMdole = new ApiModel<>(model); 
        apiMdole.setStatus(APIExecutedStatusType.SUCCESS.getValue());
        return apiMdole;
	}
}
