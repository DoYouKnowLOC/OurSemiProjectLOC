<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, com.kh.loc.notice.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>
<%
	Notice n = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>

   <% ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); %>
    				
<%@ include file="/views/common/header.jsp" %>


	<div class="outer">
		<br>
		<h2 align="center">공지 사항 내용</h2>
		<div class="tableArea"  >
				<table border="1px solid black" >
					<tr >
						<td >제목 </td>
						<td colspan="3" width="600" height="50" align="left"><%= n.getNtitle() %></td>
					</tr>
					<tr >
						<td colspan="1" width="100" height="50">작성자</td>
						<td>
							<%= n.getNwriter() %>
						</td>
						<td>작성일 </td>
						<td><%= n.getNdate() %></td>
					</tr>
					<tr>
						<td colspan="5">내용 </td>
					</tr>
					<tr> 
						<td width="1500"height="550" colspan="5"><br>
							<span><%= n.getNcontent().charAt(0) %></span><%= n.getNcontent().substring(1) %>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					
					<button class="button1" onclick="location.href='selectList.no'">메뉴로 돌아가기</button>

					<% if( m != null && m.getUserId().equals("admin")) { %>
						<button class="button1"onclick="location.href='nUpView.no?nno=<%=n.getNno()%>'">수정하기</button>
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
	</div>
	
	 <%@ include file="/views/common/footer.jsp" %>
