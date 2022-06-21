package com.jang.car.mapper;

import java.util.List;
import java.util.Map;

import com.jang.car.model.CodeBean;

public interface CodeService {
	public List<CodeBean> retrieveCodeList(Map<String, String> codeParam);
}
