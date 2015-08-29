package com.cscs.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_view")
public class SMSView implements Serializable {
	private static final long serialVersionUID = 1L;
	private String softwareName;
	private boolean flag;

	// Property accessors
	@Id
	@Column(name = "software_name", nullable = false, length = 125)
	public String getSoftwareName() {
		return this.softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	@Column(name = "flag", nullable = false, length = 5)
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}