package com.lvhq.platform.modules.sys.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvhq.platform.modules.sys.user.dao.UserDao;
import com.lvhq.platform.modules.sys.user.entity.User;
import com.lvhq.platform.modules.sys.user.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
