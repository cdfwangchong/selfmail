package com.cdfg.selfmail.service.impl;


import java.util.Map;

import com.cdfg.selfmail.dao.UserDao;
import com.cdfg.selfmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@author wangchong
*2019
*/
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao=null;

	public Map<String, String> getCoupon(Map<String, String> param) {
		
		return userdao.registerUser(param);
	}

	public Map<String, String> login(Map<String, String> param) {
		
		return userdao.login(param);
	}

	public Map<String, String> changPass(Map<String, String> param) {
		return userdao.changePass(param);
	}

	public Map<String, String> getVercode(Map<String, String> param) {
		
		return userdao.getVercode(param);
	}

	public Map<String, String> weChat(Map<String, String> param) {
		
		return userdao.weChat(param);
	}
}
