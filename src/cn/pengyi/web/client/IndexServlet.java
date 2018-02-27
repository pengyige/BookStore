package cn.pengyi.web.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.pengyi.domain.Page;
import cn.pengyi.service.BusinessService;
import cn.pengyi.service.impl.BusinessServiceImpl;

public class IndexServlet extends HttpServlet {

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
		
		if(method.equals("getAll")){
		getAll(request, response);
		}else if(method.equals("listBookWithCategory")){
			listBookWithCategory(request,response);
		}
	}

	private void listBookWithCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String category_id = request.getParameter("category_id");
		String pagenum = request.getParameter("pagenum");
		BusinessService service  = new BusinessServiceImpl();
		Page page = service.getBookPageData(pagenum, category_id);
		
		List categorys = service.getAllCategory();
		request.setAttribute("categorys", categorys);
		
		request.setAttribute("page",page);
		
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
		
	}

	public void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BusinessService service = new BusinessServiceImpl();
		List categorys = service.getAllCategory();
		request.setAttribute("categorys", categorys);
		
		String pagenum = request.getParameter("pagenum");
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page",page);
		
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
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
