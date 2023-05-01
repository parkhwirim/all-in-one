package com.group.libraryapp.service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;

@Service
public class UserServiceV2 {

	private final UserRepository userRepository;

	public UserServiceV2(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 함수가 시작될 때 start transaction
	// 함수가 예외없이 잘 끝났다면 commit
	// 함수가 예외가 발생했다면 rollback
	@Transactional
	public void saveUser(UserCreateRequest request) {
		userRepository.save(new User(request.getName(), request.getAge()));
	}

	@Transactional(readOnly = true)
	public List<UserResponse> getUsers() {
		return userRepository.findAll().stream()
			.map(UserResponse::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateUser(UserUpdateRequest request) {
		User user = userRepository.findById(request.getId())
			.orElseThrow(IllegalArgumentException::new);
		user.setName(request.getName());
		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(String name) {
		User user = userRepository.findByName(name)
			.orElseThrow(IllegalArgumentException::new);
		userRepository.deleteById(user.getId());
	}
}
