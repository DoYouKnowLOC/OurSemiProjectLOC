<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.loc.notice.model.vo.*,
    				com.kh.loc.member.model.vo.*"%>


<%
	Member m = (Member)session.getAttribute("member");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<title>LOC</title> 
	<script src="<%= request.getContextPath() %>/resources/js/jquery-3.5.1.min.js"></script>
	<style>
   .outer{
      width:1200px;
    
      background:white;
      border : 1px solid #dddddd;
      color:black;
      margin-left:auto;
      margin-right:auto;
      margin-top:50px;
      text-align:center;
      align:center;
   }
   table {
      
      
      text-align:center;
   }
   .tableArea {
      width:850px;
      height:550px;
      margin-left:auto;
      margin-right:auto;
      
   }
   .tableArea2 {
      width:850px;
      height:550px;
      margin-left:auto;
      margin-right:auto;
      border : 1px solid #dddddd;
      
   }
   .searchArea {
      width:850px;
      margin-left:auto;
      margin-right:auto;
      
   }
   #button1{
      width:150px;
      height:50px;
      background : gray;
      color : white;
   }
   
   .button1{
      width:150px;
      height:50px;
      background : gray;
      color : white;
   }
   
   .search{
      width:150px;
   height:40px;      
   }
   
   .keyword{
      width: 300px;
      height:40px;
   }
   
   .title{
      width:400px;
      height:100px;
      margin-left:auto;
      margin-right:auto;
      algin:center;
   }
   
   .area{
      text-align:center;
      vertical-align : center;
      position : relative;
      margin : 0auto;
   }
   
   .textarea{
          border-style: none;
        border-color: Transparent;
        overflow: auto;
        outline: none;
        width:100%;
        height:98%;
        resize : none;
        
   }
   .text{
          border-style: none;
        border-color: Transparent;
        overflow: auto;
        margin : 0auto;
        outline: none;
        width : 700px;
        resize : none;
   }
   
   .text2{
      border-style: none;
        border-color: Transparent;
        overflow: auto;
        outline: none;
        width : 700px;
        height: 10px;
        resize : none;
   }
   
   .text3{
         
         width : 100%;
         height : 100%;
         border-style: none;
        border-color: black;
        outline: none;
        text-align : left;
   }

	#footer {
		/* position: fixed; */
		bottom: 0;
		width: 100%;
		height: 50%;
	}



   

