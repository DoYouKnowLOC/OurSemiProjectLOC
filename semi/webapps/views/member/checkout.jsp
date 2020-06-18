<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<style>
#idCheck, #ckZip, #goMain, #joinBtn {
	background: skyblue;
	border-radius: 5px;
	width: 150px;
	height: 50px;
	text-align: center;
	float: left;
	margin-right: 100px;
}
/* .row justify-content-center{
	margin: 0 auto;
	
	} */
</style>
<div class="hero-wrap hero-bread"
	style="background-image: url(<%=request.getContextPath()%>/resources/images/bg_1.jpg);">
	<div class="container">
		<div
			class="row no-gutters slider-text align-items-center justify-content-center">
			<div class="col-md-9 ftco-animate text-center">
				<p class="breadcrumbs">
					<span class="mr-2"></span>
				</p>
				<h1 class="mb-0 bread">회원가입</h1>
			</div>
		</div>
	</div>
</div>

<section class="ftco-section">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-xl-7 ftco-animate">
				<form action="<%=request.getContextPath()%>/mInsert.me"
					  method="post" class="billing-form" id="joinForm">
					<h3 class="mb-4 billing-heading">회원가입</h3>
					<div class="row align-items-end">
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">아이디</label>
								<input type="text" class="form-control" name="userId"
									id="userId_login">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" style="padding-bottom: 55px">
								<button id="idCheck" type="button">중복확인</button>
							</div>
						</div>
						<div class="w-100"></div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">비밀번호</label> <input type="password"
									class="form-control" name="userPwd" id="userPwd_login">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">비밀번호 확인</label> <input type="password"
									class="form-control" name="userPwd2" id="userPwd2" >
									

							</div>
						</div>
						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="country">사용자 이름</label> <input type="text"
									class="form-control" name="userName" id="userName">
							</div>
						</div>

						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="streetaddress">전화번호</label> <input type="text"
									class="form-control" name="phone" id="phone">
							</div>
						</div>

						<div class="col-md-6">
							<div class="form-group">
								<label for="streetaddress">우편번호</label> <input type="text"
									class="form-control" id="zipCode" name="zipCode">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group" style="padding-bottom: 55px">
								<button type="button" id="ckZip" onclick="addrSearch();">검색</button>
							</div>
						</div>

						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="streetaddress">주소</label> <input type="text"
									class="form-control" id="address1" name="address1">
							</div>
						</div>


						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="streetaddress">상세주소</label> <input type="text"
									class="form-control" id="address2" name="address2">

							</div>
						</div>


						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group">
								<label for="towncity">이메일</label> <input type="text"
									class="form-control" id="email" name="email">
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label for="birth">생년월일</label> 
								<input type="date" class="form-control" name="birth" id="birth" max="9999-12-31" placeholder="">
							</div>
						</div>

						<div class="w-100"></div>
						<div class="col-md-12">
							<div class="form-group"  style="padding-left: 90px">
								<button id="goMain" type="button">메인으로</button>
								<button id="joinBtn" type="submit">가입하기</button>
							</div>
						</div>
					</div>
				</form>
				<!-- END -->

				<!-- 다음 주소 aip 태그 -->
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
                 
<!--                  <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->

				<script>
					/* function insertMember() {
						$("#joinForm").submit();
					} */
					
					$("#joinForm").submit(function(event){
						if($("#userPwd_login").val() == "" || ($("#userId_login").val() == "")) alert("아이디나 비밀번호는 필수 값입니다.");
						else if($('#userPwd_login').val()!=($('#userPwd2').val())) alert("비밀번호 확인 값과 다릅니다.");
						else return;

						event.preventDefault();
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
					
					$('#goMain').on('click', function(){
						location.href='/loc/index.jsp';
					});

					$('#idCheck').on('click', function() {
						$.ajax({
							url : '/loc/idDup.do',
							type : 'post',
							data : {
								userId : $("#joinForm input[name=userId]").val()
							},
							success : function(data) {
								console.log(data);

								// 결과가 0이면 사용자 없음 : 가입 가능
								//        1이면 1 명 사용중 : 가입 불가
								if (data == 0) {
									alert("사용 가능한 아이디입니다.");
								} else {
									alert("이미 사용 중인 아이디입니다.");
								}

							},
							error : function() {
								console.log("에러 발생~!!");
							}
						});
					});
				</script>

			</div>


		</div>
		<!-- .col-md-8 -->
	</div>
</section>
<!-- .section -->
<script>
	$(document).ready(function() {

		var quantitiy = 0;
		$('.quantity-right-plus').click(function(e) {

			// Stop acting like a button
			e.preventDefault();
			// Get the field name
			var quantity = parseInt($('#quantity').val());

			// If is not undefined

			$('#quantity').val(quantity + 1);

			// Increment
});
		$('.quantity-left-minus').click(function(e) {
			// Stop acting like a button
			e.preventDefault();
			// Get the field name
			var quantity = parseInt($('#quantity').val());

			// If is not undefined

			// Increment
			if (quantity > 0) {
				$('#quantity').val(quantity - 1);
			}
		});

	});
</script>
<%@ include file="../common/footer.jsp"%>