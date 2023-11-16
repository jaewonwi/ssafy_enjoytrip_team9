package com.mycom.enjoytrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoytrip.dto.UserDto;
import com.mycom.enjoytrip.service.UserService;

@RestController
//@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping("users")
	public int regist(@RequestBody UserDto dto) {
		System.out.println(dto);
		return userService.regist(dto);
	}
	
	@GetMapping("users/{userId}")
	public UserDto detail(@PathVariable int userId) {
		return userService.detail(userId);
	}
	
	@PutMapping("/users/{userId}")
	public int update(@PathVariable int userId, UserDto dto) {
		return userService.update(dto);
	}
	
	@DeleteMapping("/users/{userId}")
	public int delete(@PathVariable int userId) {
		return userService.delete(userId);
	}
}
