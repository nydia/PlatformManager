package com.lvhq.platform.modules.sys.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lvhq.platform.modules.sys.user.entity.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query(" select u from User u ")
	List<User> findAll();

	@Query(" select u from User u where u.loginName = ? ")
	User findByLoginName(String loginName);

}
