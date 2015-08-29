package com.cscs.model.bo;

import java.util.Iterator;

public class MyObject {
	private Iterator<?> objList;
	private Object object;
	private int length;

	public MyObject(Iterator<?> objList, Object object, int length) {
		this.objList = objList;
		this.object = object;
		this.length = length;
	}

	public Iterator<?> getObjList() {
		return objList;
	}

	public void setObjList(Iterator<?> objList) {
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