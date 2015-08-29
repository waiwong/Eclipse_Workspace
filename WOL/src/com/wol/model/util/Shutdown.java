package com.wol.model.util;

public class Shutdown implements Runnable {
	private String hostName;

	public Shutdown(String hostName) {
		this.hostName = hostName;
	}

	public void run() {
		try { // DOS command
			String cmd = String.format("shutdown /s /m \\\\%s /t 1 /f", hostName);
			Runtime.getRuntime().exec(cmd);
			System.out.println("Remote shutdown : " + hostName);
		} catch (Exception ex) {
			System.out.println("Shutdown command fail : " + hostName);
		}		
	}

}