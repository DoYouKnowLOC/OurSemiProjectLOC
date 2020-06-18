<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴하기</title>
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
                  <form action="<%= request.getContextPath() %>/mDelete.me" method="post" class="billing-form" id="deleteForm">
                     <h3 class="mb-4 billing-heading">회원탈퇴</h3>
                     
                <div class="row align-items-end">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="country">사용자 이름</label>
                       <input type="text" class="form-control" name="userName" id="userName" placeholder="">
                      </div>
                     </div>
                  </div>
                  
                  <div class="row align-items-end">
                 <div class="col-md-6">
                   <div class="form-group">
                      <label for="lastname">비밀번호</label>
                     <input type="password" class="form-control" name="userPwd" id="userPwd" placeholder="">
                   </div>
                </div>
                </div>
               <div class="btns" align="center">
					<div id="joinBtn" onclick="deleteMember();">탈퇴하기</div>
				</div>
                  </div>
                  
				
             </form><!-- END -->
  		</div>
             
               
          </div> <!-- .col-md-8 -->
        </div>
      </div>
    </section> <!-- .section -->
<%@ include file="../common/footer.jsp" %>
</body>
</html>