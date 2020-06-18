package com.kh.loc.payment.model.dao;


import static com.kh.loc.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.loc.payment.model.vo.Delivery;
import com.kh.loc.payment.model.vo.Payment;
import com.kh.loc.payment.model.vo.PdOrder;

public class PaymentDAO {
	
	private Properties prop;
	
		public PaymentDAO() {	
			
			prop = new Properties();     
			
			String filePath = PaymentDAO.class
					
                    .getResource("/config/payment.properties")
                    
                    .getPath(); 
			try {
				
				prop.load(new FileReader(filePath));    
				
			} catch (IOException e) {    
				
				e.printStackTrace();
			}
		}

		public int insertOrder(Connection con, PdOrder o) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertOrder");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, o.getPdCode());
				pstmt.setString(2, o.getUserId());
				pstmt.setInt(3, o.getCount());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {				
				e.printStackTrace();
				
			} finally {
				close(pstmt);			
			}
			return result;
		}

		public int insertDelivery(Connection con, Delivery d, PdOrder o) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertDelivery");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, o.getPdCode());
				pstmt.setInt(2, o.getoNo());
				pstmt.setString(3, d.getdAddress());
				pstmt.setString(4, d.getdPhone());
				pstmt.setString(5, d.getdName());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {				
				e.printStackTrace();
				
			} finally {
				close(pstmt);			
			}
			return result;
		}

		public int getCurrentOno(Connection con) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("currentOno");
			
			try {
				pstmt = con.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					result = rset.getInt(1);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return result;
		}

		public int insertPayment(Connection con, PdOrder o) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertPayment");
//			INSERT INTO PAYMENT VALUES(SEQ_PAYNO.NEXTVAL, ?, ?)
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, o.getoNo());
				pstmt.setString(2, o.getPdCode());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		public int insertOtherOrder(Connection con, PdOrder o) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertListOrder");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, o.getoNo());
				pstmt.setString(2, o.getPdCode());
				pstmt.setString(3, o.getUserId());
				pstmt.setInt(4, o.getCount());
				
				System.out.println("insertother ono : " + o.getoNo());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {				
				e.printStackTrace();
				
			} finally {
				close(pstmt);			
			}
			return result;
		}

		public ArrayList<Payment> paymentDelList(Connection con, String userId) {
			
			ArrayList<Payment> list = new ArrayList<Payment>();
			Payment p = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("paymentDeliveryList");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, userId);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					p = new Payment();
					
					p.setPayNo(rset.getInt("payno"));
					p.setOno(rset.getInt("ono"));
					p.setPdCode(rset.getString("pdcode"));
					p.setPdName(rset.getString("pdname"));
					p.setCount(rset.getInt("count"));
					p.setPdPrice(rset.getInt("pdprice"));
					p.setPdPreview(rset.getString("pdpreview"));
					p.setPdSubCont(rset.getString("pdsubcont"));
					p.setdStatus(rset.getString("dstatus"));
					
					list.add(p);
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			
			return list;
		}

		public int paymentCancel(Connection con, int payno) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("paymentCancel");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, payno);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			
			return result;
		}

		public ArrayList<Payment> adminPaymentList(Connection con) {
			
			ArrayList<Payment> list = new ArrayList<Payment>();
			Payment p = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("adminPaymentList");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					p = new Payment();
					
					p.setPayNo(rset.getInt("payno"));
					p.setOno(rset.getInt("ono"));
					p.setPdCode(rset.getString("pdcode"));
					p.setPdName(rset.getString("pdname"));
					p.setCount(rset.getInt("count"));
					p.setPdPrice(rset.getInt("pdprice"));
					p.setPdSubCont(rset.getString("pdsubcont"));
					p.setdName(rset.getString("dname"));
					p.setdPhone(rset.getString("dphone"));
					p.setdAddress(rset.getString("daddress"));
					p.setdStatus(rset.getString("dstatus"));
					
					list.add(p);
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			
			return list;
		}

		public int adminPaymentCheck(Connection con, int payno) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("adminPaymentUpdate");
			
			try {
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, payno);
				
				result = pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		
}









































