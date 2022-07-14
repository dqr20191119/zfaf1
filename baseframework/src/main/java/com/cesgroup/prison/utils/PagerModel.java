package com.cesgroup.prison.utils;

import java.io.Serializable;

public class PagerModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private int total;
	
	private int totalPages;

	private int pageNumber;
	
	private Object rows;
	
	public PagerModel() {
		
	}
	
	public PagerModel(int total, Object rows, int page, int number) {
		this.total = total;
		this.rows = rows;
		this.pageNumber = page;
		this.totalPages = (total % number == 0) ? (total / number) : (total / number + 1);
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

}
