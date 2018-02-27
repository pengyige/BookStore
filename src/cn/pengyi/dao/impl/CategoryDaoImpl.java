package cn.pengyi.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.pengyi.dao.CategoryDao;
import cn.pengyi.domain.Category;
import cn.pengyi.uitls.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.CategoryDao#add(cn.pengyi.domain.Category)
	 */
	@Override
	public void add(Category category){
		
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object params[] = {category.getId(),category.getName(),category.getDescription()};
			runner.update(sql,params);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id){
		try{	
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";
			return (Category)runner.query(sql, id,new BeanHandler(Category.class));	
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll(){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category";
			return (List<Category>)runner.query(sql, new BeanListHandler(Category.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
}
