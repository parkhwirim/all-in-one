package com.group.libraryapp.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepository;

@Service
public class UserServiceV1 {

	private final UserJdbcRepository userJdbcRepository;

	public UserServiceV1(UserJdbcRepository userJdbcRepository) {
		this.userJdbcRepository = userJdbcRepository;
	}

	public void updateUser(UserUpdateRequest request) {
		boolean isUserNotExist = userJdbcRepository.isUserNotExist(request.getId());
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}
		userJdbcRepository.updateUserName(request.getId(), request.getName());
	}

	public void deleteUser(String name) {
		boolean isUserNotExist = userJdbcRepository.isUserNotExist(name);
		if (isUserNotExist) {
			throw new IllegalArgumentException();
		}
		userJdbcRepository.deleteUser(name);
	}

	public void saveUser(UserCreateRequest request) {
		userJdbcRepository.saveUser(request.getName(), request.getAge());
	}

	public List<UserResponse> getUsers() {
		return userJdbcRepository.getUsers();
	}
}
