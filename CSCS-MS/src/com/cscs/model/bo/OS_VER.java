package com.cscs.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OS_VER")
public class OS_VER implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String cscsName;
	private String abbreviation;

	@Id
	@Column(name = "id", nullable = false, length = 5)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "cscs_name", nullable = false, length = 20)
	public String getCscsName() {
		return cscsName;
	}

	public void setCscsName(String cscsName) {
		this.cscsName = cscsName;
	}

	@Column(name = "abbreviation", nullable = false, length = 10)
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

}