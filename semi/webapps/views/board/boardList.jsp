<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, com.kh.loc.board.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>
    				
<% 
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%>

   <%@ include file="/views/common/header.jsp" %>


<div class="outer">
		<br>
		<h2 align="center">게시판 목록</h2>
		<div class="tableArea">
			<table align="center" id="listArea">
			<tr >
				<th width="200px">글번호</th>
				<th height="50px" width="400px">글제목</th>
				<th height="50px"  width="200px">작성자</th>
				<th width="200px">작성일</th>
				<th height="50px" width="200px">조회수</th>
				<th width="100px">첨부파일</th>
			</tr>
			<% for(Board b : list){ %>
			<tr>
				<input type="hidden" value="<%= b.getBno() %>"/>
				<td><%= b.getBno() %></td>
				<td><%= b.getBtitle() %></td>
				<td><%= b.getBwriter() %></td>
				<td><%= b.getBdate() %></td>
				<td><%= b.getBcount() %></td>
				<% if( b.getBoardfile() != null) { %>
				<td> ◎ </td>
				<%} else { %>
				<td> X </td>
				<% } %>
			</tr>
			<% } %>
		</table>
		</div>
		
		<%-- 페이지 처리 --%>
		<div class="pagingArea" align="center">
			<button onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=1&con=<%=request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><<</button>
			<%  if(currentPage <= 1){  %>
			<button disabled><</button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%=currentPage - 1 %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><</button>
			<%  } %>
			
			<% for(int p = startPage; p <= endPage; p++){
					if(p == currentPage){	
			%>
				<button disabled><%= p %></button>
			<%      }else{ %>
				<button onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%= p %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'"><%= p %></button>
			<%      } %>
			<% } %>
				
			<%  if(currentPage >= maxPage){  %>
			<button disabled>></button>
			<%  }else{ %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%=currentPage + 1 %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'">></button>
			<%  } %>
			<button onclick="location.href='<%= request.getContextPath() %>/searchBoard.bo?currentPage=<%= maxPage %>&con=<%=  request.getParameter("con") %>&keyword=<%= request.getParameter("keyword") %>'">>></button>
			
		</div>
		<br>
		<div class="searchArea"  align="center">
			<select id="searchCondition" name="searchCondition">
				<option value="">---</option>
				<option value="writer">작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input type="search" id="keyword">
			<button class="button1" type="button" onclick="search();">검색하기</button>
			<% if(m != null){ %>
				<button class="button1" onclick="location.href='views/board/boardInsertForm.jsp'">작성하기</button>
			<% } %>
			
		</div>
	</div>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<script>
		$(function(){
			$("#listArea td").mouseenter(function(){
				$(this).parent().css({"background":"#dddddd", "cursor":"pointer"});
			}).mouseout(function(){
				$(this).parent().css({"background":"white"});
			}).click(function(){
				var bno = $(this).parent().find("input").val();
				location.href="<%=request.getContextPath()%>/selectOne.bo?bno=" + bno;
			});
		});
		function search(){
			location.href="<%=request.getContextPath()%>/searchBoard.bo?con="+$('#searchCondition').val()+"&keyword="+$('#keyword').val();
		}
		
	</script>
	
	
 <%@ include file="/views/common/footer.jsp" %>
  