package com.group.libraryapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void updateUser(UserUpdateRequest request) {
		boolean isUserNotExist = userRepository.isUserNotExist(request.getId());
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}
		userRepository.updateUserName(request.getId(), request.getName());
	}

	public void deleteUser(String name) {
		boolean isUserNotExist = userRepository.isUserNotExist(name);
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}
		userRepository.deleteUser(name);
	}

	public void saveUser(UserCreateRequest request) {
		userRepository.saveUser(request.getName(), request.getAge());
	}

	public List<UserResponse> getUsers() {
		return userRepository.getUsers();
	}
}
