package com.douzone.mysite.vo;

public class BoardVo 
{
	private long no;
	private String title;
	private String name;
	private long hit;
	private String write_Date;
	private long depth;
	private String contents;
	private long userNo;
	private long gNo;
	private long oNo;
	private long totalCount;
	
	
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public long getgNo() {
		return gNo;
	}
	public void setgNo(long gNo) {
		this.gNo = gNo;
	}
	public long getoNo() {
		return oNo;
	}
	public void setoNo(long oNo) {
		this.oNo = oNo;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public String getWrite_Date() {
		return write_Date;
	}
	public void setWrite_Date(String write_Date) {
		this.write_Date = write_Date;
	}
	
	
}
