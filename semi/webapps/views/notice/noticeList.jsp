<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, com.kh.loc.notice.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>
<% 
ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); 
noticePageInfo pi = (noticePageInfo)request.getAttribute("pi");
int listCount = pi.getListCount();
int currentPage = pi.getCurrentPage();
int maxPage = pi.getMaxPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();


%>
    				
<%@ include file="/views/common/header.jsp" %>



	<div class="outer">
		<br>
		<h2 align="center">공지사항</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
			<tr >
				<th width="200px">글번호</th>
				<th height="50px" width="400px">글제목</th>
				<th height="50px"  width="200px">작성자</th>
				<th width="200px">조회수</th>
				<th height="50px" width="200px">작성일</th>
			</tr>
			<% for(Notice n : list){ %>
			<tr>
				<td><%= n.getNno() %></td>
				<td><%= n.getNtitle() %></td>
				<td><%= n.getNwriter() %></td>
				<td><%= n.getNcount() %></td>
				<td><%= n.getNdate() %></td>
			</tr>
			<% } %>
		</table>
		</div>
		
		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/searchNotice.no?currentPage=1&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchNotice.no?currentPage=<%=currentPage - 1 %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/searchNotice.no?currentPage=<%= p %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchNotice.no?currentPage=<%=currentPage + 1 %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchNotice.no?currentPage=<%= maxPage %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'">>></button>
		</div>		
		
		<div class="searchArea" align="center">
			<select class="search" id="searchCondition" name="searchCondition">
				<option value="">---</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input class ="keyword" type="search" id="keyword" placeholder="이 곳에 검색하세요!"> 
			<button class="button1" type="button" onclick="search();">검색하기</button>
			<% if(m != null && m.getUserId().equals("admin")){ %>
				<button class="button1" onclick="location.href='views/notice/noticeInsertForm.jsp'">작성하기</button>
			<% } %>
		</div>
	</div>
	<script>  
		$(function(){
			
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"#eeeeee", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"white"});
			}).click(function(){
				//console.log($(this).parent().children().eq(0).text());
				var nno = $(this).parent().children().eq(0).text();
				location.href="<%=request.getContextPath()%>/selectOne.no?nno=" + nno;
			});
		});
		
		function search(){
			location.href="<%=request.getContextPath()%>/searchNotice.no?con="+$('#searchCondition').val()+"&keyword="+$('#keyword').val();
		}
		
	</script>
 <%@ include file="/views/common/footer.jsp" %>