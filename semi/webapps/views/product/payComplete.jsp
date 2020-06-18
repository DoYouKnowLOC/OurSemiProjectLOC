<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<div class="hero-wrap hero-bread"
	style="background-image: url('<%=request.getContextPath()%>/resources/images/bg_1.jpg');">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"><a
						href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>결제완료</span>
				</p>
				<h1 class="mb-0 bread">주문이 완료되었습니다.</h1>
			</div>
		</div>
	</div>
</div>

<section class="ftco-section ftco-cart">
	<div class="container">
		<div class="row">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">
					<table class="table">
						<thead class="thead-primary" style="font-size: 17px">
							<tr class="text-center">
								<td><a href="<%=request.getContextPath()%>/index.jsp" style="color: white">메인으로 이동하기</a></td>
							<tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<%@ include file="../common/footer.jsp"%>