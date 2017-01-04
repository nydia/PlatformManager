package com.lvhq.platform.modules.sys.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lvhq.platform.modules.sys.user.entity.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	@Query(" select u from User u ")
	List<User> findAll();

	// 1.第一种查询方法
	// @Query(" select u from User u where u.loginName = :loginName ")
	// User findByLoginName(@Param("loginName") String loginName);

	// 2.第二种查询方法
	 @Query(" select u from User u where u.loginName = ?1 ")
	 User findByLoginName(String loginName);

	// 3.第三种查询方法
	//@Query(" select u from User u where u.loginName = ? ")
	//User findByLoginName(String loginName);

}
