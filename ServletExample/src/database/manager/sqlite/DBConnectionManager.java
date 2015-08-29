﻿package database.manager.sqlite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class DBConnectionManager {
	static private DBConnectionManager instance; // 创建唯一实例
	static private int clients; // 客户端

	private Vector drivers = new Vector(); // 驱动程序
	private PrintWriter log; // 日志
	private Hashtable pools = new Hashtable(); // 连接池

	// 返回唯一实例,如果是第一次调用此方法,则创建实例
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}

	private DBConnectionManager() {
		init();
	}

	// 将连接对象返回给由名字指定的连接池
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		}
	}

	// 获得一个空闲的连接.如果没有空闲连接且已有连接数小于最大连接数,则创建并返回新连接
	public Connection getConnection(String name) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection();
		}
		return null;
	}

	/**
	 * 获得一个空闲的连接.若没有空闲的连接,且已有连接数小于最大连接数限制, 则创建并返回新连接.否则,在指定的时间内等待其它线程释放连接.
	 */
	public Connection getConnection(String name, long time) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection(time);
		}
		return null;
	}

	// 关闭所有连接,撤销驱动程序的注册
	public synchronized void release() {
		// 等待直到最后一个客户程序调用
		if (--clients != 0) {
			return;
		}

		Enumeration allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		Enumeration allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				log("撤销JDBC驱动程序 " + driver.getClass().getName() + "的注册");
			} catch (SQLException e) {
				log(e, "无法撤销下列JDBC驱动程序的注册: " + driver.getClass().getName());
			}
		}
	}

	// 根据指定属性创建连接池实例.
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					log("没有为连接池" + poolName + "指定URL");
					continue;
				}
				// String user = props.getProperty(poolName + ".user");
				// String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					log("错误的最大连接数限制: " + maxconn + " .连接池: " + poolName);
					max = 0;
				}
				DBConnectionPool pool = new DBConnectionPool(poolName, url, max);
				pools.put(poolName, pool);
				log("成功创建连接池" + poolName);
			}
		}
	}

	// 读取属性完成初始化
	private void init() {
		InputStream is = getClass().getResourceAsStream("/DBConfig.properties"); // 读取DBConfig.properties文件中的属性配置
		Properties dbProps = new Properties();
		try {
			dbProps.load(is);
		} catch (Exception e) {
			System.err.println("不能读取属性文件. "
					+ "请确保db.properties在CLASSPATH指定的路径中");
			return;
		}
		String logFile = dbProps.getProperty("logfile",
				"DBConnectionManager.log");
		try {
			log = new PrintWriter(new FileWriter(logFile, true), true);
		} catch (IOException e) {
			System.err.println("无法打开日志文件: " + logFile);
			log = new PrintWriter(System.err);
		}
		loadDrivers(dbProps);
		createPools(dbProps);
	}

	// 装载和注册所有JDBC驱动程序
	private void loadDrivers(Properties props) {
		String driverClasses = props.getProperty("drivers");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				log("成功注册JDBC驱动程序" + driverClassName);
			} catch (Exception e) {
				log("无法注册JDBC驱动程序: " + driverClassName + ", 错误: " + e);
			}
		}
	}

	// 将文本信息写入日志文件
	private void log(String msg) {
		log.println(new Date() + ": " + msg);
	}

	// 将文本信息与异常写入日志文件
	private void log(Throwable e, String msg) {
		log.println(new Date() + ": " + msg);
		e.printStackTrace(log);
	}

	/**
	 * 此内部类定义了一个连接池. 它能够根据要求创建新连接,直到预定的最大连接数为止. 在返回连接给应用程序之前,它能够验证连接的有效性.
	 */
	class DBConnectionPool {
		private int checkedOut;
		private Vector freeConnections = new Vector();
		private int maxConn;
		private String name;
		private String URL;

		// 创建新的连接池
		public DBConnectionPool(String name, String URL, int maxConn) {
			this.name = name;
			this.URL = URL;
			this.maxConn = maxConn;
		}

		// 将不再使用的连接返回给连接池
		public synchronized void freeConnection(Connection con) {
			// 将指定连接加入到向量末尾
			freeConnections.addElement(con);
			checkedOut--;
			notifyAll();
		}

		/**
		 * 从连接池获得一个空闲的连接. 如没有空闲的连接且当前连接数小于最大连接数限制, 则创建新连接.
		 * 如原来登记为空闲的连接不再有效,则从向量Vector中删除, 然后递归调用以尝试新的空闲连接.
		 */
		public synchronized Connection getConnection() {
			Connection con = null;
			if (freeConnections.size() > 0) {
				// 获取向量中第一个可用连接
				con = (Connection) freeConnections.firstElement();
				freeConnections.removeElementAt(0);
				try {
					if (con.isClosed()) {
						log("从连接池" + name + "删除一个无效连接");
						// 递归调用自己,尝试再次获取可用连接
						con = getConnection();
					}
				} catch (SQLException e) {
					log("从连接池" + name + "删除一个无效连接");
					// 递归调用自己,尝试再次获取可用连接
					con = getConnection();
				}
			} else if (maxConn == 0 || checkedOut < maxConn) {
				con = newConnection();
			}
			if (con != null) {
				checkedOut++;
			}
			return con;
		}

		// 从连接池获取可用连接.可以指定客户程序能够等待的最长时间
		public synchronized Connection getConnection(long timeout) {
			long startTime = new Date().getTime();
			Connection con;
			while ((con = getConnection()) == null) {
				try {
					wait(timeout);
				} catch (InterruptedException e) {
				}
				if ((new Date().getTime() - startTime) >= timeout) {
					// wait()返回的原因是超时
					return null;
				}
			}
			return con;
		}

		// 关闭所有连接
		public synchronized void release() {
			Enumeration allConnections = freeConnections.elements();
			while (allConnections.hasMoreElements()) {
				Connection con = (Connection) allConnections.nextElement();
				try {
					con.close();
					log("关闭连接池" + name + "中的一个连接");
				} catch (SQLException e) {
					log(e, "无法关闭连接池" + name + "中的连接");
				}
			}
			freeConnections.removeAllElements();
		}

		// 创建新的连接
		private Connection newConnection() {
			Connection con = null;
			try {
				con = DriverManager.getConnection(URL);
				log("连接池" + name + "创建一个新的连接");
			} catch (SQLException e) {
				log(e, "无法创建下列URL的连接: " + URL);
				return null;
			}
			return con;
		}
	}
}
