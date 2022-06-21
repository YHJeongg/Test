package com.jang.bbs.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jang.bbs.model.UserVO;

@Mapper //@Repository(value = "loginMapper")
public interface LoginMapper {
	
	UserVO findUser(UserVO userVO);
	
	UserVO getUser(String userId);

	int updateUser(UserVO userVO);
	int insertUser(UserVO userVO);


}
