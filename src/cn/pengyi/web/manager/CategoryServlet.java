package cn.pengyi.web.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pengyi.domain.Category;
import cn.pengyi.domain.User;
import cn.pengyi.service.BusinessService;
import cn.pengyi.service.impl.BusinessServiceImpl;
import cn.pengyi.uitls.PrivilegeException;
import cn.pengyi.uitls.ServiceFactory;
import cn.pengyi.uitls.WebUtils;

public class CategoryServlet extends HttpServlet {

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

		String method = request.getParameter("method");
		if(method.equals("add")){
			add(request,response);
		}else if(method.equals("delete")){
			delete(request,response);
		}else if(method.equals("find")){
			find(request,response);
		}else if(method.equals("listall")){
			listAll(request,response);
		}else{
			request.setAttribute("message","不支持此类操作");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		user.setId("1");
		BusinessService service = ServiceFactory.getIntance().createService("cn.pengyi.service.impl.BusinessServiceImpl",BusinessService.class,user);
		List<Category> categorys = service.getAllCategory();
		request.setAttribute("categorys", categorys);
		request.getRequestDispatcher("/manager/listcategory.jsp").forward(request, response);
		
	}

	private void find(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		Category category = new Category();
		category.setName(name);
		category.setDescription(description);
		category.setId(WebUtils.makeID());
		
		BusinessServiceImpl service = new BusinessServiceImpl() ;
		service.addCategory(category);
		
		request.setAttribute("message", "添加成功!");
		}catch (Exception e){
			if(e.getCause() instanceof PrivilegeException){
				e.printStackTrace();
			}else{
			e.printStackTrace();
			request.setAttribute("message","添加失败!");
			}
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
