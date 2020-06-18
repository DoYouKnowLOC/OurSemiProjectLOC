package com.kh.loc.payment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.loc.member.model.vo.Member;
import com.kh.loc.payment.model.service.CartService;
import com.kh.loc.payment.model.vo.Cart;

/**
 * Servlet implementation class CartCheckServlet
 */
@WebServlet("/cartChk.cart")
public class CartCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		String userId = m.getUserId();
		String pdCode = request.getParameter("pdcode");
		
		System.out.println(userId);
		System.out.println(pdCode);
		
		
		CartService cs = new CartService();
		int result = cs.cartCheck(userId, pdCode);
		
		System.out.println("result : " + result);
		
		
		Cart cart = new Cart();
		
		if(result >= 1) {
			System.out.println("이미 등록된 상품 롸잇뎃");
			response.sendRedirect("/loc/cList.cart");
		} else if(result == 0) {
			System.out.println("없는 상품 예에");
			request.setAttribute("cart", cart);
			request.getRequestDispatcher("/cInsert.cart").forward(request, response);
		} else {
			request.setAttribute("error-msg", "장바구니 등록 실패");
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
