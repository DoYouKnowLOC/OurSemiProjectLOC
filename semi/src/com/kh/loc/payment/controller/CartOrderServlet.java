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
import com.kh.loc.payment.model.service.PaymentService;
import com.kh.loc.payment.model.vo.Cart;
import com.kh.loc.payment.model.vo.PdOrder;

/**
 * Servlet implementation class CartOrderServlet
 */
@WebServlet("/cOrder.o")
public class CartOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartOrderServlet() {
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
        	
        	ArrayList<Cart> clist = cs.listCart2(userId);
        	PdOrder po = null;
        	
        	String pdcode = null;
        	int count = 0;
        	int oTotalPrice = 0;//금액 합계
        	
        	ArrayList<PdOrder> poList = new ArrayList<PdOrder>();
    		for(Cart c : clist) {
    			pdcode = c.getPdCode();
    			count = c.getCount();
    			po = new PdOrder(pdcode, userId, count);
    			poList.add(po);
    		}
    		
    		oTotalPrice = cs.totalMoney(userId);
    		
        	
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
