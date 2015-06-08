package cn.edu.ustb.www.util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//import appstu.model.*;
import javax.swing.JOptionPane;

import cn.edu.ustb.www.view.ErrorPage;

import java.sql.*;

public class JdbcAdapter {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	public static String infoStr = null;

	public boolean BuildeDeleteTempView(String sqlState) {
		boolean flag = false;
		System.out.println("执行的语句为:" + sqlState);
		try {
			conn = DataBaseConnection.getConn();
			pstmt = conn.prepareStatement(sqlState);
			pstmt.execute();
			flag = true;

		} catch (java.sql.SQLException sql) {
			flag = false;
			sql.printStackTrace();
		}
		return flag;
	}

	/* 真正的执行对数据库的各种操作 */
	public boolean AdapterObject(String sqlState) {
		boolean flag = false;
		conn = DataBaseConnection.getConn();
		try {
			conn = DataBaseConnection.getConn();
			pstmt = conn.prepareStatement(sqlState);
			pstmt.execute();
			flag = true;
			JOptionPane.showMessageDialog(null, infoStr + "数据成功!!!", "系统提示",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException sql) {
			ErrorPage error = new ErrorPage("执行的SQL语句为:\n" + sqlState
					+ "\n错误信息为：" + sql.getMessage());
			flag = false;
			sql.printStackTrace();
		}
		return flag;
	}

	/* 执行删除数据表中的数据 */
	public boolean DeleteObject(String deleteSql) {
		infoStr = "删除";
		return AdapterObject(deleteSql);
	}

	/* 验证数据表中是否存在数据 */
	private boolean validateID(String id, String tb_name, String idvalue) {
		String sqlStr = null;
		sqlStr = "select count(*) from " + tb_name + " where " + id + " = '"
				+ idvalue + "'";
		System.out.println(sqlStr);
		try {
			conn = DataBaseConnection.getConn();
			pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return true;
			}
		} catch (java.sql.SQLException sql) {
			new ErrorPage("执行的SQL语句为:\n" + sqlStr + "\n错误信息为："
					+ sql.getMessage());
			sql.printStackTrace();
			return false;
		}

		return false;
	}
}
