package com.kh.loc.product.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.loc.product.model.dao.ProductDAO;
import com.kh.loc.product.model.vo.Product;

import static com.kh.loc.common.JDBCTemplate.*;

public class ProductService {

	private Connection con;
	private ProductDAO pdDAO = new ProductDAO();
	
	public ArrayList<Product> selectList() {
		
		con = getConnection();
		
		ArrayList<Product> pdList = pdDAO.selectList(con);
		
		close(con);
		
		return pdList;
	}

	public int insertProduct(Product p) {

		con = getConnection();
		int result = pdDAO.insertProduct(con, p);
		if(result > 0){
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		return result;
	}

	public Product selectOne(String pdcode) {
		
		con = getConnection();
		Product p = pdDAO.selectOne(con, pdcode);
		
		close(con);
		
		return p;
	}

	public int deleteProduct(String pdcode) {
		
		con = getConnection();
		int result = pdDAO.deleteProduct(con, pdcode);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

	public int updateProduct(Product p) {
		
		con = getConnection();
		int result = pdDAO.updateProduct(con, p);
		
		if(result > 0) commit(con);
		else rollback(con);
		
		close(con);
		
		return result;
	}

}
