﻿package database.manager.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
	private Connection conn = null; // 数据库连接变量
	private Statement stmt = null; // Statement语句变量
	private PreparedStatement prepstmt = null; // 预定义Statement变量
	private DBConnectionManager dcm = null; // 连接池管理变量

	void init() {
		dcm = DBConnectionManager.getInstance();
		conn = dcm.getConnection("sqlite");
	}

	// 构造数据库的连接和访问类
	public DBConnect() throws Exception {
		init();
		stmt = conn.createStatement();
	}

	public DBConnect(int resultSetType, int resultSetConcurrency)
			throws Exception {
		init();
		stmt = conn.createStatement(resultSetType, resultSetConcurrency);
	}

	/*
	 * 构造数据库的连接和访问类 预编译SQL语句
	 */

	public DBConnect(String sql) throws Exception {
		init();
		this.prepareStatement(sql);
	}

	public DBConnect(String sql, int resultSetType, int resultSetConcurrency)
			throws Exception {
		init();
		this.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	// 返回连接
	public Connection getConnection() {
		return conn;
	}

	// 关闭连接
	public void close() throws Exception {
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}
		if (prepstmt != null) {
			prepstmt.close();
			prepstmt = null;
		}
		if (conn != null) {
			dcm.freeConnection("sqlServer", conn);
		}
	}

	/*
	 * PreparedStatement 创建一个PreparedStatement对象
	 */

	public void prepareStatement(String sql) throws SQLException {
		prepstmt = conn.prepareStatement(sql);
	}

	public void prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		prepstmt = conn.prepareStatement(sql, resultSetType,
				resultSetConcurrency);
	}

	/**
	 * 设置对应值 index 参数索引 value 对应值
	 */
	public void setString(int index, String value) throws SQLException {
		prepstmt.setString(index, value);
	}

	public void setInt(int index, int value) throws SQLException {
		prepstmt.setInt(index, value);
	}

	public void setBoolean(int index, boolean value) throws SQLException {
		prepstmt.setBoolean(index, value);
	}

	public void setDate(int index, Date value) throws SQLException {
		prepstmt.setDate(index, value);
	}

	public void setLong(int index, long value) throws SQLException {
		prepstmt.setLong(index, value);
	}

	public void setFloat(int index, float value) throws SQLException {
		prepstmt.setFloat(index, value);
	}

	public void setBytes(int index, byte[] value) throws SQLException {
		prepstmt.setBytes(index, value);
	}

	public void clearParameters() throws SQLException {
		prepstmt.clearParameters();
		prepstmt = null;
	}

	// 返回预设状态
	public PreparedStatement getPreparedStatement() {
		return prepstmt;
	}

	// 返回状态
	public Statement getStatement() {
		return stmt;
	}

	// 执行SQL语句返回字段集
	public ResultSet executeQuery(String sql) throws SQLException {
		if (stmt != null) {
			return stmt.executeQuery(sql);
		} else
			return null;
	}

	public ResultSet executeQuery() throws SQLException {
		if (prepstmt != null) {
			return prepstmt.executeQuery();
		} else
			return null;
	}

	// 执行SQL语句
	public int executeUpdate(String sql) throws SQLException {
		if (stmt != null)
			return stmt.executeUpdate(sql);
		else
			return -1;
	}

	public int executeUpdate() throws SQLException {
		if (prepstmt != null)
			return prepstmt.executeUpdate();
		else
			return -1;
	}

}