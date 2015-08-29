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
@Table(name = "Computer")
public class Computer implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Department dept;
	private String name;
	private String gaNo;
	private OS_VER os;
	private int catalog;

	public Computer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Computer(int id, Department dept, String name, String gaNo, OS_VER os, int catalog) {
		this.id = id;
		this.dept = dept;
		this.name = name;
		this.gaNo = gaNo;
		this.os = os;
		this.catalog = catalog;
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

	@Column(name = "name", nullable = false, length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ga_no", nullable = false, length = 15)
	public String getGaNo() {
		return gaNo;
	}

	public void setGaNo(String gaNo) {
		this.gaNo = gaNo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "os_id", nullable = true)
	public OS_VER getOs() {
		return os;
	}

	public void setOs(OS_VER os) {
		this.os = os;
	}

	@Column(name = "catalog", nullable = false, length = 2)
	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

}