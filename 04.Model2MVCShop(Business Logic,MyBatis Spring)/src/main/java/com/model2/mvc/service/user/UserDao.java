package com.model2.mvc.service.user;


import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

/*
 * FileName : UserDao.java(Data Access Object)
 * �����ͺ��̽��� �������� ����� ����ϴ� �۽ý��Ͻ� ������ ����� �������̽�
 */

public interface UserDao {

	//==> ȸ������ :: INSERT(ȸ������)
	public int addUser(User user) throws Exception;
	
	
	//==> ȸ������ :: SELECT(ȸ������ �˻�)
	public User getUser(String userId) throws Exception;
	
	//==> ȸ������ :: UPDATE(ȸ������ ����)
	public int updateUser(User user) throws Exception;
	
	
	//==> ȸ������ :: SELECT(ȸ������ �˻�)
	public Map<String, Object> getUserList(Search search) throws Exception;
	

	
}//end of class