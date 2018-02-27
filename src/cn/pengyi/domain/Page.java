package cn.pengyi.domain;

import java.util.List;

public class Page {
	
	private int totalpage;  //总页数*
	private int pagesize = 3;// 一页显示的数据条数
	
	private int totalrecord;//总记录数
	private int pagenum; //当前页*
	
	private List list;//页面数据*
	
	private int startPage;//开始页码*
	private int endPage;//结束页码*
	
	private int startindex;//想看的页数据在数据库的第几条
	
	public Page(int pagenum,int totalrecord){
		this.pagenum = pagenum;
		this.totalrecord = totalrecord;
		//1.根据总记录数，算出总页数--决定开始页码，起始页码
		this.totalpage = (this.totalrecord+this.pagesize-1)/this.pagesize;
		//2.根据想看的页，算出在数据库中第几条--决定页面数据
		this.startindex = (this.pagenum-1)*this.pagesize;
		
		if(this.totalpage <= 10){
			this.startPage = 1;
			this.endPage = this.totalpage;
		}else{
			this.startPage = pagenum - 4;
			this.endPage = pagenum + 5;
			
			if(this.startPage < 1){
				this.startPage = 1;
				this.endPage = 10;
			}
			
			
			if(this.endPage>this.totalpage){
				this.endPage = this.totalpage;
				this.startPage = this.totalpage-9;
			}
		}
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartindex() {
		return startindex;
	}

	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	
	
	
}
