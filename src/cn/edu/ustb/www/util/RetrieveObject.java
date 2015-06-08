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

	/* 获得满足条件的数据（一行一行的给，先把每行各列赋值再换行） */
	public Collection getTableCollection(String sqlStr) {
		Collection collection = new Vector(); //
		conn = DataBaseConnection.conn;
		try {
			rs = conn.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData(); // 获取列的类型和属性
			while (rs.next()) {
				Vector vdata = new Vector();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					vdata.addElement(rs.getObject(i));
					// rs.getObject(i):以 Java编程语言中 Object 的形式获取此ResultSet对象的当前行中指定列的值。
				}
				collection.add(vdata);
			}
		} catch (SQLException sql) {
			new ErrorPage("执行的SQL语句为:\n" + sqlStr + "\n错误信息为："
					+ sql.getMessage());
			sql.printStackTrace();
			return null;
		}
		return collection;
	}

	/* 创建数据表(字段为name[],条件sql语句为sqlStr) */
	public DefaultTableModel getTableModel(String[] name, String sqlStr) {
		Vector vname = new Vector();
		for (int i = 0; i < name.length; i++) {
			vname.addElement(name[i]);
		}
		// DefaultTableModel(Vector columnNames, int rowCount)
		// 构造一个DefaultTableModel，它的列数与columnNames中元素的数量相同，并具有rowCount 行null对象值。
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
				tableModel.addRow(vdata);//addRow(Vector rowData)添加一行到模型的结尾。
			}
		} catch (java.sql.SQLException sql) {
			sql.printStackTrace();
			new ErrorPage("执行的SQL语句为:\n" + sqlStr + "\n错误信息为："
					+ sql.getMessage());
			return null;
		}
		return tableModel;
	}

	/* 获得满足条件的数据（一行一行的给，先把每行各列赋值再换行） */
	public Vector getObjectRow(String sqlStr) {
		Vector vdata = new Vector();
		conn = DataBaseConnection.conn;
		try {
			rs = conn.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					vdata.addElement(rs.getObject(i));
					// rs.getObject(i):以 Java编程语言中 Object 的形式获取此ResultSet对象的当前行中指定列的值。
				}
			}
		} catch (java.sql.SQLException sql) {
			sql.printStackTrace();
			return null;
		}
		return vdata;
	}
}
