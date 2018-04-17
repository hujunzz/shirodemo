package com.jun.service;

import com.jun.entity.User;

public interface UserService {
	public User getUserById(int id);

	public User findUserByLoginName(String username);
}
