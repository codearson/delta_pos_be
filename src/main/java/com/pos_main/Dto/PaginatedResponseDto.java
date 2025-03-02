/**
 * 
 */
package com.pos_main.Dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaginatedResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pageNumber;
	private int pageSize;
	private int totalRecords;
	private Object payload;
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
