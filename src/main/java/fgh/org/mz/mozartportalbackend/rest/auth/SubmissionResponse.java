package fgh.org.mz.mozartportalbackend.rest.auth;

import fgh.org.mz.mozartportalbackend.model.Submission;

public class SubmissionResponse {
	private long id;
	
	private int year;
	private String quarter;
	
	private String password;
	
	private String partner;
	
	private String fileName;
	
	private String createdBy;
	
	public SubmissionResponse() {}
	
	public SubmissionResponse(Submission submission) {
		this.id = submission.getId();
		this.year = submission.getYear();
		this.quarter = submission.getQuarter();
		this.password = submission.getPassword();
		this.partner = submission.getPartner();
		this.fileName = submission.getFileName();
		this.createdBy = submission.getUser().getName();
	}
	
	public SubmissionResponse(long id, int year, String quarter, String password, String partner, String fileName,
			String createdBy) {
		this.id = id;
		this.year = year;
		this.quarter = quarter;
		this.password = password;
		this.partner = partner;
		this.fileName = fileName;
		this.createdBy = createdBy;
	}	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
