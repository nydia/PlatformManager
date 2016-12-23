package com.lvhq.platform.modules.sys.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

	@Override
	public User findByLoginName(String loginName) {
		User user = null;
		if (StringUtils.isNotBlank(loginName)) {
			user = userDao.findByLoginName(loginName);
		}
		return user;
	}

	@Override
	public void insert(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}
}
