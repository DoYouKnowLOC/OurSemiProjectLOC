<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.loc.product.model.vo.*, com.kh.loc.payment.model.vo.*"%>
<%
	HashMap<String, Object> hmap = (HashMap<String, Object>)request.getAttribute("hmap");
	ArrayList<Payment> plist = (ArrayList<Payment>)hmap.get("list");
%>
<%@ include file="../common/header.jsp"%>
<div class="hero-wrap hero-bread"
	style="background-image: url('<%=request.getContextPath()%>/resources/images/bg_1.jpg');">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>Delivery</span>
				</p>
				<h1 class="mb-0 bread">Delivery</h1>
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
								<th>주문취소</th>
							</tr>
						</thead>
						<tbody>
							<% for(Payment p : plist){ %>
							<tr class="text-center" id="delivery-<%=p.getPayNo()%>">
							<input type="hidden" name="payno" value="<%=p.getPayNo()%>" />
								
								<td class="image-prod">
									<div class="img" style="background-image:url(<%=request.getContextPath()%>/resources/images/productUploadFiles/<%=p.getPdPreview() %>);"></div>
								</td>

								<td class="product-name">
									<h3><%=p.getPdName() %></h3>
									<p><%=p.getPdSubCont() %></p>
								</td>

								<td class="quantity" style="20%;">
									<div class="input-group mb-3">
										<input type="text" name="quantity"
											class="quantity form-control input-number" value="<%=p.getCount() %>" min="1"
											max="100" readonly="readonly">
									</div>
								</td>

								<td class="total"><%=p.getPdPrice() %></td>
								
								<% if(p.getdStatus().equals("N")){ %>
									<td class="product-name" style="width: 20%;">
										배송 전
									</td>
									<td class="product-remove" style="width: 10%;">
										<a onclick="deleteDelivery(<%=p.getPayNo() %>);"><span class="ion-ios-close"></span></a>
									</td>
								<% } else { %>
									<td class="product-name" style="width: 20%;">
										배송 중
									</td>
									<td class="product-remove" style="width: 10%;">
										<a onclick="noCancel();"><span class="ion-ios-close"></span></a>
									</td>
								<% } %>
								
							</tr>
							<% } %>
							<!-- END TR-->
						</tbody>
					</table>
				</div>
				<br />
				<h5>※  배송중인 상품은 취소할 수 없습니다. ※</h5>
			</div>
		</div>
	</div>
</section>
<script>
	function deleteDelivery(payno){
		
		$.ajax({
			  url : '<%=request.getContextPath()%>/pmCancel.pm'
			, data : {payno : payno}
			, success : function(data){
				if(data > 0){
					alert("정상적으로 취소 되었습니다.");
					$('#delivery-'+payno).remove();
				} else {
					alert("서버 오류입니다. \n관리자에게 문의 주세요.");
				}
			}
		});
	}
	
	function noCancel(){
		alert("배송중인 상품은 취소할 수 없습니다.");
	}
	
</script>
<%@ include file="../common/footer.jsp"%>