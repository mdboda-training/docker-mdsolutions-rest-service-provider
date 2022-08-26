package com.mdsolutions.controller;

import java.util.List;

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

	@Autowired
	private UserService userService;
	
	@Autowired
	Environment environment;

	@GetMapping("/mdsolutions-rest-service-provider/user/getUsers")
	public UserDto getUsers(@RequestParam Integer userId) {
		UserDto userDto= userService.getUser(userId);
		userDto.setMessage("Port: "+environment.getProperty("server.port"));
		return userDto;
	}

	@GetMapping("/mdsolutions-rest-service-provider/user/getAllUsers")
	public List<UserDto> getUsers() {
		List<UserDto> userDtoList = userService.getUsers();
		return userDtoList;
	}

	@GetMapping("/mdsolutions-rest-service-provider/user/deleteUser")
	public String getDeleteUser(@RequestParam Integer userId) {
		userService.deleteUser(userId);
		return "User Deleted Successfully";
	}
	
	@PostMapping("/mdsolutions-rest-service-provider/user/createUser")
	public UserDto createUser(@RequestBody UserDto userDto) {
		return userService.createUser(userDto);
	}

}
