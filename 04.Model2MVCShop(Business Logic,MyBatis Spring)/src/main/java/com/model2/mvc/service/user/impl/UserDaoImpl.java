package com.model2.mvc.service.user.impl;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;



@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::"+getClass()+".setSqlSession() Call..");
		this.sqlSession=sqlSession;
	}
	
	//Constructor
	public UserDaoImpl() {
		System.out.println("::"+getClass()+"default Constructor Call..");
	}
	
	//Method
	public int addUser(User user) throws Exception{
		return sqlSession.insert("UserMapper10.addUser",user);
	}
	
	public User getUser(String userId) throws Exception{
		return (User)sqlSession.selectOne("UserMapper10.getUser",userId);
	}
	
	public int updateUser(User user) throws Exception{
		return sqlSession.update("UserMapper10.updateUser",user);
	}
	
	
	public Map<String, Object> getUserList(Search search) throws Exception{
		
		return sqlSession.selectOne("UserMapper10.getUserList",search);
		//selectlist인데 잘 멀겠음
	}
	

}
