package com.kh.loc.notice.model.dao;

import static com.kh.loc.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.loc.notice.model.vo.Notice;
import com.kh.loc.notice.model.vo.noticePageInfo;

public class NoticeDAO {
	private Properties prop;
	
	public NoticeDAO() {
		prop = new Properties();
		
		String filePath =NoticeDAO.class
						 .getResource("/config/notice.properties")
						 .getPath();
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Notice> selectList(Connection con, int currentPage, int limit) {
		ArrayList<Notice> list = null;
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
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt("NNO"));
				n.setNtitle(rset.getString("NTITLE"));
				n.setNcontent(rset.getString("NCONTENT"));
				n.setNwriter(rset.getString("NWRITER"));
				n.setNcount(rset.getInt("NCOUNT"));
				n.setNdate(rset.getDate("NDATE"));
				
				list.add(n);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}

	public Notice selectOne(Connection con, int nno) {
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				n = new Notice();
				
				n.setNno(rset.getInt("nno"));
				n.setNtitle(rset.getString("ntitle"));
				n.setNcontent(rset.getString("ncontent"));
				n.setNwriter(rset.getString("nwriter"));
				n.setNcount(rset.getInt("ncount"));
				n.setNdate(rset.getDate("ndate"));
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return n;
	}

	public int updateReadCount(Connection con, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateReadCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection con, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setString(3, n.getNwriter());
			pstmt.setDate(4, n.getNdate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result;
	}

	// 기본 데이터 이동 경로 
	//                       ┌--.getParameter()            ┌-- SQL 담당
	// View(.jsp) --> Controller(Servlet) --> Service --> DAO --> DB 
	//										   └-- DAO 메소드 호출 
	//                                             Commit & Rollback
	public int updateNotice(Connection con, Notice n) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, n.getNtitle());
			pstmt.setString(2, n.getNcontent());
			pstmt.setInt(3, n.getNno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result;
	}
	
	public int deleteNotice(Connection con, int nno) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, nno);
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Notice> searchList(Connection con, String condition, String keyword,
																		int currentPage, int limit) {
		ArrayList<Notice> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "writer" : // 작성자로 검색할 경우
			sql = prop.getProperty("searchWriterNotice");
			break;
		case "title" : // 제목으로 검색할 경우
			sql = prop.getProperty("searchTitleNotice");
			break;
		case "content" : // 내용으로 검색할 경우
			sql = prop.getProperty("searchContentNotice");
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
			
			list = new ArrayList<Notice>();

			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt("NNO"));
				n.setNtitle(rset.getString("NTITLE"));
				n.setNcontent(rset.getString("NCONTENT"));
				n.setNwriter(rset.getString("NWRITER"));
				n.setNcount(rset.getInt("NCOUNT"));
				n.setNdate(rset.getDate("NDATE"));
				
				list.add(n);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
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

	public int getSelectListCount(Connection con, String condition, String keyword) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = null;
		
		switch(condition) {
		case "writer" : // 작성자로 검색할 경우
			sql = prop.getProperty("searchWriterNoticeCount");
			break;
		case "title" : // 제목으로 검색할 경우
			sql = prop.getProperty("searchTitleNoticeCount");
			break;
		case "content" : // 내용으로 검색할 경우
			sql = prop.getProperty("searchContentNoticeCount");
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







