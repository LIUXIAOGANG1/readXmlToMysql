package cn.edu.ustb.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	public static Connection conn=null;
	public DataBaseConnection() {
		getConn();
	}
	public static Connection getConn() {
		String url="jdbc:mysql://localhost:3306/cheng?"+"user=root&password=root&useUnicode=true&characterEncoding=gbk";//
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url);
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
