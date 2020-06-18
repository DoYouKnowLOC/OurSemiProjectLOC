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
					<span class="mr-2"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>Product-Manage</span>
				</p>
				<h1 class="mb-0 bread">Product-Manage</h1>
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
								<th>상품 명</th>
								<th>수량</th>
								<th>주문자 / <br />주문자번호</th>
								<th>주문 현황 /<br />배송지</th>
								<th>주문취소</th>
								<th>배송시작</th>
							</tr>
						</thead>
						<tbody>
							<%  %>
							<% for(Payment p : plist){ %>
								<input type="hidden" name="payno" value="<%=p.getPayNo() %>"/>
								<tr class="text-center" id="delivery-<%=p.getPayNo() %>">
									<td class="product-name" style="width: 20%;">
										<h3><%=p.getPdName() %></h3>
										<p><%=p.getPdSubCont() %></p>
									</td>
	
									<td class="quantity" style="width: 10%;">
										<p><%=p.getCount() %>개</p>
									</td>
	
									<td class="product-name" style="width: 20%;">
										<h3><%=p.getdName() %></h3>
										<p><%=p.getdPhone() %></p>
									</td>
								
									<% if(p.getdStatus().equals("N")){ %>
										<td class="product-name" style="width: 30%;">
											<h3 id="status-<%=p.getPayNo()%>">주문 확인 중</h3>
											<p><%=p.getdAddress() %></p>
										</td>
										<td class="product-remove" style="width: 10%;"><a onclick="deleteDelivery(<%=p.getPayNo()%>);"><span class="ion-ios-close"></span></a></td>
										<td class="product-remove" style="width: 10%;"><a onclick="goDelivery(<%=p.getPayNo()%>);"><span class="ion-ios">배송시작</span></a></td>
									<% } else if(p.getdStatus().equals("I"))  { %>
										<td class="product-name" style="width: 30%;">
											<h3 id="status-<%=p.getPayNo()%>">배송 중</h3>
											<p><%=p.getdAddress() %></p>
										</td>
										<td class="product-remove" style="width: 10%;"><a onclick="noCancel();"><span class="ion-ios-close"></span></a></td>
										<td class="product-remove" style="width: 10%;"><a onclick="alreadyD();"><span class="ion-ios">배송시작</span></a></td>
									<% } else { %>
										<td class="product-name" style="width: 30%;">
											<h3 id="status-<%=p.getPayNo()%>">배송 완료</h3>
											<p><%=p.getdAddress() %></p>
										</td>
										<td class="product-remove" style="width: 10%;"><a onclick="noCancel();"><span class="ion-ios-close"></span></a></td>
										<td class="product-remove" style="width: 10%;"><a onclick="alreadyD();"><span class="ion-ios">배송시작</span></a></td>
									<% } %>
									
									
								</tr>
							<% } %>
							<!-- 배송정보 TR-->
						</tbody>
					</table>
				</div>
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
	
	function goDelivery(payno){
		$.ajax({
			  url : '<%=request.getContextPath()%>/pmCheck.admin'
			, data : {payno : payno}
			, success : function(data){
				if(data > 0){
					alert("성공적으로 배송을 시작하였습니다.");
					location.href('<%=request.getContextPath()%>/dLIst.admin');
				} else {
					alert("서버 오류입니다. \n관리자에게 문의 주세요.");
				}
			}
		});
	}
	
	
	function noCancel(){
		alert("배송중인 상품은 취소할 수 없습니다.");
	}
	
	function alreadyD(){
		alert("이미 배송중인 상품입니다.");
	}
	
</script>
<%@ include file="../common/footer.jsp" %>