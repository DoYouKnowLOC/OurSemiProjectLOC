package com.kh.loc.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.loc.board.model.vo.Board;

import static com.kh.loc.common.JDBCTemplate.*;

public class BoardDAO {
   
   private Properties prop;
   
   public BoardDAO() {
      prop = new Properties();
      
      String filePath = BoardDAO.class
                  .getResource("/config/board.properties")
                  .getPath();
      
      try {
         prop.load(new FileReader(filePath));
      } catch (IOException e) {

         e.printStackTrace();
      }
   }

   public int getListCount(Connection con,int currentPage, int limit, String condition, String keyword) {
      int result = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("listCount");
      
      try {
         pstmt = con.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            result = rset.getInt(1);
         }
         
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return result;
   }

   public ArrayList<Board> selectList(Connection con, int currentPage, int limit) {
      ArrayList<Board> list = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectList");
      
      try {
         pstmt = con.prepareStatement(sql);
         
         // 1. 마지막 행의 번호
         // 2. 첫 행의 번호 
         int startRow = (currentPage - 1) * limit + 1; 
         int endRow = startRow + limit- 1;
         
         pstmt.setInt(1, endRow);
         pstmt.setInt(2, startRow);
         
         rset = pstmt.executeQuery();
         
         list = new ArrayList<Board>();
         
         while(rset.next()) {
            Board b = new Board();
            
            b.setBno(rset.getInt("BNO"));
            b.setBtype(rset.getInt("BTYPE"));
            b.setBtitle(rset.getString("BTITLE"));
            b.setBcontent(rset.getString("BCONTENT"));
            b.setBwriter(rset.getString("NAME"));
            b.setBcount(rset.getInt("BCOUNT"));
            b.setBdate(rset.getDate("BDATE"));
            b.setBoardfile(rset.getString("BOARDFILE"));
            
            list.add(b);
         }
         
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return list;
   }

   public int insertBoard(Connection con, Board b) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("insertBoard");
      
      try {
         pstmt = con.prepareStatement(sql);
         
         pstmt.setString(1, b.getBtitle());
         pstmt.setString(2, b.getBcontent());
         pstmt.setString(3, b.getBwriter());
         pstmt.setString(4, b.getBoardfile());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }


   public Board selectOne(Connection con, int bno) {
      Board b = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      
      String sql = prop.getProperty("selectOne");
      
      try {
         pstmt=con.prepareStatement(sql);
         
         pstmt.setInt(1, bno);
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            b = new Board();
            
            b.setBno(rset.getInt("bno"));
            b.setBtype(rset.getInt("btype"));
            b.setBtitle(rset.getString("btitle"));
            b.setBcontent(rset.getString("bcontent"));
            b.setBwriter(rset.getString("name"));
            b.setUserId(rset.getString("bwriter"));
            b.setBcount(rset.getInt("bcount"));
            b.setBoardfile(rset.getString("boardfile"));
            b.setBdate(rset.getDate("bdate"));
         }
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return b;
   }
   
   public int updateBoard(Connection con, Board b) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("updateBoard");
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, b.getBtitle());
         pstmt.setString(2, b.getBcontent());
         pstmt.setString(3, b.getBoardfile());
         pstmt.setInt(4, b.getBno());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      
      }finally {
         close(pstmt);
         
      }
      
      return result;
   }


   public int deleteBoard(Connection con, int bno) {
      int result = 0;
      PreparedStatement pstmt = null;
      
      String sql = prop.getProperty("deleteBoard");
      
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, bno);
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      
      }finally {
         close(pstmt);
      }
      
      return result;
   }

   public ArrayList<Board> selectTop5(Connection con) {
      ArrayList<Board> list = null;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectTop5");

      try {
         pstmt = con.prepareStatement(sql);
                  
         rset = pstmt.executeQuery();
         
         list = new ArrayList<Board>();
         
         while(rset.next()) {
            Board b = new Board();
            
            b.setBno(rset.getInt("BNO"));
            b.setBtype(rset.getInt("BTYPE"));
            b.setBtitle(rset.getString("BTITLE"));
            b.setBcontent(rset.getString("BCONTENT"));
            b.setBwriter(rset.getString("NAME"));
            b.setBcount(rset.getInt("BCOUNT"));
            b.setBdate(rset.getDate("BDATE"));
            b.setBoardfile(rset.getString("BOARDFILE"));
            
            list.add(b);
         }
         
      } catch (SQLException e) {

         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }

      
      return list;
   }
   
	public int updateReadCount(Connection con, int bno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateReadCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	public int getListCount(Connection con) {
	      int result = 0;
	      PreparedStatement pstmt = null;
	      ResultSet rset = null;
	      
	      String sql = prop.getProperty("listCount");
	      
	      try {
	         pstmt = con.prepareStatement(sql);
	         
	         rset = pstmt.executeQuery();
	         
	         if(rset.next()) {
	            result = rset.getInt(1);
	         }
	         
	      } catch (SQLException e) {

	         e.printStackTrace();
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return result;
	   }

	public ArrayList<Board> searchList(Connection con, String condition, String keyword,int currentPage,int limit) {
		ArrayList<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;

		switch(condition) {
		case "writer" : // 작성자로 검색할 경우
			sql = prop.getProperty("searchWriterBoard");
			break;
		case "title" : // 제목으로 검색할 경우
			sql = prop.getProperty("searchTitleBoard");
			break;
		case "content" : // 내용으로 검색할 경우
			sql = prop.getProperty("searchContentBoard");
			break;
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			
			int startRow = (currentPage - 1 ) * limit + 1;
			int endRow = startRow + limit - 1;
			
			pstmt.setString(1, keyword);
			pstmt.setInt(2, endRow);
			pstmt.setInt(3, startRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Board>();

			while(rset.next()) {
				Board b = new Board();
			     
				b.setBno(rset.getInt("BNO"));
				b.setBtype(rset.getInt("BTYPE"));
				b.setBtitle(rset.getString("BTITLE"));
				b.setBcontent(rset.getString("BCONTENT"));
				b.setBwriter(rset.getString("BWRITER"));
				b.setBcount(rset.getInt("BCOUNT"));
				b.setBdate(rset.getDate("BDATE"));
				b.setBoardfile(rset.getString("BOARDFILE"));
				
				list.add(b);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}

	public int getSelectListCount(Connection con, String condition, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "writer" : // 작성자로 검색할 경우
			sql = prop.getProperty("searchWriterBoardCount");
			break;
		case "title" : // 제목으로 검색할 경우
			sql = prop.getProperty("searchTitleBoardCount");
			break;
		case "content" : // 내용으로 검색할 경우
			sql = prop.getProperty("searchContentBoardCount");
			break;
		}
		
	      try {
		         pstmt = con.prepareStatement(sql);
		         
		         pstmt.setString(1, keyword);
		         
		         rset = pstmt.executeQuery();
		         
		         if(rset.next()) {
		            result = rset.getInt(1);
		         }
		         
		      } catch (SQLException e) {

		         e.printStackTrace();
		      } finally {
		         close(rset);
		         close(pstmt);
		      }
		      
		      return result;
	}
   


}