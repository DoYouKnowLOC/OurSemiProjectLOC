<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.loc.member.model.vo.*"%>
<%
	Member m = (Member)session.getAttribute("member");
%>
<script type="text/javascript">
  	//회원가입하기
	function fn_join(){
  		alert("회원가입 화면으로 이동합니다.");
  		location.href="/loc/views/member/memberJoinForm.jsp";
  	}
  	//로그인하기
  	function fn_login(){
  		alert("로그인 중");
  		$('#loginForm').submit();
  	}
  	//로그아웃하기
  	function logout(){
		location.href="/loc/logout.me";
	}
</script>
<div class="py-1 bg-primary">
	<div class="container">
		<div
			class="row no-gutters d-flex align-items-start align-items-center px-md-0">
			<div class="col-lg-12 d-block">
				<div class="row d-flex">
					<div class="col-md pr-4 d-flex topper align-items-center">
						<div
							class="icon mr-2 d-flex justify-content-center align-items-center">
							<span class="icon-phone2"></span>
						</div>
						<span class="text">+ 1235 2355 98</span>
					</div>
					<div class="col-md pr-4 d-flex topper align-items-center">
						<div
							class="icon mr-2 d-flex justify-content-center align-items-center">
							<span class="icon-paper-plane"></span>
						</div>
						<span class="text">youremail@email.com</span>
					</div>
					<div
						class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
						<!-- <span class="text">3-5 Business days delivery &amp; Free Returns</span> -->
						<%
							if (m == null) {
						%>
						<form id="loginForm" action="/loc/login.me" method="post">
							<span>ID : <input type="text" name="userId" id="userId"
								value=""></span> <span>PASSWORD : <input type="password"
								name="userPwd" id="userPwd" value=""></span> <input
								type="button" name="btn1" value="로그인"
								onclick="javascript:fn_login();"> <input type="button"
								name="btn1" value="회원가입" onclick="javascript:fn_join();">
						</form>
						<%
							} else {
						%>
						<span style="color: black;">ID : <%=m.getUserName()%>님의
							방문을 환영합니다.
						</span>
						<button type="button" id="changeInfo" onclick="changeInfo()">정보수정</button>
						<button type="button" id="logoutBtn" onclick='logout()'>로그아웃</button>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<nav
	class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
	id="ftco-navbar">
	<div class="container">
		<a class="navbar-brand" href="/loc/index.jsp">LOC</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#ftco-nav" aria-controls="ftco-nav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="oi oi-menu"></span> Menu
		</button>

		<div class="collapse navbar-collapse" id="ftco-nav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a href="/loc/index.jsp"
					class="nav-link">Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="dropdown04"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">메뉴</a>
					<div class="dropdown-menu" aria-labelledby="dropdown04">
						<a class="dropdown-item" href="#">메뉴</a> <a class="dropdown-item"
							href="#">Wishlist</a> <a class="dropdown-item" href="#">Single
							Product</a> <a class="dropdown-item" href="#">Cart</a> <a
							class="dropdown-item" href="#">Checkout</a>
					</div></li>
				<li class="nav-item"><a href="#" class="nav-link">About</a></li>
				<li class="nav-item"><a href="#" class="nav-link">Blog</a></li>
				<li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
				<li class="nav-item cta cta-colored"><a href="#"
					class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

			</ul>
		</div>
	</div>
</nav>
<!-- END nav -->