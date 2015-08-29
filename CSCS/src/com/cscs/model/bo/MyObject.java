package com.cscs.model.bo;

import java.util.List;

public class MyObject {
	private List<SMSView> objList;
	private Object object;
	private int length;

	public List<?> getObjList() {
		return objList;
	}

	public void setObjList(List<SMSView> objList) {
		this.objList = objList;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}