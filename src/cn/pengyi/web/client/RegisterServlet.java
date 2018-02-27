package cn.pengyi.web.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pengyi.domain.User;
import cn.pengyi.service.BusinessService;
import cn.pengyi.service.impl.BusinessServiceImpl;
import cn.pengyi.uitls.WebUtils;

public class RegisterServlet extends HttpServlet {

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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String cellphone =request.getParameter("cellphone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		
		
		User user = new User();
		user.setAddress(address);
		user.setCellphone(cellphone);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setId(WebUtils.makeID());
		
		BusinessService service = new BusinessServiceImpl();
		service.registerUser(user);
		request.setAttribute("message", "注册成功!");
		}catch (Exception e){
			e.printStackTrace();
			request.setAttribute("message","注册失败!");
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
