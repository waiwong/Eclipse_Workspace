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
@Table(name = "TB_CSCS")
public class TB_CSCS implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Department dept;
	private String pcGaNo;
	private String codeType;
	private String codeNo;
	private String softwareName;
	private boolean bcp;

	public TB_CSCS() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TB_CSCS(int id, Department dept, String pcGaNo, String codeType,
			String codeNo, String softwareName, boolean bcp) {
		this.id = id;
		this.dept = dept;
		this.pcGaNo = pcGaNo;
		this.codeType = codeType;
		this.codeNo = codeNo;
		this.softwareName = softwareName;
		this.bcp = bcp;
	}

	@Id
	@Column(name = "id", nullable = false, length = 10)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id", nullable = false)
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Column(name = "ga_no", nullable = false, length = 25)
	public String getPcGaNo() {
		return pcGaNo;
	}

	public void setPcGaNo(String pcGaNo) {
		this.pcGaNo = pcGaNo;
	}

	@Column(name = "code_type", nullable = false, length = 10)
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Column(name = "code_no", nullable = false, length = 45)
	public String getCodeNo() {
		return codeNo;
	}

	public void setCodeNo(String codeNo) {
		this.codeNo = codeNo;
	}

	@Column(name = "software_name", nullable = false, length = 125)
	public String getSoftwareName() {
		return softwareName;
	}

	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}

	@Column(name = "bcp", nullable = false, length = 5)
	public boolean isBcp() {
		return bcp;
	}

	public void setBcp(boolean bcp) {
		this.bcp = bcp;
	}

}