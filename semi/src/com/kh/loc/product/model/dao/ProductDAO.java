package com.kh.loc.product.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.loc.product.model.vo.Product;
import static com.kh.loc.common.JDBCTemplate.*;

public class ProductDAO {

	private Properties prop;
	
	public ProductDAO() {
		prop = new Properties();
		
		String filePath = ProductDAO.class
						 .getResource("/config/product.properties")
						 .getPath();
		
		try {
			prop.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Product> selectList(Connection con) {
		
		ArrayList<Product> pdList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectList");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			pdList = new ArrayList<Product>();
			
			while(rset.next()) {
				Product p = new Product();
				
				p.setPdCode(rset.getString("pdcode"));
				p.setPdPrice(rset.getInt("pdprice"));
				p.setPdName(rset.getString("pdname"));
				p.setPdStatus(rset.getString("pdstatus"));
				p.setPdCont(rset.getString("pdcont"));
				p.setPdSubCont(rset.getString("pdsubcont"));
				p.setPdPreview(rset.getString("pdpreview"));
				
				
				pdList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return pdList;
	}

	public int insertProduct(Connection con, Product p) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p.getPdPrice());
			pstmt.setString(2, p.getPdName());
			pstmt.setString(3, p.getPdCont());
			pstmt.setString(4, p.getPdSubCont());
			pstmt.setString(5, p.getPdPreview());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Product selectOne(Connection con, String pdcode) {
		
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, pdcode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				p = new Product();
				
				p.setPdCode(rset.getString("pdcode"));
				p.setPdPrice(rset.getInt("pdprice"));
				p.setPdName(rset.getString("pdname"));
				p.setPdStatus(rset.getString("pdstatus"));
				p.setPdCont(rset.getString("pdcont"));
				p.setPdSubCont(rset.getString("pdsubcont"));
				p.setPdPreview(rset.getString("pdpreview"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return p;
	}

	public int deleteProduct(Connection con, String pdcode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteProduct");
		
		try {
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, pdcode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateProduct(Connection con, Product p) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProduct");
		
		try {
			
			pstmt = con.prepareStatement(sql);
//			int pdPrice, String pdName, String pdCont, 
//			String pdSubCont, String pdPreview
			pstmt.setInt(1, p.getPdPrice());
			pstmt.setString(2, p.getPdName());
			pstmt.setString(3, p.getPdCont());
			pstmt.setString(4, p.getPdSubCont());
			pstmt.setString(5, p.getPdCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
}













