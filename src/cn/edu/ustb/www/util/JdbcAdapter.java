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
		System.out.println("ִ�е����Ϊ:" + sqlState);
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

	/* ������ִ�ж����ݿ�ĸ��ֲ��� */
	public boolean AdapterObject(String sqlState) {
		boolean flag = false;
		conn = DataBaseConnection.getConn();
		try {
			conn = DataBaseConnection.getConn();
			pstmt = conn.prepareStatement(sqlState);
			pstmt.execute();
			flag = true;
			JOptionPane.showMessageDialog(null, infoStr + "���ݳɹ�!!!", "ϵͳ��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException sql) {
			ErrorPage error = new ErrorPage("ִ�е�SQL���Ϊ:\n" + sqlState
					+ "\n������ϢΪ��" + sql.getMessage());
			flag = false;
			sql.printStackTrace();
		}
		return flag;
	}

	/* ִ��ɾ�����ݱ��е����� */
	public boolean DeleteObject(String deleteSql) {
		infoStr = "ɾ��";
		return AdapterObject(deleteSql);
	}

	/* ��֤���ݱ����Ƿ�������� */
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
			new ErrorPage("ִ�е�SQL���Ϊ:\n" + sqlStr + "\n������ϢΪ��"
					+ sql.getMessage());
			sql.printStackTrace();
			return false;
		}

		return false;
	}
}
