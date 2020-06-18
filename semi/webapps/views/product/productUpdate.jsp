<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kh.loc.product.model.vo.*"%>

<%
	Product p = (Product) request.getAttribute("product");
%>
<%@ include file="../common/header.jsp"%>
<!-- title -->
<section class="ftco-section">
	<form action="<%=request.getContextPath()%>/pdUpdate.pd" method="post"
		  enctype="multipart/form-data">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 mb-5 ftco-animate" id="titleImgArea"
					style="border: 1px black solid; height: 350px; margin-left: 70px">
					<input type="hidden" name="pdcode" value="<%=p.getPdCode()%>" />
					<!-- product img 삽입하는 곳 -->
					<img id="pdPreview" name="pdPreview" style="height: 350px; width: 350px" align="middle"
						 src="<%= request.getContextPath() %>/resources/images/productUploadFiles/<%=p.getPdPreview()%>">
				</div>
				<div class="col-lg-6 product-details pl-md-5 ftco-animate">
					<table style="border: white;">
						<tr>
							<td><label for="productTitle">상품명</label></td>
							<td><input type="text" class="productTitle" name="pdName"
								id="pdName" value="<%=p.getPdName()%>" /></td>
						</tr>
						<tr>
							<td><label for="productPrice">가격</label></td>
							<td><input type="number" class="productPrice" name="pdPrice"
								id="pdPrice" value="<%=p.getPdPrice()%>" /></td>
						</tr>
						<tr>
							<td><label for="pdSubCont">부연설명</label></td>
							<td>
								<textarea class="text=left mr-4" name="pdSubCont"
										  id="pdSubCont" rows="5" cols="37" style="resize: none;"><%=p.getPdSubCont()%></textarea>
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
					<textarea id="pdCont" class="summernote" name="pdCont"><%=p.getPdCont()%></textarea>
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
							<button class="btn btn-black py-3 px-5" type="button" onclick="<%=request.getContextPath()%>/pdMenu.pd">취소하기</button>
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
			, toolbar : [
				// [groupName, [list of button]]
				[ 'style', [ 'style' ] ],
				['font',[ 'strikethrough', 'bold', 'underline','clear' ] ],
				[ 'Font Style', [ 'fontname' ] ],
				[ 'fontsize', [ 'fontsize' ] ],
				[ 'color', [ 'color' ] ],
				[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
				[ 'table', [ 'table' ] ],
				[ 'height', [ 'height' ] ],
				[ 'insert', [ 'link', 'picture', 'video' ] ],
				[ 'view', [ 'fullscreen', 'codeview', 'help' ] ] 
			], callbacks : {
				onImageUpload : function(files, editor, welEditorble) {
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
							check
									.summernote('insertImage', fileUrl);
							alert("업로드 성공~!");
						},
						error : function(request, status, error) {
							alert("code:" + request.status + "\n"
									+ "message:"
									+ request.responseText + "\n"
									+ "error:" + error);
							console.log("왜안되니 파일22" + files);
						}
					});
				}
			}
		});
	$("div.note-editable").on('drop', function(e) {
		for (i = 0; i < e.originalEvent.dataTransfer.files.length; i++) {
			uploadSummernoteImageFile(
					e.originalEvent.dataTransfer.files[i],
					$("#summernote")[0]);
		}
		e.preventDefault();
	});
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
