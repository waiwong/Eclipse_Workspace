package com.cscs.model.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.cscs.model.bo.DIR_DISPLAY;
import com.cscs.model.bo.SMS;
import com.cscs.model.service.DirService;

public class FolderMapper {

	public List<SMS> softwareMapToFolder(ApplicationContext context,
			List<SMS> smsList) throws Exception {
		List<SMS> sfList = new ArrayList<SMS>();
		DirService dirService = (DirService) context.getBean("dirService");
		List<DIR_DISPLAY> dirList = dirService.findAll();

		for (DIR_DISPLAY dir : dirList) {
			String displayName = dir.getDisplayName();
			for(SMS sms : smsList) {
				if(sms.getSoftwareName().equalsIgnoreCase(displayName)) {
					sms.setFolder(dir.getFolder());
					sfList.add(sms);
				}								
			}
		}
		return sfList;
	}

}