package com.cscs.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_DEFAULT")
public class SYS_DEFAULT implements Serializable {
	private static final long serialVersionUID = 1L;
	private String softwareName;

	public SYS_DEFAULT(String softwareName) {
		this.softwareName = softwareName;
	}

	@Id
	@Column(name = "software_name", nullable = false, length = 125)
	public String getSoftwareName() {
		return this.softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

}