package cn.pengyi.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.pengyi.dao.OrderDao;
import cn.pengyi.domain.Book;
import cn.pengyi.domain.Order;
import cn.pengyi.domain.OrderItem;
import cn.pengyi.domain.User;
import cn.pengyi.uitls.JdbcUtils;

public class OrderDaoImpl implements OrderDao {
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.OrderDao#add(cn.pengyi.domain.Order)
	 */
	@Override
	public void add(Order order){
		try{
			//将订单信息添加到订单表
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into orders(id,ordertime,price,state,user_id) values(?,?,?,?,?)";
			Object params[] ={order.getId(),order.getOrdertime(),order.getPrice(),order.isState(),order.getUser().getId()};
			runner.update(sql,params);
			
			//将订单信息中维护的订单项信息添加到订单项表
			Set<OrderItem> set = order.getOrderitem();
			for(OrderItem item : set){
				sql = "insert into orderitem(id,quantity,price,book_id,order_id) values(?,?,?,?,?)";
				params = new Object[]{item.getId(),item.getQuantity(),item.getPrice(),item.getBook().getId(),order.getId()};
				runner.update(sql,params);
			}
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.OrderDao#find(java.lang.String)
	 */
	@Override
	public Order find(String id){
		try{	
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			//1.找出订单的基本信息
			String sql = "select * from orders where id=?";
			Order order = (Order)runner.query(sql, id,new BeanHandler(Order.class));
		
			//2.找出订单中所有订单项
			sql = "select * from orderitem where order_id =?";
			List<OrderItem> list = (List<OrderItem>)runner.query(sql, id, new BeanListHandler(OrderItem.class));
			for(OrderItem item : list){
				sql = "select * from orderitem,book where orderitem.id=? and orderitem.book_id=book.id";
				Book book = runner.query(sql, item.getId(),new BeanHandler(Book.class));
				item.setBook(book);
			}
			
			//3.订单属于哪个用户
			sql = "select user.* from orders,user where orders.id=? and orders.user_id = user.id";
			User user = (User) runner.query(sql,order.getId(),new BeanHandler(User.class));
			order.setUser(user);
			return order;
		}
		catch (Exception e){
		throw new RuntimeException(e);
		
		
		
		}
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.OrderDao#getAll(boolean)
	 */
	@Override
	public List<Order> getAll(boolean state){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql ="select * from orders where state=?";
			List<Order> list = (List<Order>) runner.query(sql, state,new BeanListHandler(Order.class));
			for(Order order : list){
				
				//找到当前订单中的用户信息
				sql = "select user.* from orders,user where orders.id=? and orders.user_id=user.id";
				User user = (User)runner.query(sql, order.getId(),new BeanHandler(User.class));
				order.setUser(user);
			}
			return list;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.OrderDao#update(cn.pengyi.domain.Order)
	 */
	@Override
	public void update(Order order){
		try{			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update orders set state=? where id=?";
			Object params[] = {order.isState(),order.getId()};
			runner.update(sql,params);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
