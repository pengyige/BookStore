package cn.pengyi.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.pengyi.dao.BookDao;
import cn.pengyi.domain.Book;
import cn.pengyi.uitls.JdbcUtils;

public class BookDaoImpl implements BookDao {
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.BookDao#add(cn.pengyi.domain.Book)
	 */
	@Override
	public void add(Book book){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into book(id,name,author,price,image,description,category_id) values(?,?,?,?,?,?,?)";
			Object params[] = {book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getImage(),book.getDescription(),book.getCategory_id()};
			runner.update(sql,params);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.BookDao#find(java.lang.String)
	 */
	@Override
	public Book find(String id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql  = "select * from book where id=?";
			return (Book)runner.query(sql, id,new BeanHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}

	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.BookDao#getAll()
	 */
	@Override
	public List<Book> getPageData(int startindex,int pagesize){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book limit ?,?";
			Object params[] = {startindex,pagesize};
			return (List<Book>)runner.query(sql, params,new BeanListHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	
	public int getTotalRecord(){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "select count(*) from book";
			long totalrecord = (Long)runner.query(sql,new ScalarHandler());
			return (int)totalrecord;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public List<Book> getPageData(int startindex,int pagesize,String category_id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where category_id=? limit ?,?";
			Object params[] = {category_id,startindex,pagesize};
			return (List<Book>)runner.query(sql, params,new BeanListHandler(Book.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	public int getTotalRecord(String category_id){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "select count(*) from book where category_id=?";
			long totalrecord = (Long)runner.query(sql,category_id,new ScalarHandler());
			
			return (int)totalrecord;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
}
