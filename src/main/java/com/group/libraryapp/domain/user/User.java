package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String name;

	private Integer age;

	@OneToMany(mappedBy = "user")
	private List<UserLoanHistory> userLoanHistories;

	public User(String name, Integer age) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}
		this.name = name;
		this.age = age;
	}

	protected User() {}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this .name = name;
	}
}
