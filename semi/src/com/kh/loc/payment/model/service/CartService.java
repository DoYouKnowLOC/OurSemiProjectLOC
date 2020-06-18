package com.kh.loc.payment.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.loc.payment.model.dao.CartDAO;
import com.kh.loc.payment.model.vo.Cart;

import static com.kh.loc.common.JDBCTemplate.*;

public class CartService {

	private Connection con;
	private CartDAO cDAO = new CartDAO();
	
	public HashMap<String, Object> listCart(String userId) {

		con = getConnection();
		HashMap<String, Object> hmap= cDAO.listCart(con, userId);
		System.out.println("hmap : " + hmap);
		close(con);
		
		return hmap;
	}

	public int totalMoney(String userId) {
		
		con = getConnection();
		int result = cDAO.totalMoney(con, userId);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		return result;
	}

	public int insertCart(Cart cart) {
		
		con = getConnection();
		int result = cDAO.insertCart(con, cart);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<Cart> listCart2(String userId) {
		con = getConnection();
		
		ArrayList<Cart> list= cDAO.listCart2(con, userId);
		close(con);
		
		return list;
	}

	public int deleteOneCart(int cartno) {
		
		con = getConnection();
		int result = cDAO.deleteOneCart(con, cartno);
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int cartCheck(String userId, String pdCode) {
		
		con = getConnection();
		int result = cDAO.cartCheck(con, userId, pdCode);
		
		close(con);
		
		return result;
	}

	public int deleteCart(String userId) {
		
		con = getConnection();
		int result = 0;
		ArrayList<Integer> cartNoList = cDAO.selectCartNoList(con, userId);
		
		if(!cartNoList.isEmpty()) {
			for(Integer cartNo : cartNoList) {
				result = cDAO.deleteOneCart(con, cartNo);
			}
			
		}
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}
}
