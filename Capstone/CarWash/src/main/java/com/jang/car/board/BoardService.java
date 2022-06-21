package com.jang.car.board;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jang.car.mapper.BoardMapper;
import com.jang.car.model.AttFileVO;
import com.jang.car.model.BoardLikeVO;
import com.jang.car.model.BoardVO;
import com.jang.car.model.BoardViewVO;
import com.jang.car.model.ReplyLikeVO;
import com.jang.car.model.ReplyVO;
import com.jang.car.model.SearchVO;

@Service(value = "boardService") // �����̳ʿ� ��ü�� �����? �ִ´�.
public class BoardService {
	
	@Resource(name = "boardMapper") // �����̳ʿ��� ��ü�� �����´�. / @Resource = �������� ��
	private BoardMapper boardMapper; // �ν��Ͻ����� �����ϰ� �׸��� ��´�?. 
	
	PageHelper pageHelper = new PageHelper(); // pageHelper�� �׸��� �����? ��´�?. / PageHelper�̶��? Ŭ������ ��ü�� ������
	
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
	
	public int addBoardView(BoardViewVO boardView){   //userid-�?번호 조회 기록 추�? 
        return this.boardMapper.addBoardView(boardView);
    } 
	
	public int getBoardView(BoardViewVO boardView){ //userid-�?번호 조회 기록 ?���? 
        return this.boardMapper.getBoardView(boardView);
    } 

	public BoardVO getArticle(int bno) {
		return this.boardMapper.getArticle(bno);
	}
	
	public int writeArticle (BoardVO board) {
		return this.boardMapper.writeArticle(board);
	}
		
	public int updateArticle(BoardVO board) {
		return this.boardMapper.updateArticle(board);
	}
	
	public void deleteArticle(int bno){
		this.boardMapper.deleteArticle(bno);
		return; 
	}
		
	public int increaseViewCnt(int bno){
		return this.boardMapper.incrementViewCnt(bno);
	}
		
	public int increaseRecommendcnt(int bno){
		return this.boardMapper.incrementGoodCnt(bno);
	}
		
	public int increaseReplyCnt(int rno, int bno){
		return this.boardMapper.incrementReplyCnt(bno);
	}
		
	public List<ReplyVO> getReplyList(int bno) {
		return this.boardMapper.getReplyList(bno);
	}
		
	public ReplyVO getReply(int rno){
		return this.boardMapper.getReply(rno);
	}
		
	public int writeReply(ReplyVO reply){
		int bno = reply.getBno(); 
		this.boardMapper.incrementReplyCnt(bno); 
		return this.boardMapper.writeReply(reply);
	}
	
	public int updateReply(ReplyVO reply){
		return this.boardMapper.updateReply(reply);
	}	
		
	public int incReplyRecCnt( int rno) {
		return this.boardMapper.incReplyGoodCnt(rno);
	}
		
	public void deleteReply(int rno){
		this.boardMapper.deleteReply(rno); 
		return;
	}
		
	public void deleteReplyBybno(int bno){
		this.boardMapper.deleteReplyBybno(bno);
		return;
	}
		
	public List<AttFileVO> getFileList( int bno) {
		return this.boardMapper.getFileList(bno);
	}
		
	public String getFileName(int fno){
		return this.boardMapper.getFileName(fno);
	}
		
	public int insertFile(AttFileVO file) {
		return this.boardMapper.insertFile(file);
	}
		
	public void deleteFile(int fno) {
		this.boardMapper.deleteFile(fno); return;
	}
		
	public void deleteFileBybno(int bno) {
		this.boardMapper.deleteFileBybno(bno); 
		return;
	}
		
	public int addBoardLike(BoardLikeVO boardLike) {
		return this.boardMapper.addBoardLike(boardLike);
	}
		
	public int getBoardLike(BoardLikeVO boardLike) {
		return this.boardMapper.getBoardLike (boardLike);
	}
		
	public int addReplyLike(ReplyLikeVO boardLike) {
		return this.boardMapper.addReplyLike(boardLike);
	}
		
	public int getReplyLike(ReplyLikeVO replyLike) {
		return this.boardMapper.getReplyLike(replyLike);
	}
}
