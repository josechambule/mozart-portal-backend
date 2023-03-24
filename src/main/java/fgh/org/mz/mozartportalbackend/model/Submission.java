package fgh.org.mz.mozartportalbackend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="submission")
public class Submission implements Serializable {

	private static final long serialVersionUID = 8325873660909077287L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="year")
	private int year;
	
	@Column(name="quarter")
	private String quarter;
	
	@Column(name="password")
	private String password;
	
	@Column(name="partner")
	private String partner;
	
	@Column(name="filename")
	private String fileName;
	
	@ManyToOne
    @JoinColumn(name = "createdBy")
	private User user;

	public Submission() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Submission [id=" + id + ", year=" + year + ", quarter=" + quarter + ", password=" + password
				+ ", partner=" + partner + ", fileName=" + fileName + ", user=" + user + "]";
	}

}
