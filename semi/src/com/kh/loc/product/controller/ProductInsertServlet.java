package com.kh.loc.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.loc.common.MyRenamePolicy;
import com.kh.loc.product.model.service.ProductService;
import com.kh.loc.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class ProductInsertServlet
 */
@WebServlet("/pdInsert.pd")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		
		int pdPrice = Integer.parseInt(mre.getParameter("pdPrice"));
		String pdName = mre.getParameter("pdName");
		String pdCont =mre.getParameter("pdCont");
		String pdSubCont =mre.getParameter("pdSubCont");
//		String fileName = mre.getFilesystemName("pdPreview");
		Product pd = null;
		
		// 파일 이름 변경하기
		String originFileName = mre.getFilesystemName("pdPreview");
		System.out.println(originFileName);
		if (originFileName != null) {
			// 업로도된 파일명을 "년월일시분초.확장자" 로 변경함
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			
			long currentTimes = System.currentTimeMillis();
			int randomNumber = (int)(Math.random() * 10000);
			// 확장자 명만 제외하고 이름 바꾸기
			String name = originFileName;
			String body = ""; // 파일 명 (test.jpg --> test)
			String ext = ""; // 확장자 (test.jpg --> .jpg)
			
			int dot = name.lastIndexOf('.'); // test.jpg.zip
			
			if(dot != -1) {
				// 확장자가 있다면!
				body = name.substring(0, dot);
				ext = name.substring(dot);
			} else {
				// 확장자가 없다면
				body = name;
			}
			
			String renameFileName = sdf.format(new Date(currentTimes))
                    + "_" + randomNumber + ext;

			// 파일명 바꾸기하려면 File 객체의 renameTo() 사용함
			File originFile = new File(savePath + "/" + originFileName);
			File renameFile = new File(savePath + "/" + renameFileName);

			// 파일이름 바꾸기 실행 >> 실패할 경우 직접 바꾸기함
			// 새 파일만들고 원래 파일내용 읽어서 복사 기록하고
			// 원 파일 삭제함
			if (!originFile.renameTo(renameFile)) {
				int read = -1;
				byte[] buf = new byte[1024];
				// 한번에 읽을 배열 크기 지정
				FileInputStream fin = new FileInputStream(originFile);
				FileOutputStream fout = new FileOutputStream(renameFile);

				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();
				originFile.delete(); // 원본 파일 삭제함
			} 
			pd = new Product(pdPrice, pdName, pdCont, pdSubCont, renameFileName);
			
			System.out.println("name : " + renameFileName);
			System.out.println("pd : " + pd);
		}
		
		
		
		ProductService ps = new ProductService();
		
		int result = ps.insertProduct(pd);
		
		if(result > 0) {
			response.sendRedirect("/loc/pdMenu.pd");
		} else {
			request.setAttribute("error-msg", "사진 게시글 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp")
				   .forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
