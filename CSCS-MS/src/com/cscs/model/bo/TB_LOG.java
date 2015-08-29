package com.cscs.model.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LOG")
public class TB_LOG implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tableName;
	private String currentFileName;
	private Date lastUpdate;
	private int index;

	@Id
	@Column(name = "table_name", nullable = false, length = 20)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "current_file_name", nullable = true, length = 50)
	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	@Column(name = "last_update", nullable = true, length = 25)
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Column(name = "tb_index")
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}