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
 *  ȸ������ ���� �����Ͻ� ������ �߻�ȭ�� interface ����
 *  Component : interface�� Encapsulation �� ���밡���� library
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
	//==> ȸ������ :: INSERT(ȸ������)
	public int addUser(User user) throws Exception {
		return userDao.addUser(user);
	}
	//==> ȸ������ :: SELECT(ȸ�������� �˻�)
	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
	//==> ȸ������ :: UPDATE(ȸ������ ����)
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	//==> ȸ������ :: SELECT(��� ȸ�������� �˻�)
	public Map<String, Object> getUserList(Search search) throws Exception {
		return userDao.getUserList(search);
	}
}
