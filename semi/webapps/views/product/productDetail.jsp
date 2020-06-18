<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.loc.product.model.vo.*"%>
	
<%
	Product p = (Product)request.getAttribute("product");
%>
<%@ include file="../common/header.jsp"%>

<!-- title -->
<div class="hero-wrap hero-bread"
	style="background-image: url('<%=request.getContextPath()%>/resources/images/bg_1.jpg');">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"><a
						href="<%=request.getContextPath()%>/index.html">Home</a></span> <span>Products</span>
				</p>
				<h1 class="mb-0 bread">Products</h1>
			</div>
		</div>
	</div>
</div>


<!-- 상품내역의 시작입니다. -->
<section class="ftco-section">
	<div class="container">
		<div class="row">
			
			<!-- (관리자) 상품 수정 삭제 -->
			<div class="col-lg-9 product-details pl-md-5 ftco-animate">
				<div class="row mt-4">
					<div>
					</div>
				</div>
			</div>
			<div class="col-lg-3 product-details pl-md-5 ftco-animate">
				<div class="row mt-4">
					<% if (m != null && m.getUserId().equals("admin")) { %>
						<form id="whatForm" method="post" enctype="multipart/form-data">
							<input type="hidden" name="pdcode" value="<%=p.getPdCode() %>" />
							<button onclick="goDelete();">삭제하기</button>
						</form>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button onclick="goUpdate();" type="button">수정하기</button>
					<% } %>
				</div>
			</div>
			<div class="col-lg-12 product-details pl-md-5 ftco-animate">
				<div class="row mt-4">
					<div>
					</div>
				</div>
			</div>
			
			
			<!-- 메인화면 띄우기 -->
			<div class="col-lg-6 mb-5 ftco-animate">
				<a href="<%=request.getContextPath()%>/resources/images/productUploadFiles/<%=p.getPdPreview() %>" class="image-popup">
					<img src="<%=request.getContextPath()%>/resources/images/productUploadFiles/<%=p.getPdPreview() %>"
						 class="img-fluid">
				</a>
			</div>
						<!--  상품명 / 가격 / 가격아래내용 -->
			<div class="col-lg-6 product-details pl-md-5 ftco-animate">
				<form id="orderForm" method="post">
					<h3><%=p.getPdName()%></h3>
					<p class="price">
						<span><%=p.getPdPrice()%></span>
					</p>
					<p><%=p.getPdSubCont()%></p>
					<input type="number" id="count" name="count"
						class="form-control input-number" min="1" max="100" value="1">
					
					<!--  장바구니 넣기 / 구매하기 -->
					<div class="row mt-4">
						<div>
							<input type="hidden" name="pdname" value="<%=p.getPdName()%>" />
							<input type="hidden" name="price" value="<%=p.getPdPrice()%>" /> 
							<input type="hidden" name="pdcode" value="<%=p.getPdCode()%>" />
							<input type="hidden" name="pdpreview" value="<%=p.getPdPreview()%>" />
							<% if(m == null){ %>
								<a class="btn btn-black py-3 px-5" onclick="noLogin();">장바구니</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="btn btn-black py-3 px-5" onclick="noLogin();">결제하기</a>
							<% } else { %>
								<a class="btn btn-black py-3 px-5" onclick="insertCart();">장바구니</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="btn btn-black py-3 px-5" onclick="insertOrder();">결제하기</a>
							<% } %>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<script>
	function noLogin(){
		alert("로그인이 필요합니다.");
	}
	function insertCart() {
		$("#orderForm").attr("action", "<%=request.getContextPath()%>/cartChk.cart").submit();
	}
	function insertOrder() {
		$("#orderForm").attr("action", "<%=request.getContextPath()%>/order.o").submit();
	}

</script>
<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 ftco-animate">
				<hr />
				<!-- 하는 것 마다 p 태그로 넣어주면 됨 -->
				<br /><br />
				<p style="height: auto"><%=p.getPdCont() %></p>
				<br /><br />
				<hr />
				<br /><br />
				
				<!-- comment 시작 -->
				<div class="pt-5 mt-5">
					<div id="disqus_thread"></div>
						<script>
						
						/**
						*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
						*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
						/*
						var disqus_config = function () {
						this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
						this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
						};
						*/
						(function() { // DON'T EDIT BELOW THIS LINE
						var d = document, s = d.createElement('script');
						s.src = 'https://loc-3.disqus.com/embed.js';
						s.setAttribute('data-timestamp', +new Date());
						(d.head || d.body).appendChild(s);
						})();
						</script>
						<noscript>Please enable JavaScript to view the <a href="https://disqus.com/?ref_noscript">comments powered by Disqus.</a></noscript>
				</div>
			</div>
			<!-- .col-md-12 -->
		</div>
	</div>
</section>
<!-- .section -->

<script>
	function goDelete(){
		$("#whatForm").attr("action", "<%= request.getContextPath() %>/pdDelete.pd");
	}
	function goUpdate(){
		location.href="<%= request.getContextPath() %>/pdUpView.pd?pdcode=<%=p.getPdCode()%>"
	}
</script>

<%@ include file="../common/footer.jsp"%>
