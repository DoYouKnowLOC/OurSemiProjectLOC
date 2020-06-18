package com.kh.loc.payment.model.vo;

import java.io.Serializable;

public class Payment extends Delivery implements Serializable {

	private static final long serialVersionUID = 123456L;
	private int payNo;
	private int ono;
	private String pdCode;
	private String userId;
	private String pdName;
	private int pdPrice;
	private int count;
	private String pdPreview;
	private String pdSubCont;
	private String dStatus;
	private int totalPrice;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int dNo, String pdCode, int oNo, String dAddress, String dPhone, String dName, String dStatus) {
		super(dNo, pdCode, oNo, dAddress, dPhone, dName, dStatus);
		// TODO Auto-generated constructor stub
	}


	public Payment(String pdCode, int oNo, String dAddress, String dPhone, String dName) {
		super(pdCode, oNo, dAddress, dPhone, dName);
		// TODO Auto-generated constructor stub
	}

	public Payment(int payNo, int ono) {
		super();
		this.payNo = payNo;
		this.ono = ono;
	}

	public Payment(int totalPrice) {
		super();
		this.totalPrice = totalPrice;
	}

	public Payment(int ono, String pdCode, int count) {
		super();
		this.ono = ono;
		this.pdCode = pdCode;
		this.count = count;
	}

	public Payment(int payNo, int ono, String pdCode, String userId, String pdName, int pdPrice, int count,
			String pdPreview, String pdSubCont, String dStatus, int totalPrice) {
		super();
		this.payNo = payNo;
		this.ono = ono;
		this.pdCode = pdCode;
		this.userId = userId;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.count = count;
		this.pdPreview = pdPreview;
		this.pdSubCont = pdSubCont;
		this.dStatus = dStatus;
		this.totalPrice = totalPrice;
	}

	public Payment(int ono, String pdCode, String userId, String pdName, int pdPrice, int count, String pdPreview,
			String pdSubCont, String dStatus) {
		super();
		this.ono = ono;
		this.pdCode = pdCode;
		this.userId = userId;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.count = count;
		this.pdPreview = pdPreview;
		this.pdSubCont = pdSubCont;
		this.dStatus = dStatus;
	}

	public Payment(int ono, String pdCode, String pdName, int pdPrice, int count, String pdPreview, String pdSubCont,
			String dStatus) {
		super();
		this.ono = ono;
		this.pdCode = pdCode;
		this.pdName = pdName;
		this.pdPrice = pdPrice;
		this.count = count;
		this.pdPreview = pdPreview;
		this.pdSubCont = pdSubCont;
		this.dStatus = dStatus;
	}

	public int getPayNo() {
		return payNo;
	}

	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public int getOno() {
		return ono;
	}

	public void setOno(int ono) {
		this.ono = ono;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getPdPreview() {
		return pdPreview;
	}

	public void setPdPreview(String pdPreview) {
		this.pdPreview = pdPreview;
	}

	public String getPdSubCont() {
		return pdSubCont;
	}

	public void setPdSubCont(String pdSubCont) {
		this.pdSubCont = pdSubCont;
	}

	public String getdStatus() {
		return dStatus;
	}

	public void setdStatus(String dStatus) {
		this.dStatus = dStatus;
	}
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Payment [payNo=" + payNo + ", ono=" + ono + ", pdCode=" + pdCode + ", userId=" + userId + ", pdName="
				+ pdName + ", pdPrice=" + pdPrice + ", count=" + count + ", pdPreview=" + pdPreview + ", pdSubCont="
				+ pdSubCont + ", dStatus=" + dStatus + "]";
	}
	
	

}