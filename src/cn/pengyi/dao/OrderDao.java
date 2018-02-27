package cn.pengyi.dao;

import java.util.List;

import cn.pengyi.domain.Order;

public interface OrderDao {

	public abstract void add(Order order);

	public abstract Order find(String id);

	public abstract List<Order> getAll(boolean state);

	public abstract void update(Order order);

}