package com.book.service;

import java.util.List;

import com.book.pojo.BookCategory;

public interface CategoryService {
	//查找数据
	List<BookCategory> getCategoryList();
	//添加数据
	boolean saveCategory(BookCategory category);
}
