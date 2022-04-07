package com.model2.mvc.service.user;


import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

/*
 * FileName : UserDao.java(Data Access Object)
 * 데이터베이스와 직접적인 통신을 담당하는 퍼시스턴스 계층을 담당할 인터페이스
 */

public interface UserDao {

	//==> 회원정보 :: INSERT(회원가입)
	public int addUser(User user) throws Exception;
	
	
	//==> 회원정보 :: SELECT(회원정보 검색)
	public User getUser(String userId) throws Exception;
	
	//==> 회원정보 :: UPDATE(회원정보 변경)
	public int updateUser(User user) throws Exception;
	
	
	//==> 회원정보 :: SELECT(회원정보 검색)
	public Map<String, Object> getUserList(Search search) throws Exception;
	

	
}//end of class