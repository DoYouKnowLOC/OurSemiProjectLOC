package com.kh.loc.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.loc.product.model.service.ProductService;
import com.kh.loc.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/pdUpdate.pd")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService ps = new ProductService();
		
		if (!ServletFileUpload.isMultipartContent(request)) {

			request.setAttribute("error-msg", "멀티 파트 형식으로 보내주세요~");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}

		// 최대 크기 설정
		int maxSize = 1024 * 1024 * 10;

		// 저장할 경로 설정
		String root = request.getServletContext().getRealPath("/resources/images");
		String savePath = root + "/productUploadFiles/";

		MultipartRequest mre = new MultipartRequest(
										request
									  , savePath
									  , maxSize
									  , "UTF-8"
									  , new DefaultFileRenamePolicy()
		);
		
		String pdCode = mre.getParameter("pdcode"); 
		int pdPrice = Integer.parseInt(mre.getParameter("pdPrice"));
		String pdName = mre.getParameter("pdName");
		String pdCont =mre.getParameter("pdCont");
		String pdSubCont =mre.getParameter("pdSubCont");
		
		Product pd = ps.selectOne(pdCode);
		
		pd.setPdName(pdName);
		pd.setPdPrice(pdPrice);
		pd.setPdCont(pdCont);
		pd.setPdSubCont(pdSubCont);
		
		int result = ps.updateProduct(pd);
		
		if(result > 0) {
			response.sendRedirect("/loc/pdMenu.pd");
		} else {
			request.setAttribute("error-msg", "사진 게시글 등록 실패");
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
