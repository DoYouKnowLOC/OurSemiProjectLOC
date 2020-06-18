package com.kh.loc.payment.model.vo;

import java.io.Serializable;

public class Delivery implements Serializable {

	private static final long serialVersionUID = 589055L;
	private int dNo;
	private String pdCode;
	private int oNo;
	private String dAddress;
	private String dPhone;
	private String dName;
	private String dStatus;

	public Delivery() {
	}

	public Delivery(int dNo, String pdCode, int oNo, String dAddress, String dPhone, String dName, String dStatus) {
		super();
		this.dNo = dNo;
		this.pdCode = pdCode;
		this.oNo = oNo;
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dName = dName;
		this.dStatus = dStatus;
	}

	public Delivery(String pdCode, int oNo, String dAddress, String dPhone, String dName) {
		super();
		this.pdCode = pdCode;
		this.oNo = oNo;
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dName = dName;
	}
	
	public Delivery(String dAddress, String dPhone, String dName, String dStatus) {
		super();
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dName = dName;
		this.dStatus = dStatus;
	}
	
	public Delivery(String dAddress, String dPhone, String dName) {
		super();
		this.dAddress = dAddress;
		this.dPhone = dPhone;
		this.dName = dName;
	}
	

	@Override
	public String toString() {
		return "Delivery [dNo=" + dNo + ", oNo=" + oNo + ", pdCode=" + pdCode + ", dAddress=" + dAddress + ", dPhone="
				+ dPhone + ", dName=" + dName + ", dStatus=" + dStatus + "]";
	}


	public int getdNo() {
		return dNo;
	}

	public void setdNo(int dNo) {
		this.dNo = dNo;
	}

	public int getoNo() {
		return oNo;
	}

	public void setoNo(int oNo) {
		this.oNo = oNo;
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public String getdAddress() {
		return dAddress;
	}

	public void setdAddress(String dAddress) {
		this.dAddress = dAddress;
	}

	public String getdPhone() {
		return dPhone;
	}

	public void setdPhone(String dPhone) {
		this.dPhone = dPhone;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdStatus() {
		return dStatus;
	}

	public void setdStatus(String dStatus) {
		this.dStatus = dStatus;
	}

}