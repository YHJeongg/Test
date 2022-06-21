package com.jang.car.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jang.car.model.AttFileVO;
import com.jang.car.model.BoardLikeVO;
import com.jang.car.model.BoardVO;
import com.jang.car.model.BoardViewVO;
import com.jang.car.model.ReplyLikeVO;
import com.jang.car.model.ReplyVO;
import com.jang.car.model.SearchVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Resource(name = "boardService")
	private BoardService boardService;
	
	private String uploadPath = "C:\\upload\\";
	
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("searchVO") SearchVO searchVO, Model model, HttpSession session) throws Exception {
		
		List<BoardVO> blist = boardService.getBoardList(searchVO);
		model.addAttribute("boardList", blist);
		
		StringBuffer pageUrl = boardService.getPageUrl(searchVO);
		model.addAttribute("pageHttp", pageUrl);
		
		model.addAttribute("searchVO", searchVO);
		
		return "board/list";
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String boardWrite(){
	return "/board/write";
	}

	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String boardWriteProc(@ModelAttribute("Board") BoardVO board, 
	        MultipartHttpServletRequest request){

	//?源� 疫�????�삢
	String content =  board.getContent().replaceAll("\r\n", "<br />");//java?源됦빳? �굜遺얜굡 HTML餓κ쑬而�熬곷㈇由경에?
	content = content.replaceAll("<","&lt;");
	content = content.replaceAll(">","&gt;");
	content = content.replaceAll("&","&amp;"); 
	content = content.replaceAll("\"","&quot;"); 

	board.setContent(content);

	boardService.writeArticle(board);

	int bno = board.getBno(); //???�삢?�뻻 ?源�?苑�?釉� ?源됪묾?甕곕뜇�깈  xml ?�솁?�뵬insert ?肉�?苑� keyProperty="bno" ?肉� ?�벥?鍮�?苑� ?苑�?�젟?留�
	   //筌ｂ뫀? ?�솁?�뵬 ???�삢
	   AttFileVO file = new AttFileVO();
	   String uploadPath = "C:\\upload\\";
	        List<MultipartFile> fileList = request.getFiles("file"); 

	        for (MultipartFile mf : fileList) {
	        if (!mf.isEmpty()) {
	            String originFileName = mf.getOriginalFilename(); // ?�뜚癰�? ?�솁?�뵬 筌�?
	            long fileSize = mf.getSize(); // ?�솁?�뵬 ?沅�?�뵠筌�?

	            System.out.println("originFileName : " + originFileName);
	            System.out.println("fileSize : " + fileSize);
	            
	            file.setBno(bno);
	            file.setOfilename(originFileName);
	            file.setSfilename(originFileName);
	            file.setFilesize(fileSize); 
	                 
	            boardService.insertFile(file);//?��?�뵠�뇡遺용퓠 ?�넅?�뵬 ?�젟癰�? ???�삢
	   
		      String safeFile = uploadPath +  originFileName; // ?逾�?�뮞?寃�?肉� ?�솁?�뵬 ???�삢     

	            try {
	                mf.transferTo(new File(safeFile)); // ?逾�?�뮞?寃�?肉� ?�솁?�뵬 ???�삢 
	            } catch (IllegalStateException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        }        
	        return "redirect:list.do";
	}
	
	@RequestMapping("/view")
	public String boardView(@RequestParam(value = "bno", required = false, defaultValue="0") int bno, HttpSession session, Model model) throws Exception {
		
	    String id = (String) session.getAttribute("id"); 

	    BoardViewVO boardViewVO = new BoardViewVO() ; //疫�?鈺곌퀬�돳 ?�뵠?�젾 ?�뒠
	    boardViewVO.setBno(bno);

	    if(id != null ) { //?�돳?�뜚?�뵥 野껋럩�뒭
	    boardViewVO.setid(id); 
	    boardViewVO.setMem_yn('y'); //?�돳?�뜚
	    
	    if( boardService.getBoardView(boardViewVO) == 0) {//?鍮�?�뼣 甕곕뜇�깈 疫�??�뱽 ?�뵭?? 疫꿸퀡以�?�뵠 ?毓�?�몵筌�? 
	 	    boardService.increaseViewCnt(bno); // 鈺곌퀬�돳?�땾 揶쏄퉮�뻿
	    boardService.addBoardView(boardViewVO); //?�돳?�뜚 鈺곌퀬�돳 ?踰묉에?
	         }
	    }
	    else { //�뜮袁れ돳?�뜚?�뵥 野껋럩�뒭 
	boardViewVO.setid(session.getId()); //?苑�?�죍d�몴? ?�돳?�뜚 id嚥�? ?踰묉에?
	boardViewVO.setMem_yn('n'); //�뜮袁れ돳?�뜚

	if(boardService.getBoardView(boardViewVO) == 0) {//?鍮�?�뼣 甕곕뜇�깈 疫�??�뱽 ?�뵭?? 疫꿸퀡以�?�뵠 ?毓�?�몵筌�? 
	boardService.increaseViewCnt(bno); // 鈺곌퀬�돳?�땾 揶쏄퉮�뻿
	boardService.addBoardView(boardViewVO);//�뜮袁れ돳?�뜚 鈺곌퀬�돳?�땾 ?踰묉에?
	     }
	   }
	BoardVO board = boardService.getArticle(bno); // get selected article model

	List<ReplyVO> reply = boardService.getReplyList(bno); // ?�뼗癰�? 筌뤴뫖以� ?�뵭?堉� ?�궎疫�? ?? list
	List<AttFileVO> fileList = boardService.getFileList(bno); // 筌ｂ뫀??�솁?�뵬 筌뤴뫖以� ?�뵭?堉� ?�궎疫�?-list

	model.addAttribute("board", board);
	model.addAttribute("replyList", reply);
	model.addAttribute("fileList", fileList);

	return "board/view";
	    
	}
	
	@RequestMapping(value = "/modify.do", method=RequestMethod.GET )
	public String boardModify(HttpServletRequest request, HttpSession session, Model model){

	String id = (String) session.getAttribute("id");
	int bno = Integer.parseInt(request.getParameter("bno"));

	BoardVO board = boardService.getArticle(bno);

	// <br /> tag change to new line code
	String content = board.getContent().replaceAll("<br />", "\r\n");
	board.setContent(content); 

	if(!id.equals(board.getid())){ //�뜮袁れ돳?�뜚 疫�??�땾?�젟 �겫�뜃?
		model.addAttribute("errCode", "1"); 
		model.addAttribute("bno", bno);
		return "redirect:view.do";
	} else {//?�돳?�뜚 疫�??�땾?�젟
		List<AttFileVO> fileList = boardService.getFileList(bno); // 筌ｂ뫀??�솁?�뵬 ?�뵭?堉� ?�궎疫�? - list
	
		model.addAttribute("board", board);
		model.addAttribute("fileList", fileList);
		return "/board/modify";
		}
	}
	
	@RequestMapping(value = "/modify.do", method=RequestMethod.POST)  //野껊슣�뻻?�솇 疫�? ?�땾?�젟
	public String  boardModifyProc(@ModelAttribute("Board") BoardVO board, MultipartHttpServletRequest request, Model model ){

	String content =  board.getContent().replaceAll("\r\n", "<br />"); //java ?源됦빳? �굜遺얜굡 HTML餓κ쑬而�熬곷㈇由경에?
	board.setContent(content);

	boardService.updateArticle(board);

	int bno = board.getBno(); 


	//筌ｋ똾寃�?留� ?�솁?�뵬?�뱽 ?��?�뵠�뇡遺쎈궢 ?逾�?�뮞?寃�?肉�?苑� ?沅�?�젫?釉�?�뼄.
	String[] fileno = request.getParameterValues("fileno");

	if (fileno != null) {

	for (String fn : fileno) {

	    int fno = Integer.parseInt(fn);
	    
	    String oFileName = boardService.getFileName(fno);
	   
	    String safeFile = uploadPath +  oFileName;
	    
	    File removeFile = new File(safeFile);// remove disk uploaded file 
	    removeFile.delete();
	        
	    boardService.deleteFile(fno); //remove table uploaded file 
		}
	}
    AttFileVO file = new AttFileVO ();

    //?源됵㎗�뫀? ?�솁?�뵬  筌뤴뫖以� ?�뵬?堉�?�궎疫�?
    List<MultipartFile> fileList = request.getFiles("file");
   
    for (MultipartFile mf : fileList) {
   if (!mf.isEmpty()) {
        String originFileName = mf.getOriginalFilename(); // ?源됵㎗�뫀??�솁?�뵬 ?�뜚癰�? ?�솁?�뵬 筌�?
    	 long fileSize = mf.getSize(); // ?�솁?�뵬 ?沅�?�뵠筌�?
        
        file.setBno(bno);
        file.setFilesize(fileSize); 
        file.setOfilename(originFileName);
        file.setSfilename(originFileName);
             
        boardService.insertFile(file); // ?��?�뵠�뇡遺용퓠 ?�솁?�뵬 ???�삢 

        String safeFile = uploadPath +  originFileName; 

        try {
            mf.transferTo(new File(safeFile)); // ?逾�?�뮞?寃�?肉� ?�솁?�뵬 ???�삢 
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
  }
    model.addAttribute("bno", board.getBno());
  	return "redirect:/board/view.do";
	}
	
	@RequestMapping("/delete.do")
	public String boardDelete(HttpServletRequest request, HttpSession session, Model model){
		String id = (String) session.getAttribute("id"); // login 揶쏆뮆而�?裕� ?沅�?�젫
		int bno = Integer.parseInt(request.getParameter("bno"));
	
		BoardVO board = boardService.getArticle(bno);

		String setView="";
		if(id.equals(board.getid())){
			//?�뼗疫�? ?沅�?�젫 
			List<ReplyVO> reply = boardService.getReplyList(bno); 
			if(reply.size() > 0){
				boardService.deleteReplyBybno(bno);
			}
			//筌ｂ뫀? ?�솁?�뵬筌�? ?沅�?�젫, ?�뼄?�젫 ?�솁?�뵬 ?沅�?�젫 
			List<AttFileVO> files  = boardService.getFileList(bno); 
			if(files.size() > 0){
			     //???�삢?留�  ?�뼄?�젫 ?�솁?�뵬 ?沅�?�젫
				for (AttFileVO filedel : files) {
				String f_stor_all = filedel.getOfilename();
				File f = new File(session.getServletContext().getRealPath("/") + f_stor_all); 
				f.delete();
			}
			boardService.deleteFileBybno(bno); //?��?�뵠�뇡遺용퓠?苑� ?鍮�?�뼣 甕곕뜇�깈 疫�? 筌ｂ뫀? file ?�읈筌�? ?沅�?�젫
		}
		//board ?沅�?�젫
		boardService.deleteArticle(bno);
		setView="redirect:list.do";
	} else {
		model.addAttribute("errCode", "1");// it's forbidden connection
		model.addAttribute("bno", bno);
		setView="redirect:view.do";
		}
		return setView ;
	}
	
	@RequestMapping("/recommend.do")
	public String updateRecommendCnt(HttpServletRequest request, HttpSession session, Model model){

	int bno = Integer.parseInt(request.getParameter("bno")); 
	String id = (String) session.getAttribute("id"); 

	if( id==null){
		model.addAttribute("bno", bno);
		model.addAttribute("errCode", "4");
		return "redirect:/board/view.do";
	}

	BoardLikeVO boardLike = new BoardLikeVO();
	boardLike.setBno(bno);
	boardLike.setid(id);

	BoardVO board = boardService.getArticle(bno);

	if( board.getid().equals(id)){
		model.addAttribute("errCode", "3");                //癰귣챷�뵥 疫�??? �빊遺우퓝 �겫�뜃?
	} else {
		if (boardService.addBoardLike(boardLike) == 0 ) {  //?�뵠沃�? �빊遺우퓝?釉� 疫꿸퀡以�?�뵠 ?毓�?�몵筌�?
			boardService.increaseRecommendcnt(bno);
			boardService.addBoardLike(boardLike);         //�빊遺우퓝 疫꿸퀡以� ?踰묉에?
		}else {
			model.addAttribute("errCode", "2");           //?�뵠沃�? �빊遺우퓝?六�?�쐲 疫�??�뵠筌�? �빊遺우퓝 �겫�뜃? 
	     	}
		}
	model.addAttribute("bno", bno);
	return "redirect:/board/view.do";
	}
	
	@RequestMapping("/writeReply.do")
	public String replyWriteProc(@ModelAttribute("reply") ReplyVO reply, Model model){

	if (reply.getContent().isEmpty()) {
		model.addAttribute("errCode", "1");
	}
	else {
		String  content = reply.getContent().replaceAll("<","&lt;");
		content = reply.getContent().replaceAll(">","&gt;");
		content = reply.getContent().replaceAll("&","&amp;"); 
		content = reply.getContent().replaceAll("\"","&quot;"); 
		content = reply.getContent().replaceAll("\r\n", "<br />");

		reply.setContent(content);

		boardService.writeReply(reply);
	}

	model.addAttribute("bno", reply.getBno());

	return "redirect:view.do";
	}
	
	@RequestMapping("/deleteReply.do")
	public String commentDelete(HttpServletRequest request, HttpSession session,  Model model ){

		int rno = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
	
		String id = (String) session.getAttribute("id");
		ReplyVO reply = boardService.getReply(rno);
	
		if(!id.equals(reply.getWriterId())){
			model.addAttribute("errCode", "1");
		} else {
			boardService.deleteReply(rno);
		}
	
		model.addAttribute("bno", bno); // move back to the article
	
		return "redirect:view.do";
	}
	
	@RequestMapping("/recommandReply.do")
	public String RecommendRely(HttpServletRequest request, HttpSession session, Model model){

		int bno = Integer.parseInt(request.getParameter("bno")); 
		int rno = Integer.parseInt(request.getParameter("rno")); 
		String id = (String) session.getAttribute("id"); 
	
		if( id==null){
			model.addAttribute("bno", bno);
			model.addAttribute("errCode", "4");;
			return "redirect:/board/view.do";
		}
	
		ReplyLikeVO replyLike = new ReplyLikeVO();
		replyLike.setRno(rno);
		replyLike.setid(id);
	
		BoardVO board = boardService.getArticle(bno);
	
		if(board.getid().equals(id)){
			model.addAttribute("errCode", "3");
		} else {
			if (boardService.getReplyLike(replyLike) == 0 ) {
				boardService.incReplyRecCnt(rno);
				boardService.addReplyLike(replyLike);
			} else {
		    	model.addAttribute("errCode", "2");
		    }
		}
		model.addAttribute("bno", bno);
		return "redirect:/board/view.do";
	}
	
	@RequestMapping(value = "filedown")
	@ResponseBody
	public byte[] downProcess(HttpServletResponse response,@RequestParam  String fileName ) throws IOException{

		File file = new File("c:/upload/" + fileName);
		byte[] bytes = FileCopyUtils.copyToByteArray(file); //SPRING 5.0 ?�뵠?湲� 
	
		String fn = new String(file.getName().getBytes(), "iso_8859_1");
	
		response.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
		response.setContentLength(bytes.length);
	
		return bytes;
	}

}