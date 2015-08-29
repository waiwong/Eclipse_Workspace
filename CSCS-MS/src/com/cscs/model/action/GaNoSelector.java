package com.cscs.model.action;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;

import com.cscs.model.bo.TB_CSCS;
import com.cscs.model.bo.Department;
import com.cscs.model.bo.MyObject;
import com.cscs.model.service.CSCSService;

public class GaNoSelector {

	public List<MyObject> getPdfContentList(CSCSService cscsService,
			Department dept) {
		List<TB_CSCS> cscsList = cscsService.findByDept(dept);
		List<String> cscSoftNameList = cscsService.groupBySoftName(dept);
		List<MyObject> myObjectList = new LinkedList<MyObject>();
		for (String softName : cscSoftNameList) {

			List<String> list = extract(
					select(cscsList,
							having(on(TB_CSCS.class).getSoftwareName(), equalTo(softName))),
					on(TB_CSCS.class).getCodeNo());
			HashSet<String> hashSet = new HashSet<String>();
			hashSet.addAll(list);
			int length = hashSet.size();
			if (length != 0) {
				MyObject myObject = new MyObject(hashSet.iterator(), softName, length);
				myObjectList.add(myObject);
			}
		}
		return myObjectList;
	}

}