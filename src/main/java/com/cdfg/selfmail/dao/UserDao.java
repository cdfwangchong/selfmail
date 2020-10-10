package com.cdfg.selfmail.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;


/**
*@author wangchong 
*2018
*/
@Repository
public interface UserDao {

	public Map<String, String> registerUser(Map<String, String> param);
	
	public Map<String, String> login(Map<String, String> param);
	
	public Map<String, String> getVercode(Map<String, String> param);
	
	public Map<String, String> weChat(Map<String, String> param);
}
