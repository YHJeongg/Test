package com.jang.bbs.controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jang.bbs.model.UserVO;
import com.jang.bbs.service.LoginService;
import com.jang.bbs.utils.AES256Util;
import com.jang.bbs.utils.BCrypt;

@Controller
@RequestMapping("/member")
public class LoginController {
	
	@Resource(name = "loginService")  //@Autowired :다른 bean 참조  
	private LoginService loginService;

//	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
//	public String login() {
//		return "/member/login";
//	}
//	
	@RequestMapping("/ajaxlogin.do")
	public String ajlogin() {
	       return "/member/ajaxlogin";
	}
	
	
	
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public String loginProc(@Valid UserVO userVO, BindingResult result, Model model, HttpSession session) {

	if (result.hasFieldErrors("userId")||result.hasFieldErrors("passwd"))  {
	model.addAllAttributes(result.getModel());
	return "/member/login";
	}
	UserVO loginUser = this.loginService.getUser(userVO.getUserId());

	if(loginUser == null){
	model.addAttribute("userId", "");
	model.addAttribute("errCode", 1);// 등록되지않은 아이디

	return "/member/login";
	}
	else if(BCrypt.checkpw(userVO.getPasswd(), loginUser.getPasswd()) ) { //패스워드 일치
	model.addAttribute("loginUser", loginUser);

	session.setAttribute("userId", loginUser.getUserId()); //세션에 변수등록
	session.setAttribute("userName", loginUser.getName()); //세션에 변수등록
	return "redirect:/board/list.do";
	}
	else {
	model.addAttribute("userId", "");
	model.addAttribute("errCode", 4);// 패스워드 불일치

	return "/member/login";
	}

	}
	
	@RequestMapping(value="/edituser", method = RequestMethod.GET)
	public String toUserEditView( HttpSession session, Model model) throws       
	    java.io.UnsupportedEncodingException,
	    NoSuchAlgorithmException,
	    NoSuchPaddingException, 
	    InvalidKeyException, 
	    InvalidAlgorithmParameterException,
	    IllegalBlockSizeException, 
	    BadPaddingException  {

	String userId = session.getAttribute("userId").toString();
	UserVO loginUser = this.loginService.getUser(userId);

	if(loginUser == null){
	model.addAttribute("userId", "");
	model.addAttribute("errCode", 1);// 등록되지않은 아이디

	return "/member/login"; 
	}
	else {//주민번호 복호화 
	String jumin1=loginUser.getJumin().substring(0,6);
	String jumin2=loginUser.getJumin().substring(7);

	String key="jangan-1182-Key!!";
	AES256Util aes256 = new AES256Util(key);

	String decjumin = aes256.aesDecode(jumin2);
	decjumin = jumin1 + "-" + decjumin;
	     
	loginUser.setJumin(decjumin);
	      model.addAttribute("userVO", loginUser);
	      return "/member/editForm";
	} 
	} 


	
	@RequestMapping(value="/edituser", method = RequestMethod.POST)
	public String onEditSave( @Valid UserVO userVO, BindingResult result, Model model) throws Exception {

	 if (result.hasErrors()) {
	model.addAllAttributes(result.getModel());
	return "/member/editForm";
	 }
	 
	 userVO.setPasswd(BCrypt.hashpw(userVO.getPasswd(), BCrypt.gensalt(12))); 
	 
	 try {
	this.loginService.updateUser(userVO);
	model.addAttribute("userVO", userVO);
	return "/board/list.do";
	 } 
	 catch ( DataAccessException e) {
	result.reject("error.duplicate.user");
	model.addAllAttributes(result.getModel());
	return "/member/editForm";
	 }
	}

	


	@RequestMapping(value= "/ajaxlogin.do", method=RequestMethod.POST)
	public @ResponseBody String AjaxView(@Valid UserVO user, BindingResult bindingResult, HttpSession session) {
	String err=null;
	if (bindingResult.hasFieldErrors("userId"))  {
		err="eid";
	} else if(bindingResult.hasFieldErrors("passwd")) {
		err="epass";
	}

	Gson gson = new Gson();
	JsonObject object = new JsonObject();

	UserVO loginUser = this.loginService.getUser(user.getUserId());// 여기 암호화하면서 바뀌야한다.


	if(loginUser == null){
	object.addProperty("id", "Null");
	object.addProperty("msg", "Fail");
	object.addProperty("err", err);

	String json = gson.toJson(object);
	    	return json.toString();
	} else {
	session.setAttribute("userId", loginUser.getUserId()); //세션에 변수등록
	session.setAttribute("userName", loginUser.getName()); //세션에 변수등록

	object.addProperty("id", loginUser.getUserId());
	object.addProperty("msg", "Success");

	String json = gson.toJson(object);

	return json.toString();
	      }

  }
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
	session.invalidate();
	return "redirect:/member/login.do";
	}

}

