package com.kh.loc.payment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class PdOrder implements Serializable {

   private static final long serialVersionUID = 5884L;

   private int oNo;
   private String pdCode;
   private String userId;
   private Date oDate;
   private int oTotalPrice;
   private int count;

   public PdOrder() {
   }

   public PdOrder(int oNo, String pdCode, String userId, Date oDate, int oTotalPrice, int count) {
      super();
      this.oNo = oNo;
      this.pdCode = pdCode;
      this.userId = userId;
      this.oDate = oDate;
      this.oTotalPrice = oTotalPrice;
      this.count = count;
   }

   public PdOrder(String pdCode, String userId, int oTotalPrice, int count) {
      super();
      this.pdCode = pdCode;
      this.userId = userId;
      this.oTotalPrice = oTotalPrice;
      this.count = count;
   }
   

   public PdOrder(String pdCode, String userId, int count) {
	super();
	this.pdCode = pdCode;
	this.userId = userId;
	this.count = count;
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

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public Date getoDate() {
      return oDate;
   }

   public void setoDate(Date oDate) {
      this.oDate = oDate;
   }

   public int getoTotalPrice() {
      return oTotalPrice;
   }

   public void setoTotalPrice(int oTotalPrice) {
      this.oTotalPrice = oTotalPrice;
   }

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }

   @Override
   public String toString() {
      return "PdOrder [oNo=" + oNo + ", pdCode=" + pdCode + ", userId=" + userId + ", oDate=" + oDate
            + ", oTotalPrice=" + oTotalPrice + ", count=" + count + "]";
   }
   
   
   
}