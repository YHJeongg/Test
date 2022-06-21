package com.jang.bbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jang.bbs.model.BoardVO;
import com.jang.bbs.model.SearchVO;
import com.jang.bbs.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	private String uploadPath = "C:\\upload\\";
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("searchVO") SearchVO searchVO, Model model, HttpSession session) throws Exception {
		
		//session.setAttribute("userId", "TestId");
		session.setAttribute("userName", "Tester");
		
		List<BoardVO> blist = boardService.getBoardList(searchVO);
		model.addAttribute("boardList", blist);
		
		StringBuffer pageUrl = boardService.getPageUrl(searchVO);
		model.addAttribute("pageHttp", pageUrl);
		
		model.addAttribute("searchVO", searchVO);
		
		return "board/list";
	}
}

