<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>게시글 작성</title>

<%@ include file="/views/common/header.jsp" %>

	<% if (m != null) { %>
	<div class="outer" align="center">
		<br>
		<h2 align="center">게시판 작성</h2>
		<div class="tableArea">
			<form action="<%= request.getContextPath() %>/bInsert.bo" 
			      method="post" enctype="multipart/form-data" id="insertForm">
				<table border="1" align="center">
					<tr>
						<td>제목 </td>
						
						<td colspan="3">
							<input class="text3"type="text" size="70%" id="title" name="title" >
						</td>
					</tr>
					<tr>
						<td >작성자 </td>
						<td colspan="3" align="left"><%= m.getUserName() %>
							<input type="hidden" name="userId" value="<%= m.getUserId() %>"/>
						</td>
					</tr>
					<tr>
						<td>첨부파일 </td>
						<td colspan="3">
							<input type="file" name="file" id="file" />
						</td>
					</tr>
					<tr>
						<td>내용 </td>
						<td colspan="3">
							<textarea class="text3" name="content" id="content" cols="70%" rows="15%" style="resize:none;"></textarea>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
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
	</div>
	<% } else { 
		request.setAttribute("msg", "회원만 열람 가능합니다.");
		request.getRequestDispatcher("../common/errorPage.jsp").forward(request, response);
	 } %>
	<%@ include file="/views/common/footer.jsp" %>
</body>
</html>