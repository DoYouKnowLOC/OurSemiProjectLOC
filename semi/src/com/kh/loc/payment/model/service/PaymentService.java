package com.kh.loc.payment.model.service;

import static com.kh.loc.common.JDBCTemplate.commit;
import static com.kh.loc.common.JDBCTemplate.rollback;
import static com.kh.loc.common.JDBCTemplate.close;
import static com.kh.loc.common.JDBCTemplate.commit;
import static com.kh.loc.common.JDBCTemplate.getConnection;
import static com.kh.loc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.loc.payment.model.dao.PaymentDAO;
import com.kh.loc.payment.model.vo.Cart;
import com.kh.loc.payment.model.vo.Delivery;
import com.kh.loc.payment.model.vo.Payment;
import com.kh.loc.payment.model.vo.PdOrder;

public class PaymentService {
	
	private Connection con;
	private PaymentDAO oDAO = new PaymentDAO();
	
		public int insertOrder(PdOrder o, Delivery d) {
					
			con = getConnection();
			
			int result = 0;
			
			int result1 = oDAO.insertOrder(con, o);
		
	        if(result1 > 0) {
	            int ono = oDAO.getCurrentOno(con);
	             
	            o.setoNo(ono);
	        }
			
	        int result2 = oDAO.insertDelivery(con, d, o);
	        
	        int result3 = oDAO.insertPayment(con, o);
	        
	        if(result1 > 0 && result2 > 0 && result3 > 0) {
	        	 
	        	commit(con);
	        	result = 1;
	        	 
	         } else {
	        	 rollback(con);
	         }
	        return result;
		}

		public int insertOtherOrder(PdOrder o) {
			
			con = getConnection();
			
			int ono = oDAO.getCurrentOno(con);
			if(ono > 0) {
				o.setoNo(ono);
			}
			
			int result = oDAO.insertOtherOrder(con, o);

			if(result > 0) commit(con);
	        else rollback(con);
	        
	        return result;
		}

		public int insertTestOrder(ArrayList<PdOrder> poList, Delivery d, int listCount) {
			
			con = getConnection();
			
			int result = 0;
			int result1 = 0;
			int result2 = 0;
			int result3 = 0;
			for(int i = 0; i < poList.size(); i++) {
				PdOrder o = new PdOrder(poList.get(i).getPdCode()
									  , poList.get(i).getUserId()
									  , poList.get(i).getoTotalPrice()
									  , poList.get(i).getCount());
				
				System.out.println("pdCode : " + i + "번쨰 : " + o.getPdCode());
				
				if(listCount == 1) {
					
					result1 = oDAO.insertOrder(con, o);
					
			        if(result1 > 0) {
			            int ono = oDAO.getCurrentOno(con);
			             
			            o.setoNo(ono);
			        }
					
			        result2 = oDAO.insertDelivery(con, d, o);
			        
			        result3 = oDAO.insertPayment(con, o);
			        System.out.println("첫번째 포문");
				} else {
					if(i == 0) {
						
						result1 = oDAO.insertOrder(con, o);
						
				        if(result1 > 0) {
				            int ono = oDAO.getCurrentOno(con);
				             
				            o.setoNo(ono);
				        }
				        result2 = oDAO.insertDelivery(con, d, o);
				        
				        result3 = oDAO.insertPayment(con, o);
				        System.out.println("두번째 포문");
					} else {
						
						int ono = oDAO.getCurrentOno(con);
						if(ono > 0) {
							o.setoNo(ono);
						}
						
						System.out.println("세번째 포문");
			            result1 = oDAO.insertOtherOrder(con, o);
			            result3 = oDAO.insertPayment(con, o);
					}
				}
			}
			if(result1 > 0 && result2 > 0 && result3 > 0) {
				
				commit(con);
				result = 1;
				
			} else {
				rollback(con);
			}
			
			close(con);
			
			return result;
			
		}

		public ArrayList<Payment> paymentDelList(String userId) {
			
			con = getConnection();
			
			ArrayList<Payment> list = oDAO.paymentDelList(con, userId);
			
			close(con);
			
			return list;
		}

		public int paymentCancel(int payno) {
			
			con = getConnection();
			int result = oDAO.paymentCancel(con, payno);
			
			if(result > 0) commit(con);
			else rollback(con);
			
			close(con);
			
			return result;
		}

		public ArrayList<Payment> adminPaymentList() {
			
			con = getConnection();
			
			ArrayList<Payment> list = oDAO.adminPaymentList(con);
			
			close(con);
			
			return list;
		}

		public int paymentCheck(int payno) {
			
			con = getConnection();
			
			int result = oDAO.adminPaymentCheck(con, payno);
			
			if(result > 0) commit(con);
			else rollback(con);
			
			close(con);
			
			return result;
		}

	
}
