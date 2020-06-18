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
 * Servlet implementation class MemberPwServlet
 */
@WebServlet("/MemberPw.do")
public class MemberPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pwd = request.getParameter("re_userPwd");
		String userId = request.getParameter("userId");
		
		System.out.println("변경할 회원 : "+userId);
		System.out.println("변경한 회원 정보 : "+pwd);
		
		MemberService ms = new MemberService();
		
		try {
			ms.updateMemberPwd(userId, pwd);
	
			System.out.println("회원 정보 수정 완료!");
			
			response.sendRedirect("index.jsp");
			
			System.out.println("변경암호" +pwd);
						
		} catch(MemberException e) {
			
			request.setAttribute("error-msg", "회원 정보 수정 중 에러 발생!");
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
