package com.jang.bbs.model;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;
	private String title;
	private String content;
	private int viewcnt = 0;
	private int replycnt=0;
	private int recommendcnt=0;
	private String writerId;
	private String regDate;
	private char del_yn='n';

}
