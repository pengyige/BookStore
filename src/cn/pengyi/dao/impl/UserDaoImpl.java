package cn.pengyi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.pengyi.dao.UserDao;
import cn.pengyi.domain.Privilege;
import cn.pengyi.domain.User;
import cn.pengyi.uitls.JdbcUtils;

public class UserDaoImpl implements UserDao {
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.UserDao#add(cn.pengyi.domain.User)
	 */
	@Override
	public void add(User user){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql =  "insert into user(id,username,password,phone,cellphone,address,email) values(?,?,?,?,?,?,?)";
			Object params[] = {user.getId(),user.getUsername(),user.getPassword(),user.getPhone(),user.getCellphone(),user.getAddress(),user.getEmail()};
			runner.update(sql, params);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String id){
		try{
	
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			return (User)runner.query(sql, id,new BeanHandler(User.class));
			
		}catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username,String password){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object params[] = {username,password};
			return (User)runner.query(sql, params,new BeanHandler(User.class));
		}catch (Exception e){
			throw new RuntimeException(e);
		}

	}
	
	
	public List<Privilege> getPrivilege(String userid){
		try{
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user_privilege up,privilege p where user_id=? and p.id=up.privilege_id";
			return (List<Privilege>)runner.query(sql, userid,new BeanListHandler(Privilege.class));
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
