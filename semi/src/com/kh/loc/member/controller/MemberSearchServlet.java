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
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/mSearch.do")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String id = request.getParameter("userId");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Member m = new Member(id, email, phone);
		
		MemberService ms = new MemberService();
		
		try {
			m = ms.memberSearch(m);
			
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			response.sendRedirect("views/member/memberPwUpdate.jsp");
			
		} catch(MemberException e) {
			
			request.setAttribute("error-msg", "회원 로그인 실패!");
			request.setAttribute("exception", e);
			request.getRequestDispatcher("views/common/errorPage.jsp")
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
