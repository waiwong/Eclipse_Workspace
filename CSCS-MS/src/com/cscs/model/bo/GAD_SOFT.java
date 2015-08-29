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
@Table(name = "GAD_SOFT")
public class GAD_SOFT implements Serializable {
	private static final long serialVersionUID = 1L;
	private String gaNo;
	private String softwareName;
	private Department dept;

	public GAD_SOFT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GAD_SOFT(String gaNo, String softwareName, Department dept) {
		this.gaNo = gaNo;
		this.softwareName = softwareName;
		this.dept = dept;
	}

	@Id
	@Column(name = "ga_no", nullable = false, length = 15)
	public String getGaNo() {
		return gaNo;
	}

	public void setGaNo(String gaNo) {
		this.gaNo = gaNo;
	}

	@Column(name = "software_name", nullable = false, length = 125)
	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id", nullable = false)
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

}