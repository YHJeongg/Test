package com.jang.bbs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jang.bbs.mapper.BoardMapper;
import com.jang.bbs.model.BoardVO;
import com.jang.bbs.model.SearchVO;
import com.jang.bbs.utils.PageHelper;

@Service(value = "boardService") // 컨테이너에 객체를 만들어서 넣는다.
public class BoardService {
	
	@Resource(name = "boardMapper") // 컨테이너에서 객체를 가져온다. / @Resource = 꺼내오는 것
	private BoardMapper boardMapper; // 인스턴스변수 생성하고 그릇에 담는다. 
	
	PageHelper pageHelper = new PageHelper(); // pageHelper의 그릇을 만들어서 담는다. / PageHelper이라는 클래스의 객체를 만들어라
	
	public StringBuffer getPageUrl (SearchVO searchVO) {
		
		int totalRow = boardMapper.getTotalRow(searchVO);
		
		return pageHelper.getPageUrl(searchVO.getPage(), totalRow);
	}
	
	public List<BoardVO> getBoardList(SearchVO searchVO) {
		
		int currentPage = searchVO.getPage();
		
		int startRow = (currentPage - 1) * this.pageHelper.getPageSize()+1;
		int endRow = currentPage * this.pageHelper.getPageSize();
		
		searchVO.setStartRow(startRow);
		searchVO.setEndRow(endRow);
		
		return this.boardMapper.getBoardList(searchVO);
	}
	

}
