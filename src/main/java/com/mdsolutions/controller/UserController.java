package com.mdsolutions.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdsolutions.dto.UserDto;
import com.mdsolutions.services.UserService;

@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	Environment environment;

	@GetMapping("/docker-mdsolutions-rest-service-provider/user/getUsers")
	public UserDto getUsers(@RequestParam Integer userId) {
		logger.info("Invoking request /mdsolutions-rest-service-provider/user/getUsers by {} id start",userId);
		
		UserDto userDto= userService.getUser(userId);
		userDto.setMessage("Port: "+environment.getProperty("server.port"));
		logger.info("Invoking request /mdsolutions-rest-service-provider/user/getUsers by {} id end", userId);
		return userDto;
	}

	@GetMapping("/docker-mdsolutions-rest-service-provider/user/getAllUsers")
	public List<UserDto> getUsers() {
		logger.info("Invoking request /mdsolutions-rest-service-provider/user/getUsers Start");
		List<UserDto> userDtoList = userService.getUsers();
		logger.info("Invoking request /mdsolutions-rest-service-provider/user/getUsers End");
		return userDtoList;
	}

	@GetMapping("/docker-mdsolutions-rest-service-provider/user/deleteUser")
	public String getDeleteUser(@RequestParam Integer userId) {
		userService.deleteUser(userId);
		return "User Deleted Successfully";
	}
	
	@PostMapping("/docker-mdsolutions-rest-service-provider/user/createUser")
	public UserDto createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

}
