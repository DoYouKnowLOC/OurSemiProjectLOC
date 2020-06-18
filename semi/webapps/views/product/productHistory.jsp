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
					<span class="mr-2"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>History</span>
				</p>
				<h1 class="mb-0 bread">History</h1>
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
								<th colspan="2">상품 명</th>
								<th>수량</th>
								<th>총 가격</th>
								<th>배송 현황</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-center">
								
								<td class="image-prod">
									<div class="img" style="background-image:url(<%=request.getContextPath()%>/resources/images/product-3.jpg);"></div>
								</td>

								<td class="product-name">
									<h3>까르보나라</h3>
									<p>good taste.</p>
								</td>

								<td class="quantity" style="20%;">
									<div class="input-group mb-3">
										<input type="text" name="quantity"
											class="quantity form-control input-number" value="1" min="1"
											max="100" readonly="readonly">
									</div>
								</td>

								<td class="total">$4.90</td>
								
								<td class="product-name" style="width: 20%;">
									배송완료
								</td>
								
							</tr>
							<!-- END TR-->

							<tr class="text-center">

								<td class="image-prod">
									<div class="img" style="background-image:url(<%=request.getContextPath()%>/resources/images/product-4.jpg);"></div>
								</td>

								<td class="product-name">
									<h3>Bell Pepper</h3>
									<p>Far far away, behind the word mountains, far from the countries</p>
								</td>

								<td class="quantity" style="20%;">
									<div class="input-group mb-3">
										<input type="text" name="quantity"
											class="quantity form-control input-number" value="1" min="1"
											max="100" readonly="readonly">
									</div>
								</td>

								<td class="total">$17.0</td>
								
								<td class="product-name" style="width: 20%;">
									배송완료
								</td>
							</tr>
							<!-- END TR-->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>