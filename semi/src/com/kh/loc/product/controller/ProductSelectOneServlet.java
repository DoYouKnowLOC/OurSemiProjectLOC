package com.kh.loc.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.product.model.service.ProductService;
import com.kh.loc.product.model.vo.Product;

/**
 * Servlet implementation class ProductSelectOneServlet
 */
@WebServlet("/pdSelectOne.pd")
public class ProductSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pdcode = request.getParameter("pdcode");
		System.out.println(pdcode);
		Product pd = new ProductService().selectOne(pdcode);
		
		String page = "";
		
		if(pd != null) {
			request.setAttribute("product", pd);
			page = "views/product/productDetail.jsp";
		} else {
			request.setAttribute("error-msg", "레시피 상세보기 실패~!");
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
