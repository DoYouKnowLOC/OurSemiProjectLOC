package com.kh.loc.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.loc.board.model.dao.BoardDAO;
import com.kh.loc.board.model.vo.Board;

import static com.kh.loc.common.JDBCTemplate.*;

public class BoardService {
   private Connection con;
   private BoardDAO bDAO = new BoardDAO();
   
  
   public ArrayList<Board> selectList(int currentPage, int limit) {
      con = getConnection();
      
      ArrayList<Board> list = bDAO.selectList(con, currentPage, limit);
      
      close(con);
      
      return list;
   }

   public int insertBoard(Board b) {
      con = getConnection();
      int result = bDAO.insertBoard(con, b);
      
      if(result > 0) commit(con);
      else rollback(con);
      
      close(con);
      
      return result;
   }

   public Board selectOne(int bno) {
      con = getConnection();
      Board b = bDAO.selectOne(con, bno);
      
      if(b != null) {
			// 조회수 1증가
			int result = bDAO.updateReadCount(con, bno);
			
			if(result > 0) commit(con);
			else rollback(con);
		}
      
      close(con);
      
      return b;
   }
   
   public int updateBoard(Board b) {
      con = getConnection();
      int result = bDAO.updateBoard(con,b);
      
      if(result > 0) commit(con);
      else rollback(con);
      
      close(con);
      
      return result;
   }

   public int deleteBoard(int bno) {
      con = getConnection();
      int result = bDAO.deleteBoard(con, bno);
      
      if(result > 0) commit(con);
      else rollback(con);
      
      close(con);
      
      return result;
   }

   public ArrayList<Board> selectTop5() {
      con = getConnection();
      
      ArrayList<Board> list = bDAO.selectTop5(con);
      
      close(con);
      
      return list;
   }

	public ArrayList<Board> searchBoard(String condition, String keyword, int currentPage, int limit) {
		con = getConnection();
		ArrayList<Board> list = null;
		
		// 컨디션에 뭐라도 내용이 들어왔다면
		// 조건부 검색 실시(제목, 내용 등)
		if(condition != null && (condition.length() > 0 && ! condition.contentEquals("null"))) {
			list = bDAO.searchList(con, condition, keyword,currentPage,limit);
			
		} else { // 검색 조건이 없다면 전체 검색을 실시
			list = bDAO.selectList(con, currentPage, limit);
		}
		
		close(con);
		
		return list;
	}

	public int getListCount(int currentPage, int limit, String condition, String keyword) {
		 con = getConnection();
	      
	      int result = bDAO.getListCount(con,currentPage,limit,condition,keyword);
	      
	      close(con);
	      
	      return result;
	}
	

	public int getSearchListCount(int currentPage, int limit, String condition, String keyword) {
		con = getConnection();
		
		int result = 0;
		
		if(condition != null && (condition.length() > 0 && ! condition.contentEquals("null"))) {
			
			result = bDAO.getSelectListCount(con, condition, keyword);
			
		} else {
			
			result = bDAO.getListCount(con);
			
		}
		
		close(con);
		
		return result;
	}


}