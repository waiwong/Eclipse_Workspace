package jsp.sqlServer.util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class FileUpload {

	private HttpServletRequest request = null;

	private String uploadPath = null;

	private static int BUFSIZE = 1024 * 8;

	private Hashtable paramHt = new Hashtable();

	private ArrayList updFileArr = new ArrayList();

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setUploadPath(String path) {
		this.uploadPath = path;
	}

	public int process() {
		int status = 0;
		status = preCheck();
		if (status != 0)
			return status;
		try {

			String name = null;

			String value = null;

			boolean fileFlag = false;

			File tmpFile = null;

			String fName = null;

			FileOutputStream baos = null;
			
			BufferedOutputStream bos = null;

			paramHt = new Hashtable();
			updFileArr = new ArrayList();
			int rtnPos = 0;
			byte[] buffs = new byte[BUFSIZE * 8];

			String contentType = request.getContentType();
			int index = contentType.indexOf("boundary=");
			String boundary = "--" + contentType.substring(index + 9);
			String endBoundary = boundary + "--";

			ServletInputStream sis = request.getInputStream();

			while ((rtnPos = sis.readLine(buffs, 0, buffs.length)) != -1) {
				String strBuff = new String(buffs, 0, rtnPos);

				if (strBuff.startsWith(boundary)) {
					if (name != null && name.trim().length() > 0) {

						if (fileFlag) {
							bos.flush();
							baos.close();
							bos.close();
							baos = null;
							bos = null;
							updFileArr.add(fName);
						} else {
							Object obj = paramHt.get(name);
							ArrayList al = new ArrayList();
							if (obj != null) {
								al = (ArrayList) obj;
							}
							al.add(value);
							System.out.println(value);
							paramHt.put(name, al);
						}
					}
					name = new String();
					value = new String();
					fileFlag = false;
					fName = new String();
					rtnPos = sis.readLine(buffs, 0, buffs.length);
					if (rtnPos != -1) {
						strBuff = new String(buffs, 0, rtnPos);
						if (strBuff.toLowerCase().startsWith(
								"content-disposition: form-data; ")) {
							int nIndex = strBuff.toLowerCase().indexOf(
									"name=\"");
							int nLastIndex = strBuff.toLowerCase().indexOf(
									"\"", nIndex + 6);
							name = strBuff.substring(nIndex + 6, nLastIndex);
						}
						int fIndex = strBuff.toLowerCase().indexOf(
								"filename=\"");
						if (fIndex != -1) {
							fileFlag = true;
							int fLastIndex = strBuff.toLowerCase().indexOf(
									"\"", fIndex + 10);
							fName = strBuff.substring(fIndex + 10, fLastIndex);
							fName = getFileName(fName);
							if (fName == null || fName.trim().length() == 0) {
								fileFlag = false;
								sis.readLine(buffs, 0, buffs.length);
								sis.readLine(buffs, 0, buffs.length);
								sis.readLine(buffs, 0, buffs.length);
								continue;
							}else{
								fName = getFileNameByTime(fName);
								sis.readLine(buffs, 0, buffs.length);
								sis.readLine(buffs, 0, buffs.length);
							}
						}
					}
				} else if (strBuff.startsWith(endBoundary)) {
					if (name != null && name.trim().length() > 0) {
						if (fileFlag) {
							bos.flush();
							baos.close();
							bos.close();
							baos = null;
							bos = null;
							updFileArr.add(fName);
						} else {
							Object obj = paramHt.get(name);
							ArrayList al = new ArrayList();
							if (obj != null) {
								al = (ArrayList) obj;
							}
							al.add(value);
							paramHt.put(name, al);
						}
					}
				} else {
					if (fileFlag) {
						if (baos == null && bos == null) {
							tmpFile = new File(uploadPath + fName);
							baos = new FileOutputStream(tmpFile);
							bos = new BufferedOutputStream(baos);
						}
						bos.write(buffs, 0, rtnPos);
						baos.flush();
					} else {
						System.out.println("File infomation :" + value + "--" + strBuff);
						value = value + strBuff;
					}
				}
			}

		} catch (IOException e) {
			status = 4;
		}
		return status;
	}

	private int preCheck() {
		int errCode = 0;
		if ( request == null )
			return 1;
		if ( uploadPath == null || uploadPath.trim().length() == 0 )
			return 2;
		else{
			File tmpF = new File(uploadPath);
			if (!tmpF.exists())
				return 2;
		}
		String contentType = request.getContentType();
		if ( contentType.indexOf("multipart/form-data") == -1 )
			return 3;
		return errCode;
	}
	public String getParameter(String name){
		String value = "";
		if ( name == null || name.trim().length() == 0 )
			return value;
		value = (paramHt.get(name) == null)?"":(String)((ArrayList)paramHt.get(name)).get(0);
		return value;
	}
	public String[] getParameters(String name){
		if ( name == null || name.trim().length() == 0 )
			return null;
		if ( paramHt.get(name) == null )
			return null;
		ArrayList al = (ArrayList)paramHt.get(name);
		String[] strArr = new String[al.size()];
		for ( int i=0;i<al.size();i++ )
			strArr[i] = (String)al.get(i);
		return strArr;
	}
	
	public int getUpdFileSize(){
		return updFileArr.size();
	}
	
	public String[] getUpdFileNames(){
		String[] strArr = new String[updFileArr.size()];
		for ( int i=0;i<updFileArr.size();i++ )
			strArr[i] = (String)updFileArr.get(i);
		return strArr;
	}
	private String getFileName(String input){
		int fIndex = input.lastIndexOf("\\");
		if (fIndex == -1) {
			fIndex = input.lastIndexOf("/");
			if (fIndex == -1) {
				return input;
			}
		} 
		input = input.substring(fIndex + 1);
		return input;
	}
	private String getFileNameByTime(String input){
		int index = input.indexOf(".");
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return input.substring(0,index) + sdf.format(dt) + input.substring(index);
	}
}
