<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.loc.product.model.vo.Product"%>
	
<%
	ArrayList<Product> pdList = (ArrayList<Product>) request.getAttribute("pdList");
%>
<%@ include file="../common/header.jsp"%>

<div class="hero-wrap hero-bread"
	style="background-image: url('<%=request.getContextPath()%>/resources/images/bg_1.jpg');">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"><a
						href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>Products</span>
				</p>
				<h1 class="mb-0 bread">Products</h1>
			</div>
		</div>
	</div>
</div>
<section class="ftco-section">
	<div class="container">
		<div class="row justify-content-right">
			<div class="col-md-12 mb-5 text-right">
				<ul class="product-category">
					<% if (m != null && m.getUserId().equals("admin")) { %>
						<li><a href="<%= request.getContextPath() %>/views/product/productInsert.jsp" class="active">작성하기</a></li>
					<% } %>
				</ul>
			</div>
		</div>
		<div class="row">
			<%
				for (Product p : pdList) {
			%>
			<div class="col-md-6 col-lg-3 ftco-animate">
				<div class="product">
					<input type="hidden" value="<%= p.getPdCode() %>" name="pdcode"/>
					<a href="#" class="img-prod">
						<img class="img-fluid" style="height: 200px; width: 250px"
							 src="<%=request.getContextPath()%>/resources/images/productUploadFiles/<%=p.getPdPreview() %>">
						<!-- <span class="status">30%</span> -->
						<div class="overlay"></div> 
					</a>
					<div class="text py-3 pb-4 px-3 text-center">
						<h3>
							<a href="#"><%=p.getPdName()%></a>
						</h3>
						<div class="d-flex">
							<div class="pricing">
								<p class="price">
									<span class="mr-2 price"><%=p.getPdPrice()%>원</span>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<div class="row mt-5">
			<div class="col text-center">
				<div class="block-27">
					<ul>
						<li><a href="#">&lt;</a></li>
						<li class="active"><span>1</span></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">&gt;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
	$(function(){
		$('.product').on('click', function(){
			var pdcode = $(this).find("input").val();
			location.href="<%=request.getContextPath()%>/pdSelectOne.pd?pdcode="+pdcode;
		});
	});
</script>


<%@ include file="../common/footer.jsp"%>
