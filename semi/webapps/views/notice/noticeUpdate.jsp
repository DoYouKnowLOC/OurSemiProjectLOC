<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.*, com.kh.loc.notice.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>
<% Notice n = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html>
<%@ include file="/views/common/header.jsp" %>


	<% if(m != null && m.getUserId().equals("admin")){ %>
	<div class="outer" >
		<br>
		<h2 align="center">공지 사항 수정</h2>
		<div class="tableArea" align="center">
			<form id="updateForm" method="post" >
				<table align="center"  border="1">
					<tr>
						<td>제목 </td>
						<td  colspan="4" width="900" height="50"  align="left">
							<input  class="text3" id="title" type="text"     name="title" 
							       value="<%= n.getNtitle().replace("\"", "&#34;") %>">
							<input type="hidden" name="nno" value="<%= n.getNno() %>">
						</td>
					</tr>
					<tr >
						<td >작성자  </td>
							<td colspan="1" width="300" height="50" align="center"  ><input   class="text3" type="text" value="<%= n.getNwriter() %>" name="writer" readonly>
							  </td>
						<td colspan="1" width="200">작성일</td> 
						<td colspan="1" width="100" height="50">
						<input size="30"  type="date" name="date" value="<%= n.getNdate() %>"></td>
					</tr>
					<tr>
						<td colspan="4" height="40px" >내용 </td>
					</tr>
					<tr>
						<td colspan="4" align="left" width="700px" height="550px">
							<textarea  class="textarea" id="content" name="content" ><%= n.getNcontent() %></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<button class="button1" onclick="complete();">작성완료</button>
					<button class="button1" onclick="deleteNotice();">삭제하기</button>
				</div>
				<script>
				function complete(){
					if($('#content').val().length < 1){
						alert("내용을 등록 해주세요!");
						$('#content').focus();
						event.preventDefault();
					}else if($('#title').val().length < 1){
						alert("제목을 등록 해주세요!");
						$('#title').focus();
						event.preventDefault();
					}
					else {
						$("#updateForm").attr("action","<%=request.getContextPath() %>/nUpdate.no");
						
					}
				}
					
					function deleteNotice(){
						// delete 는 예약어 이므로 deleteNotice 로 ...!
						$("#updateForm").attr("action","<%=request.getContextPath() %>/nDelete.no");
					}
				
				</script>
			</form>
			
		</div>
		<br><br><br><br><br><br><br><br>
	</div>
	<% } else {
		request.setAttribute("msg", "관계자 외에 접근이 불가능한 페이지입니다.");
		request.getRequestDispatcher("view/common/errorPage.jsp").forward(request, response);
	 } %>
 <%@ include file="/views/common/footer.jsp" %>