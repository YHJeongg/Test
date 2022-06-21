package com.jang.bbs.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jang.bbs.mapper.LoginMapper;
import com.jang.bbs.model.UserVO;

@Service(value = "loginService")
public class LoginService {
	
	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;
	
	public UserVO findUser(UserVO userVO) {
		return loginMapper.findUser(userVO);
	}


	public UserVO getUser(String userId) {
		   return loginMapper.getUser(userId);
		}


	public void updateUser(UserVO userVO) {
		// TODO Auto-generated method stub
		
	}



}
