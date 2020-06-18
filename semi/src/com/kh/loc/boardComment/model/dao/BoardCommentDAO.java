package com.kh.loc.boardComment.model.dao;

import static com.kh.loc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.loc.boardComment.model.vo.BoardComment;

public class BoardCommentDAO {
	private Properties prop;
	
	public BoardCommentDAO() {
		prop = new Properties();
		
		String filePath = BoardCommentDAO.class
				          .getResource("/config/comment.properties")
				          .getPath();
		try {
			
			prop.load(new FileReader(filePath));
			
		} catch(IOException e) {
			
			e.printStackTrace();
		}
	}

	public int insertComment(Connection con, BoardComment bco) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bco.getBno());
			pstmt.setString(2, bco.getCcontent());
			pstmt.setString(3, bco.getCwriter());
			// 첫 댓글은 참조하는 댓글이 없다.
			// 따라서 refcno 가 0으로 온다.
			// pstmt.setInt(4, bco.getRefcno());
			if(bco.getRefcno() > 0) {
				pstmt.setInt(4,  bco.getRefcno());
			} else {
				pstmt.setNull(4, java.sql.Types.NULL);
			}
			
			pstmt.setInt(5, bco.getClevel());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	public ArrayList<BoardComment> selectList(Connection con, int bno) {
		ArrayList<BoardComment> clist = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			clist = new ArrayList<BoardComment>();
			
			while(rset.next()) {
				BoardComment bco = new BoardComment();
				
				bco.setCno(rset.getInt("cno"));
				bco.setBno(rset.getInt("bno"));
				bco.setCcontent(rset.getString("ccontent"));
				bco.setCwriter(rset.getString("name"));
				bco.setUserid(rset.getString("cwriter"));
				bco.setCdate(rset.getDate("cdate"));
				bco.setRefcno(rset.getInt("ref_cno"));
				bco.setClevel(rset.getInt("clevel"));
				
				clist.add(bco);				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return clist;
	}

	public int updateComment(Connection con, BoardComment bco) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bco.getCcontent());
			pstmt.setInt(2, bco.getCno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}
	
	public int deleteComment(Connection con, int cno) {
		int result  = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComment");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}		
		
		return result;
	}
}


















