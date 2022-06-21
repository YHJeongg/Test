package com.jang.bbs.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserVO {
	
	
	private int mno;
	
	@NotEmpty(message="id를 입력하세요!")
	private String userId;
	@NotEmpty(message="패스워드를 입력하세요!")
	private String passwd;
	private String name;
	private String jumin;
	private String regDate;
	private String email;
	private char del_yn='n';
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}

	
}
