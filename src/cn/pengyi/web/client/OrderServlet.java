package cn.pengyi.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pengyi.domain.Cart;
import cn.pengyi.domain.User;
import cn.pengyi.service.BusinessService;
import cn.pengyi.service.impl.BusinessServiceImpl;

public class OrderServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
		User user = (User)request.getSession().getAttribute("user");
		if(user == null){
			request.setAttribute("message", "对不起,请登入");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		
		//得到用户的购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		BusinessService service = new BusinessServiceImpl();
		service.createOrder(cart, user);
		request.setAttribute("message","订单已生成~" );
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("message", "订单生成失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
