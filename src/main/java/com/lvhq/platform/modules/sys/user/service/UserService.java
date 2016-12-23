package com.lvhq.platform.modules.sys.user.service;

import java.util.List;

import com.lvhq.platform.modules.sys.user.entity.User;

/**
 * 用户Service
 * 
 * @author lvhq
 * @date 2016年12月23日 上午10:53:09
 */
public interface UserService {

	List<User> findAll();

	User findByLoginName(String loginName);

	void insert(User user);

	void delete(User user);

	void delete(Long id);
}
