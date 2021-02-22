package com.rugbyaholic.techshare.common.ui;

import java.util.List;

public class SearchResult<E> {
	
	private int pageFrom;
	
	private int pageTo;
	
	private int currentPage;
	
	private int recordPerPage;
	
	private int totalRecordCount;
	
	private int totalPageCount;
	
	private List<E> entities;
	
	public SearchResult(int totalRecordCount, int recordPerPage) {
		
		this.totalRecordCount = totalRecordCount;
		this.recordPerPage = recordPerPage;
		
		this.totalPageCount = (this.totalRecordCount / this.recordPerPage) + 
								(this.totalRecordCount % this.recordPerPage > 0 ? 1 : 0);
	}
	
	public void moveTo(int page) {
		
		this.currentPage = page;
		this.pageFrom = Math.max(page - 1, 2);
		this.pageTo = Math.min(this.pageFrom + 2, totalPageCount -1);
		this.pageFrom = Math.max(this.pageTo - 2, 2);
	}

	public int getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(int pageFrom) {
		this.pageFrom = pageFrom;
	}

	public int getPageTo() {
		return pageTo;
	}

	public void setPageTo(int pageTo) {
		this.pageTo = pageTo;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public List<E> getEntities() {
		return entities;
	}

	public void setEntities(List<E> entities) {
		this.entities = entities;
	}
}