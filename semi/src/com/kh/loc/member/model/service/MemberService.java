package com.kh.loc.member.model.service;

import static com.kh.loc.common.JDBCTemplate.close;

import static com.kh.loc.common.JDBCTemplate.commit;
import static com.kh.loc.common.JDBCTemplate.getConnection;
import static com.kh.loc.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.dao.MemberDAO;
import com.kh.loc.member.model.vo.Member;

public class MemberService {
	private Connection con;
	private MemberDAO mDAO = new MemberDAO();
	
	public int insertMember(Member m) throws MemberException {
		con = getConnection();
		int result = mDAO.insertMember(con, m);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public Member selectMember(Member m) throws MemberException {
		con = getConnection();
		Member result = mDAO.selectMember(con, m);
		
		close(con);
		
		if(result == null) throw new MemberException("[로그인 실패] : ID나 PW를 확인하세요.");
		
		return result;
	}

	public int updateMember(Member m) throws MemberException {
		con = getConnection();
		
		int result = mDAO.updateMember(con, m);
		
		if( result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int deleteMember(String userId, String userPwd, String name) throws MemberException {
		con = getConnection(); 
		int result = mDAO.deleteMember(con, userId, userPwd, name);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
	
	public int idDupCheck(String id) {
		con = getConnection();
		
		int result = mDAO.idDupCheck(con, id);
		
		close(con);
		
		return result;
	}
	
	public Member memberSearch(Member m) throws MemberException {
		con = getConnection();
		
		Member result = mDAO.memberSearch(con, m);
		
		close(con);
		
		if(result == null) throw new MemberException("[정보확인 실패] : 입력정보를 다시 확인 하세요.");
		
		return result;
		
				
	}

	public String searchId(String userName, String birth) throws MemberException {
		con = getConnection();
		
		String result = mDAO.memberSearch(con, userName, birth);
		
		close(con);
		
		if(result == null) throw new MemberException("[정보확인 실패] : 입력정보를 다시 확인 하세요.");
		
		return result;
	}

	public int searchPwd(String userId, String email, String phone) throws MemberException {
		con = getConnection();
		
		int result = mDAO.searchPwd(con, userId, email, phone);
		
		close(con);
		
		return result;
	}

	public int updateMemberPwd(String userId, String pwd) throws MemberException {
		con = getConnection();
		
		int result = mDAO.updateMemberPwd(con, userId, pwd);
		
		if( result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}


	
}
