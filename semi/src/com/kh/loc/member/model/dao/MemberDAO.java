package com.kh.loc.member.model.dao;

import static com.kh.loc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.vo.Member;
import com.kh.loc.member.model.vo.Member2;

public class MemberDAO {
	// SQL을 별도로 보관하는 Properties 객체 생성하기
	private Properties prop;
	
	public MemberDAO() {
		prop = new Properties();
		
		String filePath = MemberDAO.class
				          .getResource("/config/member.properties")
				          .getPath();
		
		// System.out.println(filePath);
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertMember(Connection con, Member m) throws MemberException {
		// 결과 확인을 위한 변수 result 생성
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, 2);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
//			pstmt.setString(4, m.getGender());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6,  m.getEmail());
			pstmt.setString(7,  m.getBirth());
//			pstmt.setInt(7, m.getAge());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new MemberException("[DAO 에러] : " + e.getMessage());
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection con, Member m) throws MemberException {
		// 1. 사용할 변수 선언
		Member result = null; // 결과를 담을 객체
		PreparedStatement pstmt = null; // 쿼리 실행할 객체
		ResultSet rset = null; // Select 결과를 받아 올 객체
		
		String sql = prop.getProperty("selectMember");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			
			rset = pstmt.executeQuery();
			
			System.out.println("result 조회 전 : " + result);
			
			if(rset.next()) {
				result = new Member();
				
				result.setUserId(m.getUserId());
				result.setUserPwd(m.getUserPwd());
				result.setUserName(rset.getString("NAME"));
//				result.setGender(rset.getString("GENDER"));
//				result.setAge(rset.getInt(5));
				result.setPhone(rset.getString("phone"));
				result.setAddress(rset.getString("address"));
				result.setEmail(rset.getString("email"));
				result.setBirth(rset.getString("birth"));
//				result.setHobby(rset.getString("hobby"));
			}
			
			System.out.println("result 조회 후 : " + result);
			
		} catch (SQLException e) {

			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
			
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return result; // 실수 많이 하니까 주의하기! ㅇㅁㅇ)/
	}

	public int updateMember(Connection con, Member m) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}		
		
		return result;
	}

	public int deleteMember(Connection con, String userId, String userPwd, String name) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, name);
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int idDupCheck(Connection con, String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idDupCheck");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
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

	public int idSearch(Connection con, String id, String email) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("idSearch");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			
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

	
	
// 비번찾기 초기 입력
	public Member memberSearch(Connection con, Member m) throws MemberException {
		Member result = null; // 결과를 담을 객체
		PreparedStatement pstmt = null; // 쿼리 실행할 객체
		ResultSet rset = null; // Select 결과를 받아 올 객체
		
		String sql = prop.getProperty("PwSearch");
				
		try {
					
			pstmt = con.prepareStatement(sql);
					
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
					
			rset = pstmt.executeQuery();
					
			System.out.println("result 조회 전 : " + result);
					
			if(rset.next()) {
				result = new Member();
				
				
				result.setUserId(m.getUserId());
				result.setUserPwd(rset.getString("password"));
				result.setUserName(rset.getString("NAME"));
//				result.setGender(rset.getString("GENDER"));
//				result.setAge(rset.getInt(5));
				result.setPhone(m.getPhone());
				result.setAddress(rset.getString("address"));
				result.setEmail(m.getEmail());
				result.setBirth(rset.getString("birth"));
//				result.setHobby(rset.getString("hobby"));
			}
					
			System.out.println("result 조회 후 : " + result);
					
		} catch (SQLException e) {

			e.printStackTrace();
					
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
					
		} finally {
			close(rset);
			close(pstmt);
			}	
				
			return result; 
		
	}

	public String memberSearch(Connection con, String userName, String birth) throws MemberException {
		String result = null; // 결과를 담을 객체
		PreparedStatement pstmt = null; // 쿼리 실행할 객체
		ResultSet rset = null; // Select 결과를 받아 올 객체
		
		String sql = prop.getProperty("selectMemberID");
				
		try {
					
			pstmt = con.prepareStatement(sql);
					
			pstmt.setString(1, userName);
			pstmt.setString(2, birth);
					
			rset = pstmt.executeQuery();
					
			System.out.println("result 조회 전 : " + result);
					
			if(rset.next()) {
				result = rset.getString(1);
			}
					
			System.out.println("result 조회 후 : " + result);
					
		} catch (SQLException e) {

			e.printStackTrace();
					
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
					
		} finally {
			close(rset);
			close(pstmt);
		}	
			
		return result;
	}

	public int searchPwd(Connection con, String userId, String email, String phone) throws MemberException {
		int result = 0; // 결과를 담을 객체
		PreparedStatement pstmt = null; // 쿼리 실행할 객체
		ResultSet rset = null; // Select 결과를 받아 올 객체
		
		String sql = prop.getProperty("PwSearch");
				
		try {
					
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			rset = pstmt.executeQuery();
					
			System.out.println("result 조회 전 : " + result);
					
			if(rset.next()) {
				result = rset.getInt(1);
			}
					
			System.out.println("result 조회 후 : " + result);
					
		} catch (SQLException e) {

			e.printStackTrace();
					
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
					
		} finally {
			close(rset);
			close(pstmt);
		}	
			
		return result;
	}

	public int updateMemberPwd(Connection con, String userId, String pwd) throws MemberException {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, pwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new MemberException("[DAO 에러 발생] : " + e.getMessage());
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
