package com.k.shiro.common.model;

/**
 * @author kuta
 * 分页父类
 */
public class PageSetting {
	
	private int pageNumber=1;
	private int displayCount=100;
	private Integer totalRecordNumber;
	private Integer totalPageNumber;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getDisplayCount() {
		return displayCount;
	}

	public void setDisplayCount(int displayCount) {
		this.displayCount = displayCount;
	}

	public Integer getTotalRecordNumber() {
		return totalRecordNumber;
	}

	public void setTotalRecordNumber(Integer totalRecordNumber) {
		this.totalRecordNumber = totalRecordNumber;
	}

	public Integer getTotalPageNumber() {
		return totalPageNumber;
	}

	public void setTotalPageNumber(Integer totalPageNumber) {
		this.totalPageNumber = totalPageNumber;
	}

}
