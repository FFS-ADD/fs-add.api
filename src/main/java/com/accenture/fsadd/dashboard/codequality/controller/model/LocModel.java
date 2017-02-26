package com.accenture.fsadd.dashboard.codequality.controller.model;

public class LocModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codeLines;
	
	private String line;
	
	private String statement;
	
	private String file;

	public String getCodeLines() {
		return codeLines;
	}

	public void setCodeLines(String codeLines) {
		this.codeLines = codeLines;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
