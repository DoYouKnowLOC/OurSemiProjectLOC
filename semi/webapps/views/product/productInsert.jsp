<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../common/header.jsp"%>
<!-- title -->
	<section class="ftco-section">
	<form action="<%=request.getContextPath()%>/pdInsert.pd" method="post" 
	  enctype="multipart/form-data">
			<div class="container">
				<div class="row">
					<div class="col-lg-5 mb-5 ftco-animate" id="titleImgArea"
						  style="border: 1px black solid; height: auto; margin-left:70px">
						<!-- product img 삽입하는 곳 -->
						<input type="file" id="pdPreview" name="pdPreview" />
						<!-- <img class="img-fluid" alt="titleImg" style="height: 300px; width: 450px" id="pdPreview" name="pdPreview"> --> 
					</div>
					<div class="col-lg-6 product-details pl-md-5 ftco-animate">
						<table style="border: white;">
							<tr>
								<td><label for="productTitle">상품명</label></td>
								<td><input type="text" class="productTitle" name="pdName"
									id="pdName" /></td>
							</tr>
							<tr>
								<td><label for="productPrice">가격</label></td>
								<td><input type="number" class="productPrice" name="pdPrice"
									id="pdPrice"></td>
									<!-- onKeyUp="removeChar(event);inputNumberFormat(this);" -->
							</tr>
							<tr>
								<td><label for="pdSubCont">부연설명</label></td>
								<td><textarea class="text=left mr-4" name="pdSubCont"
										id="pdSubCont" rows="5" cols="37" style="resize: none;"></textarea>
								</td>
							</tr>
						</table>

					</div>
					<div class="col-lg-12 product-details pl-md-5 ftco-animate">
						<br />
						<hr />
						<br />
					</div>
					<div class="col-lg-12 product-details pl-md-5">
						<!-- form 안에 에디터를 사용하는 경우 (보통 이경우를 많이 사용하는듯)-->
						<textarea id="pdCont" class="summernote" name="pdCont"></textarea>
					</div>
					<div class="col-lg-12 product-details pl-md-5 ftco-animate">
						<br />
						<hr />
						<br />
					</div>
					<!-- 버튼 등록 -->
					<div class="col-md-12 mb-5 text-center">
						<ul class="product-category">
							<li style="padding-right: 100px">
								<button class="btn btn-black py-3 px-5" type="reset">취소하기</button>
							</li>
							<li>
								<button class="btn btn-black py-3 px-5" type="submit">작성완료</button>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</form>
	</section>
<script>
	//여기 아래 부분
	var check = $('.summernote').summernote({
		  height : 600 // 에디터 높이
		, minHeight : null // 최소 높이
		, maxHeight : null // 최대 높이
		, focus : true // 에디터 로딩후 포커스를 맞출지 여부
		, lang : "ko-KR" // 한글 설정
		, placeholder : '최대 2048자까지 쓸 수 있습니다' //placeholder 설정
		, toolbar: [
            // [groupName, [list of button]]
            ['style', ['style']],
            ['font', ['strikethrough', 'bold', 'underline', 'clear']],
            ['Font Style', ['fontname']],
            ['fontsize', ['fontsize']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['height', ['height']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
         ], callbacks : {
			onImageUpload : function(files, editor,
					welEditorble) {
				console.log(files);
				console.log(files[0]);
				data = new FormData();
				data.append("file", files[0]);
				var $note = $(this);
				
				$.ajax({
					data : data,
					type : "post",
					url : '/loc/pdImgInsert.pd', // servlet url
					cache : false,
					contentType : false,
					processData : false,
					success : function(fileUrl) {
						check.summernote('insertImage', fileUrl);
						alert("성공이미지");
					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n"
								+ "message:"
								+ request.responseText + "\n"
								+ "error:" + error);
						console.log("왜안되니 파일22"+files);
					}
				});
			}
		}
	});
	$("div.note-editable").on('drop',function(e){
        for(i=0; i< e.originalEvent.dataTransfer.files.length; i++){
        	uploadSummernoteImageFile(e.originalEvent.dataTransfer.files[i],$("#summernote")[0]);
        }
       e.preventDefault();
	});
	
	//문자 제거
	function removeChar(event) {
	    event = event || window.event;
	    var keyID = (event.which) ? event.which : event.keyCode;
	    if (keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39)
	        return;
	    else
	        //숫자와 소수점만 입력가능
	        event.target.value = event.target.value.replace(/[^-\.0-9]/g, "");
	}
	//콤마 찍기
    function comma(obj) {
        var regx = new RegExp(/\B(?=(\d{3})+(?!\d))/g);
        while (regx.test(obj.value)) {//문자열에 정규식 특수문자가 포함되어 있는지 체크
            //정수 부분에만 콤마 달기 
            obj = obj.value.replace(regx, ",");//콤마추가하기
        }
        return obj;//문자열 반환
    }
	//콤마 풀기
	function uncomma(str) {
	    str = "" + str.replace(/,/gi, ''); // 콤마 제거 
	    str = str.replace(/(^\s*)|(\s*$)/g, ""); // trim()공백,문자열 제거 
	    return (new Number(str));//문자열을 숫자로 반환
	}
	//input box 콤마달기
	function inputNumberFormat(obj) {
	    obj.value = comma(obj.value);
	}
	//input box 콤마풀기 호출
	function uncomma_call(){
	    var input_value = document.getElementById('input1');
	    input_value.value = uncomma(input_value.value);
	}
	
</script>
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />

<%@ include file="../common/footer.jsp"%>


