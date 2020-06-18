package com.kh.loc.payment.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class CartInsertServlet
 */
@WebServlet("/cInsert.cart")
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		Cart cart = new Cart();
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		String mUserId = m.getUserId();
		
		CartService cs = new CartService();
		
		int result = 0;
		
		if(mUserId == null) {
			response.sendRedirect("/loc/pdMenu.pd");
			System.out.println("로그인 필요합니다.");
		}
		
		String pdCode = request.getParameter("pdcode");
		int pdPrice = Integer.parseInt(request.getParameter("price"));
		int count = Integer.parseInt(request.getParameter("count"));
		String pdName = request.getParameter("pdname");
		String pdPreview = request.getParameter("pdpreview");
		
		cart.setUserId(mUserId);
		cart.setPdCode(pdCode);
		cart.setCount(count);
		cart.setPdName(pdName);
		cart.setPdPrice(pdPrice);
		cart.setPdPreview(pdPreview);
		cart.setTotalPrice(pdPrice * count);
		
		result = cs.insertCart(cart);
		
		if(result > 0) {
			System.out.println("장바구니로 간닷!");
			response.sendRedirect("/loc/cList.cart");

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
