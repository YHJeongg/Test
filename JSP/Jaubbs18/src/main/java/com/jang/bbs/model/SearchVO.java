package com.jang.bbs.model;

import lombok.Data;

@Data
public class SearchVO {
	
	private String type = "";
	private String keyword = "";
	private int   page=1;
	private int   startRow=1;
	private int   endRow=1;



}
