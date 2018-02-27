package cn.pengyi.service;

import java.util.List;

import cn.pengyi.domain.Book;
import cn.pengyi.domain.Cart;
import cn.pengyi.domain.Category;
import cn.pengyi.domain.Order;
import cn.pengyi.domain.Page;
import cn.pengyi.domain.Privilege;
import cn.pengyi.domain.User;

public interface BusinessService {

	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#addCategory(cn.pengyi.domain.Category)
	 */
	public abstract void addCategory(Category category);

	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	public abstract Category findCategory(String id);

	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#getAllCategory()
	 */
	public abstract List<Category> getAllCategory();

	/**
	 * 添加书
	 * @param book
	 */
	public abstract void addBook(Book book);

	/**
	 * 查找书
	 * @param id
	 * @return
	 */
	public abstract Book findBook(String id);

	/**
	 * 获取书的分页数据
	 * @param pagenum
	 * @return
	 */
	public abstract Page getBookPageData(String pagenum);

	/**
	 *获取分类图书数据
	 * @param pagenum
	 * @param category_id
	 * @return
	 */

	public abstract Page getBookPageData(String pagenum, String category_id);

	public abstract void buyBook(Cart cart, Book book);

	public abstract void registerUser(User user);

	public abstract User findUser(String id);

	public abstract User userLogin(String username, String password);

	public abstract void createOrder(Cart cart, User user);

	public abstract List<Order> listOrder(String state);

	public abstract Order findOrder(String orderid);

	public abstract void confirmOrder(String orderid);
	
	public List<Privilege> getUserPrivilege(String userid);

}