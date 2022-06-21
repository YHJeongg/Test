package com.jang.car.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jang.car.mapper.CodeService;
import com.jang.car.mapper.UserService;
import com.jang.car.model.CodeBean;
import com.jang.car.model.UserBean;

@Controller
public class UserController implements HttpSessionListener{
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "codeService")
	private CodeService codeService;
	
	@RequestMapping(value = "/work/user/login.do", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value = "/work/user/idfind.do", method = RequestMethod.GET)
	public String idFd() {
		return "user/idFind";
	}
	
	@RequestMapping(value="/work/user/idCheck.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> idCheck(HttpServletRequest request){
		String id = request.getParameter("id");
		UserBean bean = userService.retrieveUser(id);
		String checkMsg = "<font color='green' size='3px;'><i class='fa fa-check'>&nbsp;�궗�슜 媛��뒫�븳 �븘�씠�뵒�엯�땲�떎.</i></font>@true";

		if(bean != null){
			checkMsg = "<font color='red' size='3px;'><i class='fa fa-times'>&nbsp;�씠誘� �궗�슜以묒씤 �븘�씠�뵒�엯�땲�떎.</i></font>@false";
		}

		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type","text/html;charset=UTF-8");
		ResponseEntity resultMsg = new ResponseEntity<String>(checkMsg, resHeader, HttpStatus.OK);
		return resultMsg;
	}

	@RequestMapping(value="/work/user/ajaxLoginCheck.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> ajaxLogincheck(HttpServletRequest request
											, @RequestParam(value="id", required=false) String id
											, @RequestParam(value="pw", required=false) String pw){
		Map<String, String> loginYnMap = new HashMap<String, String>();

		boolean check = false;
		check = userService.logincheck(id, pw);

		if(check){
			loginYnMap.put("loginYn", "success");
		}else{
			loginYnMap.put("loginYn", "fail");
		}

		return loginYnMap;
	}

	@RequestMapping(value="/work/user/login.do",method=RequestMethod.POST)
	public String login(@ModelAttribute UserBean bean, HttpServletRequest request){
		HttpSession session = request.getSession();

		UserBean userBean = userService.retrieveSessionInfo(bean.getId());

		String userCode = userBean.getUserCode();
		String id = userBean.getId();
		String grade = userBean.getGrade();

		session.setAttribute("userCode", userCode);
		session.setAttribute("grade", grade);
		session.setAttribute("id", id);

		session.setMaxInactiveInterval(-1); //�꽭�뀡 臾댄븳��
		return "redirect:/work/product/goMain.do";
	}

	@RequestMapping(value="/work/user/retrieveUserId.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, String>> retrieveUserId(HttpServletRequest request){
		String checkMsg = "";
		String email = request.getParameter("email");
		String id = userService.retrieveUserId(email);

		Map<String, String> idMap = new HashMap<String, String>();

		if(id == null){
			checkMsg = "<div class='col-md-5 col-md-offset-2'>"
					+ "<p>�빐�떦�븯�뒗 ID媛� 議댁옱�븯吏� �븡�뒿�땲�떎</p>"
					+ "</div>";
		}else{
			checkMsg = "<div class='col-md-5 col-md-offset-2'>"
					+ "<p>�쉶�썝�떂�쓽 ID�뒗 <font color='red'><strong>" + id + "</strong></font>�엯�땲�떎."
					+ " <a href='login.jsp'>濡쒓렇�씤 �럹�씠吏�濡� �씠�룞</a>|<a href='pwFind.jsp'>PW李얘린</a></p>"
					+ "</div>";
		}

		idMap.put("checkMsg", checkMsg);

		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type","text/html;charset=UTF-8");
		ResponseEntity resultMsg = new ResponseEntity<Map<String, String>>(idMap, resHeader, HttpStatus.OK);
		return resultMsg;
	}

	@RequestMapping(value="/work/user/retrieveUserPw.do", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, String>> retrieveUserPw(HttpServletRequest request){
		String checkMsg = "";
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String pw = userService.retrieveUserPw(id, email);

		Map<String, String> idMap = new HashMap<String, String>();

		if(pw == null){
			checkMsg = "<div class='col-md-5 col-md-offset-2'>"
					+ "<p>�빐�떦�븯�뒗 ID媛� 議댁옱�븯吏� �븡�뒿�땲�떎</p>"
					+ "</div>";
		}else{
			checkMsg = "<div class='col-md-4 col-md-offset-2'>"
					+ "<p>�쉶�썝�떂�쓽 PW�뒗 <font color='blue'><strong>" + pw + "</strong></font>�엯�땲�떎."
					+ " <a href='login.jsp'>濡쒓렇�씤 �럹�씠吏�濡� �씠�룞</a></p>"
					+ "</div>";
		}

		idMap.put("checkMsg", checkMsg);

		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type","text/html;charset=UTF-8");
		ResponseEntity resultMsg = new ResponseEntity<Map<String, String>>(idMap, resHeader, HttpStatus.OK);
		return resultMsg;
	}

	@RequestMapping(value="/work/user/logout.do")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("userCode");
		session.invalidate();
		return "redirect:/work/product/goMain.do";

	}

	@RequestMapping(value="/work/user/createUser.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView createUser(@ModelAttribute UserBean bean){
		ModelAndView mv = new ModelAndView();
        String id = bean.getId(); //�뾾�쑝硫� GET(create�븞�븿), �엳�쑝硫� POST(create)
		Map<String, String> codeParam = new HashMap<String, String>();

		if(id == null){
			codeParam.put("commTyCd", "CODE0100");
			List<CodeBean> dsCode1 = codeService.retrieveCodeList(codeParam);
			mv.addObject("dsCode1", dsCode1);
			mv.setViewName("/user/userC");
		}else{
			userService.createUser(bean);
			mv.setViewName("/user/login");
		}
		return mv;
	}

	@RequestMapping(value="/work/user/updateUser.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateUser(HttpServletRequest request, @ModelAttribute UserBean bean){
		HttpSession session = request.getSession();

		ModelAndView mv = new ModelAndView();
        String id = (String) session.getAttribute("id"); //�뾾�쑝硫� GET(create�븞�븿), �엳�쑝硫� POST(create)
        String flag = bean.getName();
		Map<String, String> codeParam = new HashMap<String, String>();

		UserBean dsUser = userService.retrieveUser(id);

		if(flag == null){
			codeParam.put("commTyCd", "CODE0100");

			List<CodeBean> dsCode1 = codeService.retrieveCodeList(codeParam);

			mv.addObject("dsUser", dsUser);
			mv.addObject("dsCode1", dsCode1);
			mv.setViewName("/user/userU");
		}else{
			String userCode = dsUser.getUserCode();
			bean.setUserCode(userCode);
			userService.updateUser(bean);
			mv.setViewName("redirect:/work/product/goMain.do");
		}
		return mv;
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		session.removeAttribute("id");
		session.removeAttribute("userCode");
		session.invalidate();
	}
	
	@RequestMapping(value="/work/applicant/retrievePostByDong.do")
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> retrievePostByDong(HttpServletRequest request){
		String paramdong = request.getParameter("dong");

		Map<String, String> userParam = new HashMap<String, String>();

		userParam.put("dong", paramdong);

		List<Map<String, String>> dsPost = userService.retrievePostByDong(userParam);

		JSONArray jsonPostArr = new JSONArray();
		JSONObject jsonObj = null;

		for(int i = 0; i < dsPost.size(); i++){
			jsonObj = new JSONObject();
			String zipcode = dsPost.get(i).get("ZIPCODE");
			String sido = dsPost.get(i).get("SIDO");
			String gugun = dsPost.get(i).get("GUGUN");
			String dong = dsPost.get(i).get("DONG");
			String bunji = dsPost.get(i).get("BUNJI");

			jsonObj.put("zipcode", zipcode);
			jsonObj.put("sido", sido);
			jsonObj.put("gugun", gugun);
			jsonObj.put("dong", dong);
			jsonObj.put("bunji", bunji);

			jsonPostArr.add(jsonObj);
		}

		HttpHeaders resHeader = new HttpHeaders();
		resHeader.add("Content-Type","text/javascript;charset=UTF-8");

		ResponseEntity resultMsg = new ResponseEntity<List<Map<String, String>>>(jsonPostArr, resHeader, HttpStatus.OK);
		return resultMsg;
	}


}
