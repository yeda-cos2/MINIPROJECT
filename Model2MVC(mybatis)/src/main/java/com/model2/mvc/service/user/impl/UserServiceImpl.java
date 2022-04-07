package com.model2.mvc.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;



/*
 * FileName : UserServiceImpl13.java
 *  회원관리 서비스 비지니스 로직을 추상화한 interface 구현
 *  Component : interface로 Encapsulation 된 재사용가능한 library
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	//Field
	@Autowired
	@Qualifier("userDaoImpl")
	UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		System.out.println("::"+getClass()+".setUserDao Call....");
		this.userDao = userDao;
	}
	
	//Constructor
	public UserServiceImpl() {
		System.out.println("::"+getClass()+" default Constructor Call.....");
	}

	//Method
	//==> 회원정보 :: INSERT(회원가입)
	public int addUser(User user) throws Exception {
		return userDao.addUser(user);
	}
	//==> 회원정보 :: SELECT(회원정보를 검색)
	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
	//==> 회원정보 :: UPDATE(회원정보 변경)
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	//==> 회원정보 :: SELECT(모든 회원정보를 검색)
	public Map<String, Object> getUserList(Search search) throws Exception {
		return userDao.getUserList(search);
	}
}
