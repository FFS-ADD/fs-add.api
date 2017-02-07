package com.accenture.fsadd.sonar.controller.model;

import com.accenture.fsadd.common.mvc.model.CommonModel;

public class DuplicationModel extends CommonModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String duplication;
	
	private String line;
	
	private String blocks;
	
	private String file;

	public String getDuplication() {
		return duplication;
	}

	public void setDuplication(String duplication) {
		this.duplication = duplication;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getBlocks() {
		return blocks;
	}

	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	

}
