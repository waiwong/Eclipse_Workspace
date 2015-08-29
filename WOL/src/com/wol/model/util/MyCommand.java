package com.wol.model.util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;

public class MyCommand {

	public String getIpAddr(String hostName) {
		String ipAddress = null;
		try {
			InetAddress inetAddr = InetAddress.getByName(hostName);
			ipAddress = inetAddr.getHostAddress();
		} catch (Exception e) {
			System.out.println("UnknownHostException : " + hostName);
		}
		return ipAddress;
	}

	public String getMacAddr(String ipAddress, String hostName) {
		String str = null, macAddress = null;
		try { // DOS command
			String cmd = "nbtstat -a " + ipAddress;
			Process process = Runtime.getRuntime().exec(cmd);
			InputStreamReader ir = new InputStreamReader(process.getInputStream());
			LineNumberReader lineReader = new LineNumberReader(ir);

			for (int i = 0;; i++) {
				str = lineReader.readLine();
				// subString : MAC Address =
				int indexOf = str.indexOf("=");
				if (indexOf > 1) {
					macAddress = str.substring(indexOf + 1, str.length()).trim();
					break;
				}
			}
			lineReader.close();
			ir.close();
			process.destroy();
		} catch (Exception ex) {
			System.out.println("Find MAC Address fail : " + hostName);
		}
		return macAddress;
	}

	public boolean isConnect(String hostName) {
		boolean flag = false;
		try {
			InetAddress inetAddr = InetAddress.getByName(hostName);
			flag = inetAddr.isReachable(1000);
		} catch (Exception e) {
			System.out.println("Power Off : " + hostName);
		}
		return flag;
	}

}