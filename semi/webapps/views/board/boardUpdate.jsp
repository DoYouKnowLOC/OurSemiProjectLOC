<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.loc.board.model.vo.*, java.util.*"%>
 <% Board b = (Board)request.getAttribute("board"); %>
<!DOCTYPE html>




<%@ include file="../common/header.jsp" %>
	<div class="outer" >
		<br>
		<h2 align="center">게시글 수정 페이지</h2>
		<div class="tableArea" align="center">
			<form id="updateForm" align="center" method="post" enctype="multipart/form-data">
				<table text-align = "center" align="center" border= "1px solid black">
					<tr>
						<td>제목  </td>
						<td colspan="4" width="700" height="50" >
							<input type="text" class="text3" name="title"  id="title" value="<%=b.getBtitle() %>">
							<input type="hidden" name="bno" value="<%=b.getBno()%>">
						</td>
					</tr>
					<tr >
						<td >작성자</td>
						<td colspan="4" width="700" height="50" align="left">
						<%= b.getBwriter() %></td>
						
					</tr>
					
					<tr>
					<% if(b.getBoardfile() != null){ %>
						<td>기존 파일 </td>
						<td  >
							&nbsp;&nbsp;<a href="/myWeb/bfdown.bo?path=<%=b.getBoardfile()%>">
							<%=b.getBoardfile()%></a>	
						</td>
					
					<% } %>
					
						<td>파일 첨부 </td>
						<td  align="right" colspan="1">
							<input type="file" name="file" id="file" />
						</td>
					</tr>
					<tr>
						<td>내용 </td>
						<td colspan="4" width="700px" height="550px">
							<textarea name="content" id="content" class="textarea"   ><%=b.getBcontent() %></textarea>
						</td>
						
					</tr>
					
				</table>
				<br>
				<div align="center">
					<button class="button1" onclick="complete();">수정완료</button>
					<button class="button1" onclick="deleteBoard();">삭제하기</button>
				</div>
				<script>
						
					</script>
				
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
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bUpdate.bo");
					}
					
				}	
				
					function deleteBoard(){
						$("#updateForm").attr("action","<%= request.getContextPath()%>/bDelete.bo");
					}
				
				</script>
				
				
			</form>
	</div>
	<br><br><br><br><br><br><br><br>
	</div>
	<%@ include file="../common/footer.jsp" %>
</body>
</html>