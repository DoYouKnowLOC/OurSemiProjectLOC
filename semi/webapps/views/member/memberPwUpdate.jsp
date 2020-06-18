<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 비밀번호 설정</title>
<style>
</style>
</head>
<script>
	function deleteMember(){
		$("#deleteForm").submit();
	}
</script>
<body>
<%@ include file="../common/header.jsp" %>
	<section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-xl-7 ftco-animate">
                  <form action="<%= request.getContextPath() %>/MemberPw.do" method="post" class="billing-form" id="PwForm">
                     <h3 class="mb-4 billing-heading">비밀번호 설정</h3>
                     <input type="hidden" name="userId" value="<%=(String)request.getAttribute("userId")%>"/>
                <div class="row align-items-end">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="country">새 비밀번호</label>
                       <input type="password" class="form-control" name="re_userPwd" id="re_userPwd" placeholder="">
                      </div>
                     </div>
                  </div>
                  
                  <div class="row align-items-end">
                 <div class="col-md-6">
                   <div class="form-group">
                      <label for="lastname">새 비밀번호 확인</label>
                     <input type="password" class="form-control" name="re_userPwd2" id="re_userPwd2" placeholder="">
                   </div>
                </div>
                </div>
              
                  <button id="goMain" type="submit">수정완료</button>
                  
				
             </form><!-- END -->
  		</div>
  		
  		<script>
  			$('#PwForm').submit(function(event){
  				if($('#re_userPwd').val() != $('#re_userPwd2').val()){
  					alert("비밀번호 확인 값이 다릅니다.")	;
  					event.preventDefault(); // submit
  				} else {
  					alert("변경 완료!");
  					return;
  				}
  			});
  		</script>
             
               
          </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->
<%@ include file="../common/footer.jsp" %>
</body>
</html>