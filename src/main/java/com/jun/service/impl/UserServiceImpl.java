package com.jun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jun.dao.UserDao;
import com.jun.entity.User;
import com.jun.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(int id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public User findUserByLoginName(String username) {
		System.out.println("findUserByLoginName call!");
		return userDao.findUserByLoginName(username);
	}

}
