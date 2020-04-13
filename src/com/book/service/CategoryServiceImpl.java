package com.book.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MybatisUtils;
import com.book.dao.category.CategoryMapper;
import com.book.pojo.BookCategory;

public class CategoryServiceImpl implements CategoryService {
	private SqlSession sqlSession;
	@Override
	public List<BookCategory> getCategoryList() {
		sqlSession=MybatisUtils.createSqlSession();
		List<BookCategory> list=(List<BookCategory>) sqlSession.getMapper(CategoryMapper.class).getCategoryList();
		MybatisUtils.closeSqlSession(sqlSession);
		return list;
	}

	@Override
	public boolean saveCategory(BookCategory category) {
		sqlSession=MybatisUtils.createSqlSession();
		CategoryMapper mapper=sqlSession.getMapper(CategoryMapper.class);
		int result =mapper.saveCategory(category);
		if(result>0) {
			sqlSession.commit();
			MybatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			MybatisUtils.closeSqlSession(sqlSession);
			return false;
		}
	}

}
