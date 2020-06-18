<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID/PW 찾기</title>
<style>
/* #joinBtn {
	background: skyblue;
	border-radius: 5px;
	width: 100px;
	height: 20px;
	text-align: center;
	float: left;
	margin-right: 100px;
}  */

.hide-submit {
	display : none;
}

</style>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-xl-7 ftco-animate">
						<h3 class="mb-4 billing-heading">ID/PW찾기</h3>

						<form action="<%=request.getContextPath()%>/mSearchID.do"
							method="post" class="billing-form" id="searchIdForm">
							<div>
								<div class="row align-items-end">
									<div class="col-md-6">
										<div class="form-group">
											<label for="country">사용자 이름</label> <input type="text"
												class="form-control" name="userName" id="userName"
												placeholder="">
										</div>
									</div>
								</div>

								<div class="row align-items-end">
									<div class="col-md-6">
										<div class="form-group">
											<label for="lastname">생년월일</label> <input type="date"
												class="form-control" name="birth" id="birth" max="9999-12-31" placeholder="">
										</div>
									</div>
								</div>
								<div class="btns" align="center">
									<div id="joinBtn" onclick="fn_submit_id();">ID찾기</div>
									<input class="hide-submit" type="submit"/>
								</div>
							</div>
						</form>
						<form action="<%=request.getContextPath()%>/mSearchPWD.do"
							method="post" class="billing-form" id="searchPwdForm">
							<div>
								<div class="row align-items-end">
									<div class="col-md-6">
										<div class="form-group">
											<label for="lastname">ID</label> <input type="text"
												class="form-control" name="userId" id="userId"
												placeholder="">
										</div>
									</div>
								</div>
								<div class="row align-items-end">
									<div class="col-md-6">
										<div class="form-group">
											<label for="lastname">E-MAIL</label> <input type="text"
												class="form-control" name="email" id="email" placeholder="">
										</div>
									</div>
								</div>
								<div class="row align-items-end">
									<div class="col-md-6">
										<div class="form-group">
											<label for="lastname">Phone</label> <input type="text"
												class="form-control" name="phone" id="phone" placeholder="">
										</div>
									</div>
								</div>
								<div class="btns" align="center">
									<div id="joinBtn" onclick="fn_submit_pwd();">PW찾기</div>
									<input class="hide-submit" type="submit"/>
								</div>
							</div>
						</form>
						<!-- END -->
				</div>
				<script>
				function fn_submit_id(){
					if ($("#searchIdForm input[name=userName]").val() == null
							|| $("#searchIdForm input[name=userName]").val() == "") {
						alert("이름을 입력하세요.");
						return false;
					} else if ($("#searchIdForm input[name=birth]").val() == null
							|| $("#searchIdForm input[name=birth]").val() == "") {
						alert("생년월일을 입력하세요.");
						return false;
					}
					
					 $('#searchIdForm').submit();
				}
				
				function fn_submit_pwd(){
					if ($("#searchPwdForm input[name=userId]").val() == null
							|| $("#searchPwdForm input[name=userId]").val() == "") {
						alert("아이디를 입력하세요.");
						return false;
					} else if ($("#searchPwdForm input[name=email]").val() == null
							|| $("#searchPwdForm input[name=email]").val() == "") {
						alert("이메일주소를 입력하세요.");
						return false;
					}
					
					$('#searchPwdForm').submit();
				}

				$(function (){
					var userId = '<%= (String)request.getAttribute("userId") %>';
					if(userId != 'null' &&  userId != ''){
						alert("당신의 아이디는 "+ userId + "입니다.");
						$('[name=userId]').val(userId);
					}
				});
				/* 
					function memberSearch(data) {

						if (data == "ID") {
							if ($("#searchForm input[name=userName]").val() == null
									|| $("#searchForm input[name=userName]").val() == "") {
								alert("이름을 입력하세요.");
								return false;
							} else if ($("#searchForm input[name=birth]").val() == null
									|| $("#searchForm input[name=birth]").val() == "") {
								alert("생년월일을 입력하세요.");
								return false;
							}
							//TODO ID찾기 서블릿 추가

						} else if (data == "PW") {
							if ($("#searchForm input[name=userId]").val() == null
									|| $("#searchForm input[name=userId]").val() == "") {
								alert("아이디를 입력하세요.");
								return false;
							} else if ($("#searchForm input[name=email]").val() == null
									|| $("#searchForm input[name=email]").val() == "") {
								alert("이메일주소를 입력하세요.");
								return false;
							}
							//TODO 패스워드찾기 서블릿 추가

						}

						$("#searchForm").submit();
					} */
				</script>


			</div>


		</div>
		<!-- .col-md-8 -->
	</section>
	<!-- .section -->
	<%@ include file="../common/footer.jsp"%>
</body>
</html>