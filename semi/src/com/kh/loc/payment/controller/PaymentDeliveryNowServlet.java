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
import com.kh.loc.payment.model.service.PaymentService;
import com.kh.loc.payment.model.vo.Cart;
import com.kh.loc.payment.model.vo.Payment;

/**
 * Servlet implementation class PaymentDeliveryNowServlet
 */
@WebServlet("/pmDelivey.pm")
public class PaymentDeliveryNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentDeliveryNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Object> hmap = new HashMap<>();
		
		HttpSession session = request.getSession();
		Member m = (Member)session.getAttribute("member");
		String userId = m.getUserId();
		
		PaymentService ps = new PaymentService();
		if(userId != null) { 
            //로그인한 상태이면 실행
            ArrayList<Payment> list = ps.paymentDelList(userId);
            
            Payment pm = null;
            for(Payment p : list) {
            	int money = p.getPdPrice();// 개별 주문 금액
            	int count = p.getCount(); // 개별 주문 개수
            	int sumMoney = money * count;
            	pm = new Payment(sumMoney);
            }
            
            hmap.put("payment", pm);
            //hasp map에 장바구니에 넣을 각종 값들을 저장함
            hmap.put("list", list); //장바구니 목록
            hmap.put("count", list.size()); //레코드 갯수
            
            String page = "";
    		if(hmap.get("list") != null) {
    			request.setAttribute("hmap", hmap);
    			page = "views/product/productDelivery.jsp";
    		} else {
    			request.setAttribute("error-msg", "배송 확인 목록 조회 실패");
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
