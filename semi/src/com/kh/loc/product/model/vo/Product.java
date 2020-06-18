package com.kh.loc.product.model.vo;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 5882L;
	
	private String pdCode;
	private int pdPrice;
	private String pdName;
	private String pdStatus;
	private String pdCont;
	private String pdSubCont;
	private String pdPreview;

	public Product() {
	}

	public Product(String pdCode, int pdPrice, String pdName, String pdStatus, String pdCont,
			String pdSubCont, String pdPreview) {
		super();
		this.pdCode = pdCode;
		this.pdPrice = pdPrice;
		this.pdName = pdName;
		this.pdStatus = pdStatus;
		this.pdCont = pdCont;
		this.pdSubCont = pdSubCont;
		this.pdPreview = pdPreview;
	}

	public Product(int pdPrice, String pdName, String pdCont, String pdSubCont, String pdPreview) {
		super();
		this.pdPrice = pdPrice;
		this.pdName = pdName;
		this.pdCont = pdCont;
		this.pdSubCont = pdSubCont;
		this.pdPreview = pdPreview;
	}
	
	public Product(int pdPrice, String pdName, String pdCont, String pdSubCont) {
		super();
		this.pdPrice = pdPrice;
		this.pdName = pdName;
		this.pdCont = pdCont;
		this.pdSubCont = pdSubCont;
	}
	
	@Override
	public String toString() {
		return "Product [pdCode=" + pdCode + ", pdPrice=" + pdPrice + ", pdName=" + pdName 
				+ ", pdStatus=" + pdStatus + ", pdCont=" + pdCont + ", pdSubCont=" + pdSubCont 
				+ ", pdPreview=" + pdPreview + "]";
	}

	public String getPdCode() {
		return pdCode;
	}

	public void setPdCode(String pdCode) {
		this.pdCode = pdCode;
	}

	public int getPdPrice() {
		return pdPrice;
	}

	public void setPdPrice(int pdPrice) {
		this.pdPrice = pdPrice;
	}

	public String getPdName() {
		return pdName;
	}

	public void setPdName(String pdName) {
		this.pdName = pdName;
	}

	public String getPdStatus() {
		return pdStatus;
	}

	public void setPdStatus(String pdStatus) {
		this.pdStatus = pdStatus;
	}

	public String getPdCont() {
		return pdCont;
	}

	public void setPdCont(String pdCont) {
		this.pdCont = pdCont;
	}

	public String getPdSubCont() {
		return pdSubCont;
	}

	public void setPdSubCont(String pdSubCont) {
		this.pdSubCont = pdSubCont;
	}

	public String getPdPreview() {
		return pdPreview;
	}

	public void setPdPreview(String pdPreview) {
		this.pdPreview = pdPreview;
	}

	

	
}