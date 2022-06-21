package com.jang.car.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jang.car.model.AttFileVO;
import com.jang.car.model.BoardLikeVO;
import com.jang.car.model.BoardVO;
import com.jang.car.model.BoardViewVO;
import com.jang.car.model.ReplyLikeVO;
import com.jang.car.model.ReplyVO;
import com.jang.car.model.SearchVO;

@Repository(value = "boardMapper") //@Mapper
public interface BoardMapper {
	
	List<BoardVO> getBoardList(SearchVO searchVO); //ï¿½ï¿½ ï¿½ï¿½ï¿? ï¿½ï¿½È¸
	
	BoardVO getArticle(int bno); //ï¿½ï¿½ ï¿½ï¿½È¸
	int writeArticle(BoardVO board); //ï¿½ï¿½ ï¿½Û¼ï¿½
	int updateArticle(BoardVO board); //ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	int deleteArticle(int bno); //ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	
	int getTotalRow(SearchVO searchVO); //ï¿½ï¿½Ã¼ ï¿½Û¼ï¿½ ï¿½ï¿½È¸
	
	int incrementViewCnt(int bno); //ï¿½ï¿½È¸ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	int incrementGoodCnt(int bno); //ï¿½ï¿½ï¿½Æ¿ï¿½ ï¿½ï¿½ï¿½ï¿½
	int incrementReplyCnt(int bno); //ï¿½ï¿½Û¼ï¿? ï¿½ï¿½ï¿½ï¿½
	
	List<ReplyVO> getReplyList(int bno); //ï¿½ï¿½ï¿? ï¿½ï¿½ï¿? ï¿½ï¿½È¸
	ReplyVO getReply(int rno); //ï¿½ï¿½ï¿? ï¿½ï¿½È¸
	
	int writeReply(ReplyVO reply); //ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½
	int updateReply(ReplyVO reply); //ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½
	void deleteReply(int rno); //ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½
	void deleteReplyBybno(int bno); //ï¿½ï¿½ï¿½Û¿ï¿½ ï¿½Ò¼Óµï¿½ ï¿½ï¿½ï¿? ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
	
	int incReplyGoodCnt(int rno); //ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½Æ¿ï¿½ ï¿½ï¿½ï¿½ï¿½
	
	List<AttFileVO> getFileList(int bno); //Ã·ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿? ï¿½Ð±ï¿½
	String getFileName(int fno); //ï¿½ï¿½ï¿½ï¿½ ï¿½Ì¸ï¿½ ï¿½ï¿½È¸
	int insertFile(AttFileVO file); //ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	
	void deleteFile(int fno); //Ã·ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	void deleteFileBybno(int bno); //ï¿½ï¿½ï¿½ï¿½ ï¿½Ò¼Óµï¿½ Ã·ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
	
	int addBoardLike(BoardLikeVO boardLike); //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ãµ ï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½
	int getBoardLike(BoardLikeVO boardLike); //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½Ãµ ï¿½ï¿½ï¿? È®ï¿½ï¿½
	
	int addReplyLike(ReplyLikeVO replyLike); //ï¿½ï¿½ï¿? ï¿½ï¿½Ãµï¿½ï¿½ï¿? ï¿½ï¿½ï¿½ï¿½
	int getReplyLike(ReplyLikeVO replyLike); //ï¿½ï¿½ï¿? ï¿½ï¿½Ãµï¿½ï¿½ï¿? È®ï¿½ï¿½
	
	int addBoardView(BoardViewVO boardView);
	int getBoardView(BoardViewVO boardView);
}