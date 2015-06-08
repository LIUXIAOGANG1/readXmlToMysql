package cn.edu.ustb.www.util;

import javax.swing.table.DefaultTableModel;

import cn.edu.ustb.www.view.ErrorPage;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.Collection;

public class RetrieveObject {
	private Connection conn = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	/* ����������������ݣ�һ��һ�еĸ����Ȱ�ÿ�и��и�ֵ�ٻ��У� */
	public Collection getTableCollection(String sqlStr) {
		Collection collection = new Vector(); //
		conn = DataBaseConnection.conn;
		try {
			rs = conn.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData(); // ��ȡ�е����ͺ�����
			while (rs.next()) {
				Vector vdata = new Vector();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					vdata.addElement(rs.getObject(i));
					// rs.getObject(i):�� Java��������� Object ����ʽ��ȡ��ResultSet����ĵ�ǰ����ָ���е�ֵ��
				}
				collection.add(vdata);
			}
		} catch (SQLException sql) {
			new ErrorPage("ִ�е�SQL���Ϊ:\n" + sqlStr + "\n������ϢΪ��"
					+ sql.getMessage());
			sql.printStackTrace();
			return null;
		}
		return collection;
	}

	/* �������ݱ�(�ֶ�Ϊname[],����sql���ΪsqlStr) */
	public DefaultTableModel getTableModel(String[] name, String sqlStr) {
		Vector vname = new Vector();
		for (int i = 0; i < name.length; i++) {
			vname.addElement(name[i]);
		}
		// DefaultTableModel(Vector columnNames, int rowCount)
		// ����һ��DefaultTableModel������������columnNames��Ԫ�ص�������ͬ��������rowCount ��null����ֵ��
		DefaultTableModel tableModel = new DefaultTableModel(vname, 0);
		conn = DataBaseConnection.conn;
		try {
			rs = conn.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData();
			while (rs.next()) {
				Vector vdata = new Vector();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					vdata.addElement(rs.getObject(i));
				}
				tableModel.addRow(vdata);//addRow(Vector rowData)���һ�е�ģ�͵Ľ�β��
			}
		} catch (java.sql.SQLException sql) {
			sql.printStackTrace();
			new ErrorPage("ִ�е�SQL���Ϊ:\n" + sqlStr + "\n������ϢΪ��"
					+ sql.getMessage());
			return null;
		}
		return tableModel;
	}

	/* ����������������ݣ�һ��һ�еĸ����Ȱ�ÿ�и��и�ֵ�ٻ��У� */
	public Vector getObjectRow(String sqlStr) {
		Vector vdata = new Vector();
		conn = DataBaseConnection.conn;
		try {
			rs = conn.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					vdata.addElement(rs.getObject(i));
					// rs.getObject(i):�� Java��������� Object ����ʽ��ȡ��ResultSet����ĵ�ǰ����ָ���е�ֵ��
				}
			}
		} catch (java.sql.SQLException sql) {
			sql.printStackTrace();
			return null;
		}
		return vdata;
	}
}
