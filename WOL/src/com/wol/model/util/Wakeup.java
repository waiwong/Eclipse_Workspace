package com.wol.model.util;

import java.io.File;

import com.wol.model.bo.Computer;

public class Wakeup implements Runnable {
	private Computer computer;
	private MyCommand cmd;

	public Wakeup(Computer computer, MyCommand cmd) {
		this.computer = computer;
		this.cmd = cmd;
	}

	public void run() {
		try {
			String mc_wol = System.getProperty("user.dir") + File.separator + "mc-wol.exe ",
						 hostName = computer.getHostName(),
						 macAddr = computer.getMacAddr();
			if (!cmd.isConnect(hostName) && macAddr != null && !macAddr.equals(""))
				Runtime.getRuntime().exec(mc_wol + macAddr);
			else
				System.out.println(hostName + " : MAC Address is null.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
