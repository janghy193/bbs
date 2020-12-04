package com.tjoeun.prj.vo;

public class AttachmentVO {
	private int attnum;
	private int num;
	private String filename;
	private long filesize;
	private String mimetype;
	
	public AttachmentVO() {}

	public AttachmentVO(int attnum, int num, String filename, long filesize, String mimetype) {
		super();
		this.attnum = attnum;
		this.num = num;
		this.filename = filename;
		this.filesize = filesize;
		this.mimetype = mimetype;
	}

	public int getAttnum() {
		return attnum;
	}

	public void setAttnum(int attnum) {
		this.attnum = attnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}
	
	
}
