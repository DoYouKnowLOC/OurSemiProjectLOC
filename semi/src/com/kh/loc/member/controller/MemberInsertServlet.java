package com.kh.loc.member.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.member.exception.MemberException;
import com.kh.loc.member.model.service.MemberService;
import com.kh.loc.member.model.vo.Member;



/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/mInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 서비스 연동 서블릿
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		try {	
			// 2. 입력한 회원 정보 받아오기
			String userId = request.getParameter("userId");
			String userPwd = request.getParameter("userPwd");
			String userName = request.getParameter("userName");
			String gender = request.getParameter("gender");
		//		int age = Integer.parseInt(request.getParameter("age"));
			String email = request.getParameter("email");
			String phone = request.getParameter("phone"); // 01012345678
			// String address = request.getParameter("address");
		
			// 010-1111-2222
		//		String phone = request.getParameter("tel1") + "-"
		//					 + request.getParameter("tel2") + "-"
		//					 + request.getParameter("tel3");
			
			String address = request.getParameter("zipCode") + "/ "
					     + request.getParameter("address1") + "/ "
					     +request.getParameter("address2");
			
			// String[] hobby = request.getParameterValues("hobby");
			// 배열을 문자열로 만들기
			// why? 결국 들어갈 데이터베이스에 배열은 추가할 수 없으니까!
		//		String hobby = String.join(", ", request.getParameterValues("hobby"));
			String birth = request.getParameter("birth");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date day = sdf.parse(birth);
			sdf.applyPattern("yyyyMMdd");
			birth = sdf.format(day);
			
			// VO 사용하기
			// 회원 가입 시 정보를 하나로 묶어 전달하는 역할
			Member m = new Member(userId, userPwd, userName, gender,
					               email, phone, address, birth);
		
			System.out.println("가입 정보 확인 : " + m);
			
			// 회원 가입 실행
			MemberService ms = new MemberService();
		
			
			ms.insertMember(m);
		
			System.out.println("회원 가입 성공!");
			response.sendRedirect("index.jsp");
			
		} catch(ParseException | MemberException e) {
			
			System.out.println("회원 가입 실패!");
			RequestDispatcher view
			   = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			request.setAttribute("error-msg", "회원 가입 실패");
			request.setAttribute("exception", e);
			
			view.forward(request, response);
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
