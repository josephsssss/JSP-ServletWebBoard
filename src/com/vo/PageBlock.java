package com.vo;

public class PageBlock {
	private int currentPage;
	private int numberPerPage = 10;
	private int numberOfPageBlock = 10;
	private int start, end;  // startOfPageBlock, endOfPageBlock;
	private boolean prev, next;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNumberPerPage() {
		return numberPerPage;
	}
	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}
	public int getNumberOfPageBlock() {
		return numberOfPageBlock;
	}
	public void setNumberOfPageBlock(int numberOfPageBlock) {
		this.numberOfPageBlock = numberOfPageBlock;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	} 
}
