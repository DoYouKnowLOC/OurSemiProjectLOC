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
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/selectOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NoticeSelectOneServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
int nno = Integer.parseInt(request.getParameter("nno"));
		
		NoticeService ns = new NoticeService();
		
		Notice n = ns.selectOne(nno);
		String page = null;
		
		if(n != null) {
			request.setAttribute("notice", n);
			page = "views/notice/noticeDetail.jsp";
		} else {
			request.setAttribute("error-msg", "공지사항 상세보기 실패");
			page = "/views/common/errorPage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
