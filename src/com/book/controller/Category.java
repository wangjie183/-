package com.book.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.BookCategory;
import com.book.service.CategoryService;
import com.book.service.CategoryServiceImpl;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategoryService cg=new CategoryServiceImpl(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if ("show".equals(op)) {
			showInfo(request,response);
		}else if("add".equals(op)) {
			addBook(request,response);
		}else if("delete".equals(op)) {
			deletecategory(request,response);
		}
	}

	private void deletecategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 设置字符编码
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("id"));
			//调用删除的方法
			boolean isOk = cg.deletecategory(id);
			if(isOk) {
				response.sendRedirect("admin/book-mgr.jsp");
			}else {
				response.sendRedirect("admin/book-mgr.jsp");
				}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addBook(HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置字符编码格式
			request.setCharacterEncoding("UTF-8");
			//获取数据
			String category = request.getParameter("category");
			BookCategory ca = new BookCategory();
			boolean isOk = cg.saveCategory(category);
			if(isOk) {
				try {
					response.sendRedirect("admin/book-mgr.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				response.sendRedirect("admin/book-mgr.jsp");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showInfo(HttpServletRequest request, HttpServletResponse response) {
		List<BookCategory> list = cg.getCategoryList();
		request.getSession().setAttribute("list", list);
		try {
			response.sendRedirect("admin/category-mgr.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
