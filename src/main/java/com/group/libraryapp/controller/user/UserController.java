package com.group.libraryapp.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.user.UserService;

@RestController
public class UserController {

	private final UserService userService;
	private final FruitService fruitService;

	public UserController(UserService userService, @Qualifier("main") FruitService fruitService) {
		this.userService = userService;
		this.fruitService = fruitService;
	}

	@PostMapping("/user")
	public void saveUser(@RequestBody UserCreateRequest request) {
		userService.saveUser(request);
	}

	@GetMapping("/user")
	public List<UserResponse> getUsers() {
		return userService.getUsers();
	}

	@PutMapping("/user")
	public void updateUser(@RequestBody UserUpdateRequest request) {
		userService.updateUser(request);
	}

	@DeleteMapping("/user")
	public void deleteUser(@RequestParam String name) {
		userService.deleteUser(name);
	}

	@GetMapping("/user/error-test")
	public void errorTest() {
		throw new IllegalArgumentException();
	}
}
