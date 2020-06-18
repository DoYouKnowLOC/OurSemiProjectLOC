<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, com.kh.loc.notice.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>
<!DOCTYPE html>
<%@ include file="/views/common/header.jsp" %>
<% ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list"); %>


	<% if(m != null && m.getUserId().equals("admin")) { %>
	<div class="outer" align="center">
		<br>
		<h2 class="area" align="center">공지 사항 작성</h2>
		<div class="tableArea" align="center">
			<form id="insertForm" action="<%= request.getContextPath() %>/nInsert.no" method="post">
				<table border="1" >
					<tr>
						<td width="200px">제목 </td>
						<td width="600px" height="30"  colspan="3" ><input  id="title"  class="text3" type="text"   name="title"></td>
					</tr>
					<tr >
						<td >작성자  </td>
					<td  align="center"  >
							<input class="text3" type="text"  value="<%= m.getUserName() %>" name="writer" readonly style="text-align:left">
							<input type="hidden" value="<%= m.getUserId() %>" name="userId">
							</td>
							  
						
					<td>작성일</td>
						<td  ><input  type="date" name="date"></td>
						
					</tr>
					<tr>
						<td colspan="4">내용 </td>
					</tr>
					<tr >
						<td colspan="4" width="700px" height="550px" align="left" >
							<textarea class="textarea" id="content" name="content" ></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="right">
				
					<button class="button1" type="button" onclick="javascript:history.back();">취소하기</button>
					<button class="button1" type="submit">등록하기</button>
					
					<script>
						$('#insertForm').submit(function(event){
							if($('#content').val().length == 0){
								alert("내용을 등록 해주세요!");
								$('#content').focus();
								event.preventDefault();
							}else if($('#title').val().length == 0){
								alert("제목을 등록 해주세요!");
								$('#title').focus();
								event.preventDefault();
							}
							else {
								return;
							}
						});
					</script>
				</div>
				
			</form>
			
		</div>
		<br><br><br><br><br><br><br><br>
	</div>
	<% } else {
		request.setAttribute("msg", "관계자 외에 접근이 불가능한 페이지입니다.");
		request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
	} %>
	 <%@ include file="/views/common/footer.jsp" %>
