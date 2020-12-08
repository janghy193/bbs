package com.tjoeun.prj.pagination;

public class Pagination {
	private int pageNum;
	private int pageSize;
	private int size;
	private int startRow;
	private int endRow;
	private int total;
	private int pages;
	// private List<> list;
	private int prePage;
	private int nextPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;	
	private int navigatePages;
	private int navigateFirstPage;
	private int navigateLastPage;
	private int[] navigatepageNums;
	
//	pageNum=1, 
//	pageSize=12, 
//	size=12, 
//	startRow=1, 
//	endRow=12, 
//	total=37, 
//	pages=4, 
//	list=Page{count=true, pageNum=1, pageSize=12, startRow=0, 
//	endRow=12, total=37, pages=4, reasonable=true, pageSizeZero=false}, 
//	prePage=0, nextPage=2, isFirstPage=true, 
//	isLastPage=false, hasPreviousPage=false, 
//	hasNextPage=true, navigatePages=8, navigateFirstPage=1, navigateLastPage=4, navigatepageNums=[1, 2, 3, 4]
}
