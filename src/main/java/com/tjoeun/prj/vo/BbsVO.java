package com.tjoeun.prj.vo;

public class BbsVO {
	private int num;
	private String author;
	private String wdate;
	private String title;
	private String contents;
	private int hit;
	private int pnum;
	
	public BbsVO() {}
	
	public BbsVO(int num, String author, String wdate, String title, String contents, int hit, int pnum) {
		super();
		this.num = num;
		this.author = author;
		this.wdate = wdate;
		this.title = title;
		this.contents = contents;
		this.hit = hit;
		this.pnum = pnum;
	}

	public String toString() {
		return "num: "+num+"| author: "+author+"| wdate: "+wdate+"| title: "+title
				+"| contents: "+contents+"| hit: "+hit+"| pnum: "+pnum;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	
}
