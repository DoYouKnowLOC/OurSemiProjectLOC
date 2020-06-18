package com.kh.loc.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.notice.model.service.NoticeService;
import com.kh.loc.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/nUpdate.no")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NoticeUpdateServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int nno = Integer.parseInt(request.getParameter("nno"));
			
			Notice n = new Notice();
			
			n.setNno(nno);
			n.setNcontent(content);
			n.setNtitle(title);
			
			int result = new NoticeService().updateNotice(n);
			
			if (result > 0) {
				
				response.sendRedirect("selectList.no");
				
			} else {
				
				request.setAttribute("msg", "공지사항 수정 실패!!");
				request.getRequestDispatcher("views/common/errorPage.jsp")
				.forward(request, response);
				
			}
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
