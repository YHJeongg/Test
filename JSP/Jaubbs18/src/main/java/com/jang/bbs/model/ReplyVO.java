package com.jang.bbs.model;

import lombok.Data;

@Data
public class ReplyVO {
	
	private int rno;
	private int bno;
	private String content;
	private int recommendcnt=0;
	private int multicnt=0;
	private String writerId;
	private String regDate;
	private char del_yn='n';


}
