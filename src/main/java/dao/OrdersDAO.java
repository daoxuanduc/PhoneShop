package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import context.DBContext;
import model.Cart;
import model.Orders;


public class OrdersDAO {
	public void insertOrder(Orders o, Cart c) throws Exception {
		String sql = "insert into orders (user_mail, order_status, order_date, order_discount_code, order_address) "
				+ "values (?, ?, GETDATE(), ?, ?);";
		for (int i=0;i<c.getItems().size();i++) {
			sql+="insert into orders_detail (order_id,product_id, amount_product, price_product) "
					+ "values ((select max(order_id) from orders), ?, ?, ?);";
		}
		//String sql = "insert into orders (user_mail, order_status, order_date, order_discount_code, order_address) "
				//+ "values ('"+o.getUserMail()+"','"+o.getStatus()+"',GETDATE(),'"+o.getDiscount()+"','"+o.getAddress()+"')";
		DBContext d = new DBContext();
		Connection conn = d.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		stmt.setString(1,o.getUserMail());
		stmt.setString(2,o.getStatus()+"");
		stmt.setString(3,o.getDiscount());
		stmt.setString(4,o.getAddress());
		for (int i=0;i<c.getItems().size();i++) {
			stmt.setString(4+3*i+1,c.getItems().get(i).getId()+"");
			stmt.setString(4+3*i+2,c.getItems().get(i).getNumber()+"");
			stmt.setString(4+3*i+3,c.getItems().get(i).getPrice()+"");
		}
		
		stmt.executeUpdate();
		
		conn.close();
	}
	
}
