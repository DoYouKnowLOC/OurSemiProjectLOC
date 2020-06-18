package com.kh.loc.payment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.loc.member.model.vo.Member;
import com.kh.loc.payment.model.service.CartService;
import com.kh.loc.payment.model.vo.Cart;
import com.kh.loc.product.model.vo.Product;

/**
 * Servlet implementation class CartListServlet
 */
@WebServlet("/cList.cart")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 9999999L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Object> hmap = new HashMap<>();

		CartService cs = new CartService();
		
		HttpSession session = request.getSession();
		
		Member m = (Member)session.getAttribute("member");
		String userId = m.getUserId();
//		System.out.println("userId : " + userId);

        if(userId != null) { 
            //로그인한 상태이면 실행
            ArrayList<Cart> list = cs.listCart2(userId); //장바구니 목록
            int sumMoney = cs.totalMoney(userId);//금액 합계
            int fee = sumMoney >= 50000 ? 0 : (sumMoney == 0) ? 0 : 2500; 
            
            //배송료 계산
            //50000원이 넘으면 배송료가 0원, 안넘으면 2500원
            
            //hasp map에 장바구니에 넣을 각종 값들을 저장함
            hmap.put("sumMoney", sumMoney);
            hmap.put("fee", fee); //배송료
            hmap.put("sum", sumMoney+fee); //전체 금액
            hmap.put("list", list); //장바구니 목록
            hmap.put("count", list.size()); //레코드 갯수
            
            String page = "";
    		if(hmap.get("list") != null) {
    			request.setAttribute("hmap", hmap);
    			page = "views/product/cart.jsp";
    		} else {
    			request.setAttribute("error-msg", "장바구니 목록 조회 실패");
    			page = "views/common/errorPage.jsp";
    		}
    		
    		request.getRequestDispatcher(page).forward(request, response);
            
        } else { //로그인하지 않은 상태
        	
        	response.sendRedirect("/loc/pdMenu.pd");
			System.out.println("로그인 필요합니다.");
            //로그인을 하지 않았으면 로그인 페이지로 이동시킨다.
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
