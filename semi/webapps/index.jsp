<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="/views/common/header.jsp"%>
<!-- END nav -->

<section id="home-section" class="hero">
	<div class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url(resources/images/locMain-1.JPG);">
			<div class="overlay"></div>
			<div class="container">
				<div
					class="row slider-text justify-content-center align-items-center"
					data-scrollax-parent="true">

					<div class="col-md-12 ftco-animate text-center">
						<h1 class="mb-2">　</h1>
						<h2 class="subheading mb-4">언제나 신선한 음식을 맛보세요</h2>
						<p>
							<a href="<%= request.getContextPath() %>/pdMenu.pd" class="btn btn-primary">자세히 보기</a>
						</p>
					</div>

				</div>
			</div>
		</div>

		<div class="slider-item"
			style="background-image: url(resources/images/locMain-2.JPG);">
			<div class="overlay"></div>
			<div class="container">
				<div
					class="row slider-text justify-content-center align-items-center"
					data-scrollax-parent="true">

					<div class="col-sm-12 ftco-animate text-center">
						<h1 class="mb-2">100% 신선도 &amp; Organic Foods</h1>
						<h2 class="subheading mb-4">기분이 좋아지는 레시피
							&amp; L O C</h2>
						<p>
							<a href="<%= request.getContextPath() %>/pdMenu.pd" class="btn btn-primary">자세히 보기</a>
						</p>
					</div>

				</div>
			</div>
		</div>
		
		<div class="slider-item"
			style="background-image: url(resources/images/bg_1.jpg);">
			<div class="overlay"></div>
			<div class="container">
				<div
					class="row slider-text justify-content-center align-items-center"
					data-scrollax-parent="true">

					<div class="col-md-12 ftco-animate text-center">
						<h1 class="mb-2">Good Tasty</h1>
						<h2 class="subheading mb-4">언제나 신선한 음식을 맛보세요</h2>
						<p>
							<a href="<%= request.getContextPath() %>/pdMenu.pd" class="btn btn-primary">자세히 보기</a>
						</p>
					</div>

				</div>
			</div>
		</div>
	</div>
</section>

<section class="ftco-section">
	<div class="container">
		<div class="row no-gutters ftco-services">
			<div
				class="col-md-3 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div
						class="icon bg-color-1 active d-flex justify-content-center align-items-center mb-2">
						<span class="flaticon-shipped"></span>
					</div>
					<div class="media-body">
						<h3 class="heading">무료배송</h3>
						<span>5만원 이상 구매시 ^^</span>
					</div>
				</div>
			</div>
			<div
				class="col-md-3 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div
						class="icon bg-color-2 d-flex justify-content-center align-items-center mb-2">
						<span class="flaticon-diet"></span>
					</div>
					<div class="media-body">
						<h3 class="heading">신선한 음식</h3>
						<span>신선한 상품만 취급</span>
					</div>
				</div>
			</div>
			<div
				class="col-md-3 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div
						class="icon bg-color-3 d-flex justify-content-center align-items-center mb-2">
						<span class="flaticon-award"></span>
					</div>
					<div class="media-body">
						<h3 class="heading">질좋은 음식</h3>
						<span>둘이먹다 하나가 죽어도 모르는 맛</span>
					</div>
				</div>
			</div>
			<div
				class="col-md-3 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div
						class="icon bg-color-4 d-flex justify-content-center align-items-center mb-2">
						<span class="flaticon-customer-service"></span>
					</div>
					<div class="media-body">
						<h3 class="heading">좋은 서비스</h3>
						<span>언제나 친절한 서비스</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<section class="ftco-section testimony-section">
	<div class="container">
		<div class="row justify-content-center mb-5 pb-3">
			<div class="col-md-7 heading-section ftco-animate text-center">
				<span class="subheading">만든사람들</span>
				<h2 class="mb-4">제작자의 이야기를 들어보세요</h2>
				<p>감사감사합니다</p>
			</div>
		</div>
		<div class="row ftco-animate">
			<div class="col-md-12">
				<div class="carousel-testimony owl-carousel">
					<div class="item">
						<div class="testimony-wrap p-4 pb-5">
							<div class="user-img mb-5" style="background-image: url(resources/images/박민혁.jpg)">
								<span class="quote d-flex align-items-center justify-content-center">
									<i class="icon-quote-left"></i>
								</span>
							</div>
							<div class="text text-center" style="width: 300px">
								<p class="mb-5 pl-4 line">고생이 많으셨습니다. <br /><br />전 안녕히 떠나겠습니다..</p>
								<p class="name">박민혁</p>
								<span class="position">★결제기능 구축 <br />★ 배송지 UI 구축</span>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="testimony-wrap p-4 pb-5">
							<div class="user-img mb-5"
								style="background-image: url(resources/images/강신형.jpg)">
								<span class="quote d-flex align-items-center justify-content-center">
                      				<i class="icon-quote-left"></i>
								</span>
							</div>
							<div class="text text-center" style="width: 300px">
								<p class="mb-5 pl-4 line"><br />쇠보다 코드가 몇배는 무거웠습니다 <br /><br /></p>
								<p class="name">강신형</p>
								<span class="position">★회원관리</span>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="testimony-wrap p-4 pb-5">
							<div class="user-img mb-5"
								style="background-image: url(resources/images/김승규.jpg)">
								<span class="quote d-flex align-items-center justify-content-center">
                     				 <i class="icon-quote-left"></i>
								</span>
							</div>
							<div class="text text-center" style="width: 300px">
								<p class="mb-5 pl-4 line">팀원분 모두 정말 수고많으셨습니다. <br />앞으로도 화이팅! <br />p.s. 잠은 죽어서 자자!</p>
								<p class="name">김승규</p>
								<span class="position">★공지사항 <br />★게시판 구현</span>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="testimony-wrap p-4 pb-5">
							<div class="user-img mb-5"
								style="background-image: url(resources/images/강민정.jpg)">
								<span class="quote d-flex align-items-center justify-content-center">
                      				<i class="icon-quote-left"></i>
								</span>
							</div>
							<div class="text text-center" style="width: 300px">
								<p class="mb-5 pl-4 line">다같이 성장해 가는 기분을 느낄 수 있었던 좋은 경험이었습니다. <br />코딩.. 쉽지않네요</p>
								<p class="name">강민정</p>
								<span class="position">★친절한 상담원<br />★레시피, 장바구니, 구매, 관리 구현</span>
							</div>
						</div>
					</div>
					<div class="item">
						<div class="testimony-wrap p-4 pb-5">
							<div class="user-img mb-5"
								style="background-image: url(resources/images/봉창희5.jpg)">
								<span class="quote d-flex align-items-center justify-content-center">
                      				<i class="icon-quote-left"></i>
								</span>
							</div>
							<div class="text text-center" style="width: 300px">
								<p class="mb-5 pl-4 line">서로 조율하며 모르는 부분을 보완해가며 만든 프로젝트인 만큼 정말 재밌고 좋은 경험이였습니다. 좋은하루되세요</p>
								<p class="name">봉창희</p>
								<span class="position">★팀원들이 더 나은 결과를 이끌기 위해 <br>끝없는 의견 제시  <br />★ 공지사항과 게시판 제작</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<hr>

<%@ include file="views/common/footer.jsp"%>

