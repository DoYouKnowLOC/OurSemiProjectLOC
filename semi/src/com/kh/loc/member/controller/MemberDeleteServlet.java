package com.kh.loc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.service.MemberService;
import com.kh.loc.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mDelete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 회원 아이디 가져오기
		HttpSession session = request.getSession(false);
		
		String userId = ((Member)session.getAttribute("member")).getUserId();
		String userPwd = request.getParameter("userPwd");
		String name = request.getParameter("userName");
		
		System.out.println("회원 기존 아이디  : " + userId);
		System.out.println("회원 입력 비밀번호  : " + userPwd);
		System.out.println("회원 입력 이름  : " + name);
		
		MemberService ms = new MemberService();
		
		try {
			int result = ms.deleteMember(userId, userPwd, name);
			
			if(result > 0) {
				System.out.println("회원 탈퇴 성공!");
				session.invalidate();
				
				response.sendRedirect("index.jsp");
				
			}else {
				System.out.println("회원 탈퇴 실패!");
				throw new MemberException("[탈퇴 실패] : PW를 확인하세요.");
			}

		} catch(MemberException e) {
			request.setAttribute("error-msg", "회원 탈퇴 수행 중 에러 발생!");
			request.setAttribute("exception", e);
			
			request
			.getRequestDispatcher("views/common/errorPage.jsp")
			.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
