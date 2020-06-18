package com.kh.loc.member.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchPwdServlet
 */
@WebServlet("/mSearchPWD.do")
public class MemberSearchPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId= request.getParameter("userId");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberService ms = new MemberService();
		String page = "";
		try {
			int result = ms.searchPwd(userId, email, phone);
			if(result != 0 ) {
				// 해당 회원 승인
				request.setAttribute("userId", userId);
				page="views/member/memberPwUpdate.jsp";
				
				
			} else {
				// 해당 회원 아님
				throw new MemberException("회원 정보 불일치");
			}
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			request.setAttribute("error-msg", "회원 인증 실패!");
			request.setAttribute("exception", e);
			page = "views/common/errorPage.jsp";
		} finally {
			request.getRequestDispatcher(page)
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
