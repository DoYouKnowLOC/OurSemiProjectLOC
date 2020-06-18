package com.kh.loc.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberSearchIdServlet
 */
@WebServlet("/mSearchID.do")
public class MemberSearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "";
		
		try {
			String userName = request.getParameter("userName");
			String birth = request.getParameter("birth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date day = sdf.parse(birth);
			sdf.applyPattern("yyyyMMdd");
			birth = sdf.format(day);
			
			MemberService ms = new MemberService();
			
			String userId = ms.searchId(userName, birth);
			
			
			request.setAttribute("userId", userId);
			page = "views/member/memberSearch.jsp";
				
			System.out.println();
		} catch (ParseException | MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			request.setAttribute("error-msg", "회원 로그인 실패!");
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
