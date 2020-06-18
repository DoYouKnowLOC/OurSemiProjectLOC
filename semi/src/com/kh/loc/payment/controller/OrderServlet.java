package com.kh.loc.payment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.loc.member.model.vo.Member;
import com.kh.loc.payment.model.service.CartService;
import com.kh.loc.payment.model.vo.PdOrder;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order.o")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		HashMap<String, Object> hmap = new HashMap<>();

		HttpSession session = request.getSession();
		
		Member m = (Member)session.getAttribute("member");
		String userId = m.getUserId();

        if(userId != null) { 
            //로그인한 상태이면 실행
        	
        	CartService cs = new CartService();
        	
        	PdOrder po = null;
        	
        	ArrayList<PdOrder> poList = new ArrayList<PdOrder>();
        	
    		int price = Integer.parseInt(request.getParameter("price"));
    		int count = Integer.parseInt(request.getParameter("count"));
    		String pdcode = request.getParameter("pdcode");
    		int oTotalPrice = price * count;
    		po = new PdOrder(pdcode, userId, oTotalPrice, count);
    		poList.add(po);
    		System.out.println("입력정보확인11?! : " + po);
        	
        	int fee = oTotalPrice >= 50000 ? 0 : (oTotalPrice == 0) ? 0 : 2500;
        	
        	hmap.put("sumMoney", oTotalPrice); // 금액 합계
            hmap.put("fee", fee); //배송료
            hmap.put("sum", oTotalPrice + fee); //전체 금액
            hmap.put("poList", poList); // 주문 목록
            hmap.put("count", poList.size()); //레코드 갯수
        	
            String page = "";
    		if(hmap.get("poList") != null) {
    			request.setAttribute("hmap", hmap);
    			page = "views/product/productPurchase.jsp";
    		} else {
    			request.setAttribute("error-msg", "주문 페이지 이동 실패");
    			page = "views/common/errorPage.jsp";
    		}
    		
    		request.getRequestDispatcher(page).forward(request, response);
        	
        	
        } else { //로그인하지 않은 상태
        	
        	response.sendRedirect("/loc/pdMenu.pd");
			System.out.println("로그인 필요합니다.");
            //로그인을 하지 않았으면 로그인 페이지로 이동시킨다.
        }
		
		
//		// 1. 인코딩
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=utf-8");
//		
//		// 2. 입력한 회원 정보 받아오기
//		int price = Integer.parseInt(request.getParameter("price"));
//		int count = Integer.parseInt(request.getParameter("count"));
//		
//		HttpSession session = request.getSession();
//		Member m = (Member)session.getAttribute("member");
//		String userId = m.getUserId();
//		
//		String pdcode = request.getParameter("pdcode");	
//		
//		int oTotalPrice = price * count;
//		
//		System.out.println(oTotalPrice);
//		
//		PdOrder o = new PdOrder(pdcode, userId, oTotalPrice, count);
//		
//		System.out.println("입력정보확인 : " + o);
//		
//		// 회원 가입 성공!S
//		System.out.println("입력성공");
//		
//		request.setAttribute("order", o);
//		request.getRequestDispatcher("views/product/productPurchase.jsp").forward(request, response);
//		response.sendRedirect("views/product/productPurchase.jsp");
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
