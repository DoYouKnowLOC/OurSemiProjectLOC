<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.loc.payment.model.vo.PdOrder"%>
<%@ include file="../common/header.jsp"%>
<%
	String address = m.getAddress();
	String p1 = "/";
	String[] addArray = address.split(p1);
	
	PdOrder o = (PdOrder)request.getAttribute("order");
	
	HashMap<String, Object> hmap = (HashMap<String, Object>)request.getAttribute("hmap");
%>

<div class="hero-wrap hero-bread"
	style="background-image: url('<%=request.getContextPath()%>/resources/images/bg_1.jpg');">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"><a href="<%=request.getContextPath()%>/index.jsp">Home</a></span> <span>결제하기</span>
				</p>
				<h1 class="mb-0 bread">결제하기</h1>
			</div>
		</div>
	</div>
</div>
<section class="ftco-section">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-xl-7 ftco-animate">
				<form action="<%=request.getContextPath() %>/payment.p" method="post" id="iPayment" class="billing-form">
				
 						<% ArrayList<PdOrder> plist = (ArrayList<PdOrder>)hmap.get("poList"); %>
							<% 
								int pcount = 0;
								pcount = plist.size();
							%>
							<%-- <% for(PdOrder p : plist){ %> --%>
							<% for(int i = 0; i < plist.size(); i++){ %>
								<input type="hidden" name="pPdcode-<%=i%>" value="<%=plist.get(i).getPdCode() %>" />
								<input type="hidden" name="pCount-<%=i%>" value="<%=plist.get(i).getCount() %>" />
							<% } %>
								<input type="hidden" name="listCount" value="<%=pcount %>" />
 						<input type="hidden" name="totalPrice" value="<%=hmap.get("sum") %>" />
 						<div class="row align-items-end">
						<div class="w-100"></div>
						<div class="w-100"></div>
						<h3 class="mb-4 billing-heading">결제하기</h3>
						<div class="col-md-12">
							<div class="form-groupclick reset mt-4">
								<div class="radio">
									<label class="mr-3">
										<input type="radio" name="optradio" id="sameAddr" checked> 동일한 주소로 배송</label> &nbsp;&nbsp;&nbsp;
									<label><input
										type="radio" id="newAddr1" name="optradio"> 새로운 주소로 배송</label>
									<script>
										$('#newAddr1').on('click', function() {
											if ($(this).prop('checked') == true) {
												$('input').attr("value", "");
											}
										});
										
										$('#sameAddr').on('click', function() {
											if ($(this).prop('checked') == true) {
												$('#shipName').attr("value", "<%=m.getUserName()%>");
												$('#phone').attr("value", "<%= m.getPhone() %>");
												$('#email').attr("value", "<%= m.getEmail() %>");
												$('#zipCode').attr("value", "<%= addArray[0] %>");
												$('#address1').attr("value", "<%= addArray[1] %>");
												$('#address2').attr("value", "<%= addArray[2] %>");
											}
										});
									</script>
								</div>
							</div>
						</div>
						<hr />														
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">이름</label> <input type="text"
									class="form-control" id="shipName" name="dName"
									value="<%= m.getUserName() %>">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="phone">연락처</label> <input type="text"
									class="form-control" id="phone" name="dPhone"
									value="<%= m.getPhone() %>">
							</div>
						</div>
						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="email-fir">이메일</label> <input type="text"
									class="form-control" id="email" 
									value="<%= m.getEmail() %>">
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<label for="postcodezip">우편번호*</label> <input type="text"
									class="form-control" id="zipCode" name="zipCode"
									value="<%= addArray[0] %>">
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<p>
									<a href="#" class="btn btn-primary py-3 px-4" onclick="addrSearch();">검색하기</a>
								</p>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="country">주소</label> <input type="text" 
									class="form-control" id="address1" name="address1"
									value="<%= addArray[1] %>">
							</div>
						</div>
						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="streetaddress">상세주소</label> <input type="text"
									class="form-control" id="address2" name="address2"
									value="<%= addArray[2] %>">
							</div>
						</div>
					</div>
				</form>
				<!-- END -->
			</div>
			<div class="col-xl-5">
				<div class="row mt-5 pt-3">
					<div class="col-md-12 d-flex mb-5">
						<div class="cart-detail cart-total p-3 p-md-4">
							<h2 class="billing-heading mb-4">Cart Total</h2>
							<p class="d-flex total-price">
								<span>상품 총 금액</span> <span id="price1"><%=hmap.get("sumMoney") %></span>
							</p>
							<p class="d-flex total-price">
								<span>배송비</span> <span id="price2"><%=hmap.get("fee") %></span>
							</p>
							<hr />
							<p class="d-flex total-price">
								<span>총 결제금액</span> <span id="price"><%=hmap.get("sum") %></span>
							</p>
						</div>
					</div>
					<div class="col-md-12">
						<div class="cart-detail p-3 p-md-4">
							<p>
								<button class="btn btn-primary py-3 px-4" id="payBtn">결제하기</button>
							</p>
						</div>
					</div>
				</div>
			</div>
			<!-- .col-md-8 -->
		</div>
	</div>
</section>
<!-- .section -->
	<script type="text/javascript"
		src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script>
		var IMP = window.IMP; // 생략가능

		$(function() {
			IMP.init('imp11761491');
		}); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		/* $('#payBtn').on('click', function(){
			$("#iPayment").submit();
			location.href="/loc/views/product/payComplete.jsp";
		});*/
		$('#payBtn').on('click', function(){
			// 문서 로딩될 때 바로 호출
				IMP.request_pay({
					pg : 'kakao',
				    pay_method : 'card',
				    merchant_uid : 'merchant_' + new Date().getTime(),
					name : '맛좋은 음식',
					amount : parseInt($('#price').text()),
					buyer_email : $('#email').val(),
					buyer_name : $('#shipName').val(),
					buyer_tel : $('#phone').val(),
					buyer_addr : $('#address1').val()+$('#address2').val(),
					buyer_postcode : $('#zipcode').val()
				}, function(rsp) {
					if (rsp.success) {
						//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
						$.ajax({
							url : "/test/orderconfirm.do", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
							type : 'POST',
							dataType : 'json',
							data : {
								item : 'toy',
								code : 'P0001',
								quan : '<%=hmap.get("sum") %>',
								imp_uid : rsp.imp_uid,
								pay_method : rsp.pay_method,
								price : rsp.paid_amount,
								status : rsp.status,
								title : rsp.name,
								pg_tid : rsp.pg_tid,
								buyer_name : rsp.buyer_name,
								paid_at : rsp.paid_at,
								receipt_url : rsp.receipt_url
							//기타 필요한 데이터가 있으면 추가 전달
							}
						});
						location.href="/loc/views/product/payComplete.jsp";
						$("#iPayment").submit();
					} else {
						var msg = '결제에 실패하였습니다.';
						msg += '\n에러내용 : ' + rsp.error_msg;
						alert(msg);
					}
				});
			 
		});
		
		// 참조 API : http://postcode.map.daum.net/guide
		function addrSearch() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var fullAddr = ''; // 최종 주소 변수
	                var extraAddr = ''; // 조합형 주소 변수

	                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    fullAddr = data.roadAddress;

	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    fullAddr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	                if(data.userSelectedType === 'R'){
	                    //법정동명이 있을 경우 추가한다.
	                    if(data.bname !== ''){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있을 경우 추가한다.
	                    if(data.buildingName !== ''){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                $('#zipCode').val(data.zonecode); //5자리 새우편번호 사용
	                
	                $('#address1').val(fullAddr);

	                // 커서를 상세주소 필드로 이동한다.
	                $('#address2').focus();
	            }
	        }).open();
	    };
	    
	</script>
</body>
</html>
<%@ include file="../common/footer.jsp" %>