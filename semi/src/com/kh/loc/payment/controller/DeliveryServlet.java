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
import com.kh.loc.payment.model.service.PaymentService;
import com.kh.loc.payment.model.vo.Delivery;
import com.kh.loc.payment.model.vo.PdOrder;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/payment.p")
public class DeliveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliveryServlet() { super(); }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 1. 인코딩
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");

			PaymentService os = new PaymentService();
			
			HttpSession session = request.getSession();
			Member m = (Member)session.getAttribute("member");
			String userId = m.getUserId();
			
			ArrayList<PdOrder> poList = new ArrayList<PdOrder>();
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			
			String pdCode = "";
			int count = 0;
			int oNo = 0;
			int listCount = Integer.parseInt(request.getParameter("listCount"));
			System.out.println("listCount" + listCount);
			
			int result = 0;
			
			for(int i = 0; i < listCount; i++) {
				pdCode = request.getParameter("pPdcode-"+i);
				count = Integer.parseInt(request.getParameter("pCount-"+i));
				
				PdOrder o = new PdOrder(pdCode, userId, totalPrice, count);
				poList.add(o);
				
//					System.out.println("poList 출력 : " + poList);
				
			}
			System.out.println("poList 출력 : " + poList);
			// 2. 입력한 회원 정보 받아오기
			String dName = request.getParameter("dName");
			String dPhone = request.getParameter("dPhone");
			String dAddress = request.getParameter("zipCode") + "/"
					+ request.getParameter("address1") + "/"
					+request.getParameter("address2");
			
			Delivery d = new Delivery(pdCode ,oNo, dAddress, dPhone, dName);
			
			result = os.insertTestOrder(poList, d, listCount);
			CartService cs = new CartService();
			if(result > 0) {
				System.out.println("구매하기 성공");
				response.sendRedirect("views/product/payComplete.jsp");
				int result2 = cs.deleteCart(userId);
				if(result2 > 0) {
					System.out.println("장바구니 삭제 성공");
				} else {
					request.setAttribute("error-msg", "장바구니 삭제를 실패하였습니다.");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error-msg", "구매하기에 실패하였습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