</style>
	
	<!-- 디자인 -->
	<link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">
 
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/animate.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/magnific-popup.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/aos.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/ionicons.min.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/flaticon.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/icomoon.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/style.css">
    
    <!-- 섬머노트 -->
    <link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/summernote/summernote-lite.css" />
    <script src="<%= request.getContextPath()%>/resources/js/summernote/summernote-lite.js"></script>
    <script src="<%= request.getContextPath()%>/resources/js/summernote/lang/summernote-ko-KR.js"></script>
     
    <!-- 주소찾기 api -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    
    <script type="text/javascript">
     //회원가입하기
   function fn_join(){
      alert("회원가입 화면으로 이동합니다.");
      location.href="/loc/views/member/checkout.jsp";
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
   //회원정보수정
   function fn_changeInfo(){
       alert("회원정보수정 화면으로 이동합니다.");
         location.href="/loc/views/member/memberModify.jsp";
   }
   //회원탈퇴
   function memberDelete(){
       if(confirm("회원탈퇴 화면으로 이동하시겠습니까?")){
          location.href="/loc/views/member/memberDelete.jsp";
       }
   }
   function fn_search(){
       alert("ID/PW 찾기 화면으로 이동합니다.");
         location.href="/loc/views/member/memberSearch.jsp";
   }
   
   function pleaseLogin(){
	   alert("로그인이 필요합니다.");
	   location.href="#";
   }

  </script>
    
  </head>
  
  
  <body class="goto-here">
		<div class="py-1 bg-primary">
    	<div class="container">
    		<div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
	    		<div class="col-lg-12 d-block">
		    		<div class="row d-flex">
		    			<div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-phone2"></span></div>
						    <span class="text">-82 010-4646-9356</span>
					    </div>
					    <div class="col-md pr-4 d-flex topper align-items-center">
					    	<div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
						    <span class="text">loc@thank.you</span>
					    </div>
					     <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
						    <!-- <span class="text">3-5 Business days delivery &amp; Free Returns</span> -->
						    <%if ( m == null ) { %>
						   <form id="loginForm" action="/loc/login.me" method="post">
                         <span>ID : <input type="text" name="userId" id="userId" value=""></span>
                         <span>PASSWORD : <input type="password" name="userPwd" id="userPwd" value=""></span>
                         <input type="button" name = "btn1" value="로그인" onclick="javascript:fn_login();">
                         <input type="button" name = "btn1" value="회원가입" onclick="javascript:fn_join();">
                         <input type="button" name = "btn1" value="ID/PW찾기" onclick="javascript:fn_search();">
                      </form>
					    	</div>
				    </div>
			    </div>
		    </div>
		  </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath() %>/resources/images/loc로고2.png" alt="로고이미지" style="height: 46px;" /></a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp" class="nav-link">Home</a></li>
	          <li class="nav-item">
	              	<a class="nav-link" href="<%= request.getContextPath() %>/pdMenu.pd">레시피</a>
	          </li>
	         <li class="nav-item"><a href="<%=request.getContextPath()%>/searchNotice.no" class="nav-link">공지사항</a></li>
              <li class="nav-item active"><a href="<%=request.getContextPath()%>/searchBoard.bo" class="nav-link">게시판</a></li>
	          <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	                <a class="dropdown-item" onclick="javascript:pleaseLogin();">장바구니</a>
	                <a class="dropdown-item" onclick="javascript:pleaseLogin();">주문 현황</a>
	              </div>
	          </li>
	          <li class="nav-item cta cta-colored"><a onclick="javascript:pleaseLogin();" class="nav-link"><span class="icon-shopping_cart"></span>[★]</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
					    	<% } else if ( m.getUserId().equals("admin") && m != null) { %>
					    	<span style="color: black;">ID : <%= m.getUserName() %>님의 방문을 환영합니다.</span>
					    	<button type="button" id="changeInfo" onclick="changeInfo()">정보수정</button>
							<button type="button" id="logoutBtn" onclick='logout()'>로그아웃</button>
							</div>
				    </div>
			    </div>
		    </div>
		  </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath() %>/resources/images/loc로고2.png" alt="로고이미지"  style="height: 46px;"/></a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp" class="nav-link">Home</a></li>
	          <li class="nav-item">
	              	<a class="nav-link" href="<%= request.getContextPath() %>/pdMenu.pd">레시피</a>
	          </li>
	          <li class="nav-item"><a href="<%=request.getContextPath()%>/searchNotice.no" class="nav-link">공지사항</a></li>
              <li class="nav-item active"><a href="<%=request.getContextPath()%>/searchBoard.bo" class="nav-link">게시판</a></li>
	          <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">관리페이지</a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	                <a class="dropdown-item" href="<%= request.getContextPath() %>/dLIst.admin">주문 현황</a>
	              </div>
	          </li>
	        </ul>
	      </div>
	    </div>
	  </nav>
					    	<% } else { %>
					    	<span style="color: black;">ID : <%= m.getUserName() %>님의 방문을 환영합니다.</span>
                      <button type="button" id="changeInfo" onclick="javascript:fn_changeInfo()">정보수정</button>
                     <button type="button" id="logoutBtn" onclick='logout()'>로그아웃</button> 
                     <button type="button" id="deleteBtn" onclick='memberDelete()'>회원탈퇴</button> 
							</div>
				    </div>
			    </div>
		    </div>
		  </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath() %>/resources/images/loc로고2.png" alt="로고이미지" style="height: 46px;"/></a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="<%=request.getContextPath()%>/index.jsp" class="nav-link">Home</a></li>
	          <li class="nav-item">
	              	<a class="nav-link" href="<%= request.getContextPath() %>/pdMenu.pd">레시피</a>
	          </li>
	         <li class="nav-item"><a href="<%=request.getContextPath()%>/searchNotice.no" class="nav-link">공지사항</a></li>
              <li class="nav-item active"><a href="<%=request.getContextPath()%>/searchBoard.bo" class="nav-link">게시판</a></li>
	          <li class="nav-item dropdown">
	              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">마이페이지</a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	                <a class="dropdown-item" href="<%= request.getContextPath() %>/cList.cart">장바구니</a>
	                <a class="dropdown-item" href="<%= request.getContextPath() %>/pmDelivey.pm">주문 현황</a>
	              </div>
	          </li>
	          <li class="nav-item cta cta-colored"><a href="<%= request.getContextPath() %>/cList.cart" class="nav-link"><span class="icon-shopping_cart"></span>[★]</a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
					    	<% } %>
