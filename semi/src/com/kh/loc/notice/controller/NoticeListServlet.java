package com.kh.loc.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.loc.notice.model.vo.noticePageInfo;
import com.kh.loc.notice.model.service.NoticeService;
import com.kh.loc.notice.model.vo.Notice;



/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/selectList.no")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1786L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 글 여러 개를 DB에서 조회하여
		// 목록 형태로 (ArrayList) 데이터를 전달하는 서블릿
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		NoticeService ns = new NoticeService();
		
		//  페이징 처리에 필요한 변수들
		// 1, 2, 3 . . .5 -> 6, 7, 8, 9, 10 
		int startPage;
		
		// 한 번에 처리할 페이지들 중 가장 마지막 페이지
		// 1, 2, 3 . . . 5 / 6, 7, 8, . . . 10 / 11, 12
		int endPage;
		
		// 전체 페이지들 중 가장 끝 페이지
		int maxPage;
		
		// 현재 사용자가 보고 있는 페이지
		int currentPage;
		
		// 한 페이지 당 보여줄 게시글 수
		int limit;
		
		// 처음 접속한 사용자가 볼 페이지 초기화하기
		currentPage = 1;
		
		// 최대 게시글 10개씩
		limit = 10;
		
		// 만약에 사용자가 현재 페이지 정보를 
		// 가지고 있다면 currentPage 정보 변경하기
		if(request.getParameter("currentPage") != null) {
			currentPage
			   = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 총 게시글 수 가져오기
		int listCount = ns.getListCount();
		
		System.out.println("총 게시글 수 : " + listCount);
		
		// 총 게시글 300개!
		// 30개 페이지
		// 만약 전체 게시글 수가 13개라면
		// 페이지는 2개 나와야 한다!
		// ** 짜투리 게시글도 하나의 페이지를 잡아 먹는다!
		// 13 --> 1.3 --(올림)--> 2
		// 19 --> 1.9 --(올림)--> 2
		maxPage = (int)((double)listCount/limit + 0.9);
		
		// 한번에 보일 시작 페이지와 끝 페이지 계산하기
		// 시작 페이지
		// 1 ~ 10 : 1
		// 11 ~ 20 : 11
		startPage = ((int)((double)currentPage / limit + 0.9) - 1) * limit + 1;
		
		// 끝 페이지
		// 1 ~ 10 : 10
		// 11 ~ 20 : 20
		endPage = startPage + limit - 1;
		
		// 만약 마지막 페이지가 전체 기준 페이지보다 
		// 크다면, 즉 총 13개 페이지밖에 안나오는데 20까지 잡았다면
		if( endPage > maxPage) {
			endPage = maxPage;
		}
		
		// ------------- 페이지 처리는 끝		
		
		list = ns.selectList(currentPage, limit);
		
		String page = "";
		
		if(list != null) {
			
			noticePageInfo pi = new noticePageInfo(currentPage, listCount, limit, 
	                   maxPage, startPage, endPage);
			
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			
			page = "/views/notice/noticeList.jsp";
			
		} else {
			request.setAttribute("error-msg", "공지사항 목록 조회 실패");
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