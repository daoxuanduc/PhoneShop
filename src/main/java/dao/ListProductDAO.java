package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;

public class ListProductDAO {
	public List<Product> search(String characters) throws Exception {
		String sql = "select * from products where product_name like '%"+characters+"%'";
		DBContext d = new DBContext();
		Connection conn = d.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Product> l = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("product_id"));
			p.setName(rs.getString("product_name"));
			p.setDescription(rs.getString("product_des"));
			p.setPrice(rs.getFloat("product_price"));
			p.setSrc(rs.getString("product_img_source"));
			p.setType(rs.getString("product_type"));
			p.setBrand(rs.getString("product_brand"));
			l.add(p);
		}

		rs.close();
		conn.close();
		return l;
	}

	public Product getProduct(String characters) throws Exception {
		String sql = "select * from products where product_id = "+characters;
		DBContext d = new DBContext();
		Connection conn = d.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Product> l = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("product_id"));
			p.setName(rs.getString("product_name"));
			p.setDescription(rs.getString("product_des"));
			p.setPrice(rs.getFloat("product_price"));
			p.setSrc(rs.getString("product_img_source"));
			p.setType(rs.getString("product_type"));
			p.setBrand(rs.getString("product_brand"));
			l.add(p);
			return p;
		}
		return null;
		
	}

	public List<Product> getAllProduct() throws Exception {
		String sql = "select * from products";
		DBContext d = new DBContext();
		Connection conn = d.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		List<Product> l = new ArrayList<Product>();
		while (rs.next()) {
			Product p = new Product();
			p.setId(rs.getInt("product_id"));
			p.setName(rs.getString("product_name"));
			p.setDescription(rs.getString("product_des"));
			p.setPrice(rs.getFloat("product_price"));
			p.setSrc(rs.getString("product_img_source"));
			p.setType(rs.getString("product_type"));
			p.setBrand(rs.getString("product_brand"));
			l.add(p);
		}

		rs.close();
		conn.close();
		return l;
	}
}
