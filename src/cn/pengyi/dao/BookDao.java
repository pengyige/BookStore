package cn.pengyi.dao;

import java.util.List;

import cn.pengyi.domain.Book;

public interface BookDao {

	public abstract void add(Book book);

	public abstract Book find(String id);

	public abstract List<Book> getPageData(int startindex,int pagesize);

	public int getTotalRecord();
	
	public List<Book> getPageData(int startindex,int pagesize,String category_id);
	
	public int getTotalRecord(String category_id);
	
	
}