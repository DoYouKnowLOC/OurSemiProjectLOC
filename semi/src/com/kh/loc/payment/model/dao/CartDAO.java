package com.kh.loc.payment.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.kh.loc.payment.model.vo.Cart;
import com.kh.loc.product.model.vo.Product;

import static com.kh.loc.common.JDBCTemplate.*;

public class CartDAO {
	
	private Properties prop;
	
	public CartDAO() {
		prop = new Properties();
		
		String filePath = CartDAO.class
						 .getResource("/config/cart.properties")
						 .getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, Object> listCart(Connection con, String userId) {

		HashMap<String, Object> hmap = null;
		ArrayList<Product> list = null;
		Cart c = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCartList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			System.out.println("userId: " + userId);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Product>();
			System.out.println(rset.next() ? "yes" : "no");
			while(rset.next()) {
				c = new Cart();
				
				c.setCartNo(rset.getInt("cartno"));
				c.setPdCode(rset.getString("pdcode"));
				c.setUserId(rset.getString("userid"));
				c.setCount(rset.getInt("count"));
				System.out.println("c : " + c);
				
				Product p = new Product();
				
				p.setPdCode(rset.getString("pdcode"));
				p.setPdPrice(rset.getInt("pdprice"));
				p.setPdName(rset.getString("pdname"));
				p.setPdStatus(rset.getString("pdstatus"));
				p.setPdCont(rset.getString("pdcont"));
				p.setPdSubCont(rset.getString("pdsubcont"));
				p.setPdPreview(rset.getString("pdpreview"));
			
				
				System.out.println("p : " + p);
				
				list.add(p);
			}
			
			hmap = new HashMap<String, Object>();
			
			hmap.put("cart", c);
			hmap.put("product", list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hmap;
	}

	public int insertCart(Connection con, Cart cart) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCart");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cart.getPdCode());
			pstmt.setString(2, cart.getUserId());
			pstmt.setInt(3, cart.getCount());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int totalMoney(Connection con, String userId) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("totalMoney");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Cart> listCart2(Connection con, String userId) {
		
		ArrayList<Cart> list = new ArrayList<Cart>();
		Cart c = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCartList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				c = new Cart();
				
				c.setCartNo(rset.getInt("cartno"));
				c.setPdCode(rset.getString("pdcode"));
				c.setUserId(rset.getString("userid"));
				c.setCount(rset.getInt("count"));
				c.setTotalPrice(rset.getInt("TOTALPRICE"));
				
				c.setPdCode(rset.getString("pdcode"));
				c.setPdPrice(rset.getInt("pdprice"));
				c.setPdName(rset.getString("pdname"));
				c.setPdPreview(rset.getString("pdpreview"));
				c.setPdSubCont(rset.getString("pdsubcont"));
				
				list.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int deleteOneCart(Connection con, int cartno) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteOneCart");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, cartno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int cartCheck(Connection con, String userId, String pdCode) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("cartCheck");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, pdCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Integer> selectCartNoList(Connection con, String userId) {

		ArrayList<Integer> cartNoList = new ArrayList<Integer>();
		int cartNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCartnoList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cartNo = rset.getInt(1);
				cartNoList.add(cartNo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return cartNoList;
	}
	
}
