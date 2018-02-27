package cn.pengyi.dao;

import java.util.List;

import cn.pengyi.domain.Privilege;
import cn.pengyi.domain.User;

public interface UserDao {

	public abstract void add(User user);

	public abstract User find(String id);

	public abstract User find(String username, String password);
	
	public List<Privilege> getPrivilege(String userid);

}