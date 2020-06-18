package com.kh.loc.member.model.vo;

import java.io.Serializable;

import java.sql.Date;

public class Member implements Serializable {

	// 다른 위치에 정의된 Member 클래스가 있을 경우
	// 둘을 헷갈리지 않게 하기 위한 전용 ID 설정
	private static final long serialVersionUID = 109L;
	/*
	 * USERID, PASSWORD, USERNAME, GENDER, AGE, 
	 * EMAIL, PHONE, ADDRESS, HOBBY, ENROLLDATE
	 */
	
	// 1. 필드변수 / 멤버 변수
	private String userId;   // 사용자 아이디
	private String userPwd;  // 사용자 비밀번호

	private String userName; // 사용자 이름
	private String gender;   // 성별
	private String email;	 // 이메일
	private String phone;	 // 연락처
	private String address;  // 주소
	private Date enrollDate; // 가입일(java.sql.Date)
	private String birth;	 // 생년월일
	
	// 2. 생성자
	// 기본 생성자
	public Member() {}
	
	// 로그인용 생성자
	public Member(String userId, String userPwd) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
	}

	public Member(String userId, String email, String phone) {
		super();
		this.userId = userId;
		this.email = email;
		this.phone = phone;
	}
	
	// 회원 가입용 생성자
	public Member(String userId, String userPwd, String userName, String gender, String email, String phone,
			String address, String birth) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birth = birth;
	}

	// 회원의 전체 데이터를 가져오기 위한 생성자
	public Member(String userId, String userPwd, String userName, String gender, String email, String phone,
			String address, Date enrollDate, String birth) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.enrollDate = enrollDate;
		this.birth = birth;
	}
	
	// toString 재정의
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", gender=" + gender
				+ ", email=" + email + ", phone=" + phone + ", address=" + address 
				+ ", enrollDate=" + enrollDate + ", birth=" + birth + "]";
	}
	
	// getter & setter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
}
