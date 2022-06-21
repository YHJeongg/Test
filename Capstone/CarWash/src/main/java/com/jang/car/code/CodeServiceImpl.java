package com.jang.car.code;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jang.car.mapper.CodeService;
import com.jang.car.model.CodeBean;

@Service("codeService")
public class CodeServiceImpl implements CodeService{
	@Resource(name = "codeDAO")
	private CodeDAO codeDAO;

	@Override
	public List<CodeBean> retrieveCodeList(Map<String, String> codeParam) {
		return codeDAO.retrieveCodeList(codeParam);
	}




}
