package com.cscs.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SMS")
public class SMS implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String softwareName;
	private Computer computer;
	private String folder;

	public SMS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SMS(int id, String softwareName, Computer computer, String folder) {
		this.id = id;
		this.softwareName = softwareName;
		this.computer = computer;
		this.folder = folder;
	}

	@Id
	@Column(name = "id", nullable = false, length = 10)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SOFT_NAME", nullable = false, length = 125)
	public String getSoftwareName() {
		return this.softwareName.toLowerCase();
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMP_ID", nullable = true)
	public Computer getComputer() {
		return computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	@Column(name = "FOLDER", nullable = true, length = 75)
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}