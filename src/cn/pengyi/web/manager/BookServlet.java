package cn.pengyi.web.manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.pengyi.domain.Book;
import cn.pengyi.domain.Page;
import cn.pengyi.service.BusinessService;
import cn.pengyi.service.impl.BusinessServiceImpl;
import cn.pengyi.uitls.WebUtils;

public class BookServlet extends HttpServlet {

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
		if(method.equals("addUI")){
			addUI(request,response);
		}else if(method.equals("add")){
			add(request,response);
		}else if(method.equals("list")){
			list(request,response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagenum = request.getParameter("pagenum");
		BusinessService service = new BusinessServiceImpl();
		Page page = service.getBookPageData(pagenum);
		request.setAttribute("page",page);
		request.getRequestDispatcher("/manager/listbook.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			Book book = doupload(request);
			BusinessService service = new BusinessServiceImpl();
			book.setId(WebUtils.makeID());
			service.addBook(book);
			request.setAttribute("message", "添加成功!");
			
		}catch (Exception e){
			request.setAttribute("message", "添加失败!");
		}
		
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	private Book doupload(HttpServletRequest request){
		
		//把上传的图片保存到images目录中,并把request中的请求参数封装到book中
		try{
			
			Book book = new Book();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
		
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(book, name, value);
				}else{
					String filename = item.getName();
					String savefilename = makeFileName(filename);//保存在硬盘的唯一文件名
					String savepath = this.getServletContext().getRealPath("/images");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len=in.read(buffer))>0){
						out.write(buffer,0,len);
					}
					
					in.close();
					out.close();
					item.delete();
					book.setImage(savefilename);
				}
		
			}
			return book;
		}catch (Exception e){
			throw new RuntimeException(e);
		}

	}

	public String makeFileName(String filename){
		String ext = filename.substring(filename.lastIndexOf("."));
		return UUID.randomUUID().toString()+ext;
	}
	
	private void addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		BusinessService service = new BusinessServiceImpl();
		List categorys = service.getAllCategory();
		request.setAttribute("categorys",categorys);
		request.getRequestDispatcher("/manager/addbook.jsp").forward(request, response);
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
