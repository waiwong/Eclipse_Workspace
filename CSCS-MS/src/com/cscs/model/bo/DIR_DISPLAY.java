package com.cscs.model.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIR_DISPLAY")
public class DIR_DISPLAY implements Serializable {
	private static final long serialVersionUID = 1L;
	private String folder;
	private String displayName;

	public DIR_DISPLAY() {}
	
	public DIR_DISPLAY(String folder, String displayName) {
		this.folder = folder;
		this.displayName = displayName;
	}

	@Id
	@Column(name = "Folder", nullable = false, length = 75)
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Id
	@Column(name = "DisplayName", nullable = false, length = 75)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}