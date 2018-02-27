package cn.pengyi.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.pengyi.dao.BookDao;
import cn.pengyi.dao.CategoryDao;
import cn.pengyi.dao.OrderDao;
import cn.pengyi.dao.UserDao;
import cn.pengyi.domain.Book;
import cn.pengyi.domain.Cart;
import cn.pengyi.domain.CartItem;
import cn.pengyi.domain.Category;
import cn.pengyi.domain.Order;
import cn.pengyi.domain.OrderItem;
import cn.pengyi.domain.Page;
import cn.pengyi.domain.Privilege;
import cn.pengyi.domain.User;
import cn.pengyi.service.BusinessService;
import cn.pengyi.uitls.DaoFactory;
import cn.pengyi.uitls.WebUtils;
import cn.pengyi.utils.Permisson;

public class BusinessServiceImpl implements BusinessService {
	
	private CategoryDao dao = DaoFactory.getInstance().createDao("cn.pengyi.dao.impl.CategoryDaoImpl",CategoryDao.class);
	private BookDao bookDao = DaoFactory.getInstance().createDao("cn.pengyi.dao.impl.BookDaoImpl", BookDao.class);
	private UserDao userDao = DaoFactory.getInstance().createDao("cn.pengyi.dao.impl.UserDaoImpl", UserDao.class);
	private OrderDao orderDao = DaoFactory.getInstance().createDao("cn.pengyi.dao.impl.OrderDaoImpl",OrderDao.class);
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#addCategory(cn.pengyi.domain.Category)
	 */
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#addCategory(cn.pengyi.domain.Category)
	 */
	@Override

	public void addCategory(Category category){
		dao.add(category);
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findCategory(java.lang.String)
	 */
	@Override

	public Category findCategory(String id){
		return dao.find(id);
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#getAllCategory()
	 */
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#getAllCategory()
	 */
	@Override

	@Permisson("查看所有分类")
	public List<Category> getAllCategory(){
		return dao.getAll();
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#addBook(cn.pengyi.domain.Book)
	 */
	@Override
	public void addBook(Book book){
		bookDao.add(book);
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findBook(java.lang.String)
	 */
	@Override
	public Book findBook(String id){
		return bookDao.find(id);
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#getBookPageData(java.lang.String)
	 */
	@Override
	public Page getBookPageData(String pagenum){
		
		int totalrecord  = bookDao.getTotalRecord();
		Page page = null;
		if(pagenum == null){
			page = new Page(1,totalrecord);
		}else{
			page = new Page(Integer.parseInt(pagenum),totalrecord);
		}
		
		//得到页面数据
		List list = bookDao.getPageData(page.getStartindex(),page.getPagesize());
		page.setList(list);
		
		return page;
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#getBookPageData(java.lang.String, java.lang.String)
	 */
	
	@Override
	public Page getBookPageData(String pagenum,String category_id){
		
		int totalrecord  = bookDao.getTotalRecord(category_id);
		Page page = null;
		if(pagenum == null){
			page = new Page(1,totalrecord);
		}else{
			page = new Page(Integer.parseInt(pagenum),totalrecord);
		}
		
		//得到页面数据
		List list = bookDao.getPageData(page.getStartindex(),page.getPagesize(),category_id);
		page.setList(list);
		
		return page;
	}


	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#buyBook(cn.pengyi.domain.Cart, cn.pengyi.domain.Book)
	 */
	@Override
	public void buyBook(Cart cart, Book book) {
		// TODO Auto-generated method stub
		cart.add(book);
	}


	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#registerUser(cn.pengyi.domain.User)
	 */
	@Override
	public void registerUser(User user) {
	
		userDao.add(user);
		
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String id){
		return userDao.find(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#userLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User userLogin(String username,String password){
		return userDao.find(username, password);
	}
	
	
	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#createOrder(cn.pengyi.domain.Cart, cn.pengyi.domain.User)
	 */
	@Override
	public void createOrder(Cart cart,User user){
		if(cart == null){
			throw new RuntimeException("对不起，您还没有购买");
		}
		
		//完成订单实体
		Order order = new Order();
		order.setId(WebUtils.makeID());
		order.setOrdertime(new Date());
		order.setPrice(cart.getPrice());
		order.setState(false);
		order.setUser(user);
		
		//完成订单项实体
		for(Map.Entry<String, CartItem> me : cart.getCart().entrySet()){
			//得到一个购物项就生成一个订单项
			CartItem citem = me.getValue();
			OrderItem oitem = new OrderItem();
			oitem.setBook(citem.getBook());
			oitem.setId(WebUtils.makeID());
			oitem.setPrice(citem.getPrice());
			oitem.setQuantity(citem.getQuantity());
			
			//将得到订单项加入订单
			order.getOrderitem().add(oitem);
			
			
		}
		
		orderDao.add(order);
		
	}


	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#listOrder(java.lang.String)
	 */
	@Override
	public List<Order> listOrder(String state) {
		
		return orderDao.getAll(Boolean.parseBoolean(state));
		
	}


	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#findOrder(java.lang.String)
	 */
	@Override
	public Order findOrder(String orderid) {
		// TODO Auto-generated method stub
		return	(Order)orderDao.find(orderid);
	}


	/* (non-Javadoc)
	 * @see cn.pengyi.service.impl.BusinessService#confirmOrder(java.lang.String)
	 */
	@Override
	public void confirmOrder(String orderid) {
	
		Order order = orderDao.find(orderid);
		order.setState(true);
		orderDao.update(order);
		
	}
	
	public List<Privilege> getUserPrivilege(String userid){
		return userDao.getPrivilege(userid);
	}
}
