<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.loc.product.model.vo.*, com.kh.loc.payment.model.vo.*"%>

<%
	HashMap<String, Object> hmap = (HashMap<String, Object>)request.getAttribute("hmap");
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
						href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>Cart</span>
				</p>
				<h1 class="mb-0 bread">장바구니</h1>
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
								<th>&nbsp;</th>
								<th colspan="2">상품 명</th>
								<th>가격(개)</th>
								<th>수량</th>
								<th>총 가격</th>
							</tr>
						</thead>
						<tbody>
							<% ArrayList<Cart> clist = (ArrayList<Cart>)hmap.get("list"); %>
							<% for(Cart c : clist){ %>
								<tr class="text-center" id="cart-<%=c.getCartNo() %>">
									<td class="product-remove">
										<input type="hidden" name="clist" value="<%=hmap.get("list") %>"/>
										<input type="hidden" name="cartno" value="<%=c.getCartNo() %>" />
										<% System.out.println("c.getCartNo();" + c.getCartNo()); %>
										<a onclick="deleteOneCart(<%=c.getCartNo() %>);"><span><class="ion-ios-close">x</span></a>
									</td>
									<td class="image-prod">
										<div class="img" style="background-image:url(<%=request.getContextPath()%>/resources/images/productUploadFiles/<%=c.getPdPreview() %>);"></div>
									</td>
	
									<td class="product-name">
										<h3><%= c.getPdName() %></h3>
										<p><%= c.getPdSubCont() %></p>
									</td>
	
									<td class="price">
										<%=c.getPdPrice() %>
									</td>
	
									<td class="quantity">
										<div class="input-group mb-3">
											<input type="text" name="count" id="count"
												class="quantity form-control input-number" value="<%=c.getCount() %>" min="1"
												max="100">
										</div>
									</td>
	
									<td class="total"><%= c.getTotalPrice() %></td>
								</tr>
							<% } %>
							<!-- END TR-->
						</tbody>
					</table>
				</div>
				<br />
				<h5>※ 50,000원 이상 구매 시 배송비 무료</h5>
			</div>
		</div>

		<div class="row justify-content-end">
			<div class="col-lg-12 mt-5 cart-wrap ftco-animate">
				<div class="cart-total mb-3 pcart">
					<h3 style="font-size: 35px">Cart Totals</h3>
					<p class="d-flex" align="center"
						style="font-size: 20px; color: black;">
						<span> 상품 금액 <span style="font-size: 5px"> </span> <span id="Tprice"><%= hmap.get("sumMoney") %></span>
						</span> <span> <span style="font-size: 5px"> </span> <span>+</span>
						</span> <span> 배송비 <span style="font-size: 5px"> </span> <span id="Tfee"><%= hmap.get("fee") %></span>
						</span> <span> <span style="font-size: 5px"> </span> <span>=</span>
						</span> <span> 총 결제 예정금액 <span style="font-size: 5px"> </span> <span><strong id="TSum"><%= hmap.get("sum") %></strong></span>
						</span>
					</p>
				</div>
				
				<form id="orderForm" method="post">
				<p align="right">
					<a href="<%= request.getContextPath() %>/pdMenu.pd" class="btn btn-primary py-3 px-4">메뉴 보러가기</a> 
					<a onclick="insertOrder();" class="btn btn-primary py-3 px-4">결제하기</a>
				</p>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
	function insertOrder() {
		$("#orderForm").attr("action", "<%=request.getContextPath()%>/cOrder.o").submit();
	}
	function deleteOneCart(cartno){

		$.ajax({
			url : '<%=request.getContextPath()%>/cDeleteOne.cart',
			data : { cartno : cartno },
			success : function(data){
				if(data > 0 ){
					alert("정상 삭제가 되었습니다.");
					$('#cart-'+cartno).remove();
					location.href='<%=request.getContextPath()%>/cList.cart';
				} else {
					alert("서버 오류입니다. \n관리자에게 문의 주세요.");
				}
			}
		});
	}
</script>

<%@ include file="../common/footer.jsp"%>