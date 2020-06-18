package com.kh.loc.payment.model.vo;

import java.io.Serializable;

public class Cart implements Serializable {

	private static final long serialVersionUID = 5883L;
	
	private int cartNo;
	private String pdCode;
	private String userId;
	private int count;
	private String pdName;
	private int pdPrice;
	private String pdPreview;
	private int totalPrice;
	private String pdSubCont;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartNo, String pdCode, String userId, int count, String pdName, int pdPrice, String pdPreview,
			int totalPrice, String pdSubCont) {
		super();
		this.cartNo = cartNo;
		this.pdCode = pdCode;
		this.userId = userId;
		this.count = count;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdPreview = pdPreview;
		this.totalPrice = totalPrice;
		this.pdSubCont = pdSubCont;
	}

	public Cart(String pdCode, String userId, int count, int totalPrice) {
		super();
		this.pdCode = pdCode;
		this.userId = userId;
		this.count = count;
		this.totalPrice = totalPrice;
	}

	public Cart(String pdCode, String userId, int count, String pdName, int pdPrice, String pdPreview, int totalPrice, String pdSubCont) {
		super();
		this.pdCode = pdCode;
		this.userId = userId;
		this.count = count;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.pdPreview = pdPreview;
		this.totalPrice = totalPrice;
		this.pdSubCont = pdSubCont;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}

	public String getPdPreview() {
		return pdPreview;
	}

	public void setPdPreview(String pdPreview) {
		this.pdPreview = pdPreview;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	
	public String getPdSubCont() {
		return pdSubCont;
	}

	public void setPdSubCont(String pdSubCont) {
		this.pdSubCont = pdSubCont;
	}

	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", pdCode=" + pdCode + ", userId=" + userId + ", count=" + count + ", pdName="
				+ pdName + ", pdPrice=" + pdPrice + ", pdPreview=" + pdPreview + ", totalPrice=" + totalPrice + ", pdSubCont=" + pdSubCont + "]";
	}
	
	
	
	
}