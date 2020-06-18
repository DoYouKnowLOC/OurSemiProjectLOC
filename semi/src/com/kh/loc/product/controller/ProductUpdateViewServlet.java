package com.kh.loc.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.product.model.service.ProductService;
import com.kh.loc.product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateViewServlet
 */
@WebServlet("/pdUpView.pd")
public class ProductUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pdcode = request.getParameter("pdcode");
		
		Product pd = new ProductService().selectOne(pdcode);
		String page = "";
		
		if(pd != null) {
			request.setAttribute("product", pd);
			page = "views/product/productUpdate.jsp";
		} else {
			request.setAttribute("error-msg", "게시글 상세 보기 실패");
			page = "views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
