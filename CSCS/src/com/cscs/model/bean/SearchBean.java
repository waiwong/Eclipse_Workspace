package com.cscs.model.bean;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.cscs.model.bo.MyObject;
import com.cscs.model.bo.SMSView;
import com.cscs.model.service.SMSViewService;

@ManagedBean
@SessionScoped
public class SearchBean {
	protected String keyword, message;
	protected List<MyObject> resultList;
	private List<SMSView> softwareList;
	protected List<Integer> pageList;
	protected int firstRow, totalPages, currentPage = 1;
	private int pageSize = 20, pageRange = 10, totalRows = 0, rowSize = 0;

	@ManagedProperty(name = "smsViewService", value = "#{smsViewService}")
	protected SMSViewService smsViewService;

	public void setSmsViewService(SMSViewService smsViewService) {
		this.smsViewService = smsViewService;
	}

	// page getter and setter
	public int getFirstRow() {
		return firstRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	// search
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public List<MyObject> getResultList() {
		return resultList;
	}

	public void search() {
		resultList = new LinkedList<MyObject>();
		pageList = new LinkedList<Integer>();
		currentPage = 1;
		rowSize = 0;
		firstRow = 0;
		totalRows = smsViewService.getCountOfSoftware(keyword);
		totalPages = ((totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1);
		softwareList = smsViewService.findPageBySoftware(keyword, rowSize);
		if (softwareList.size() == 0) {
			message = "Error : No suitable records !";
			resultList.clear();
		} else {
			message = null;
			setSoftwareType(softwareList);
			pageRange = (pageRange <= totalPages)? pageRange : totalPages;
			for(int i = 1; i <= pageRange; i++)
				pageList.add(i);
		}
	}

	public void setSoftwareType(List<SMSView> list) {
		resultList.clear();
		List<SMSView> defaultList = select(list,
				having(on(SMSView.class).isFlag(), equalTo(true)));
		list.removeAll(defaultList);
		setResultList("Program", list);
		setResultList("System Default", defaultList);
	}

	public void setResultList(String title, List<SMSView> list) {
		MyObject myObject = new MyObject();
		if (list.size() != 0) {
			myObject.setObject(title);
			myObject.setObjList(list);
		}
		resultList.add(myObject);
	}

	// page setting
	public void pageFirst(ActionEvent event) {
		rowSize = 0;
		page(0);
	}

	public void pagePrevious(ActionEvent event) {
		int size = firstRow - pageSize;
		if (size >= 0) {
			rowSize -= pageSize;
			page(size);
		}
	}

	public void pageNext(ActionEvent event) {
		int size = firstRow + pageSize;
		if (size < totalRows) {
			rowSize = currentPage * pageSize;
			page(size);
		}
	}

	public void pageLast(ActionEvent event) {
		rowSize = (totalPages - 1) * pageSize;
		page(totalRows - ((totalRows % pageSize != 0) ? totalRows % pageSize : pageSize));
	}

	// get the component of jsf page
	public void changePage(int page) {
		currentPage = page - 1;
		rowSize = currentPage * pageSize;
		pageLink();
		page(currentPage * pageSize);
	}

	public void page(int firstRow) {
		this.firstRow = firstRow;
		currentPage = (totalRows / pageSize) - ((totalRows - firstRow) / pageSize) + 1;
		softwareList = smsViewService.findPageBySoftware(keyword, rowSize);
		setSoftwareType(softwareList);
	}
	
	private void pageLink() {
		int start = currentPage - 2, end = 0;
		if(start >= 1) {
			end = start >> 2;
			System.out.println(end);
		}
	}
	
}