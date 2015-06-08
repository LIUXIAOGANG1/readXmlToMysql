package cn.edu.ustb.www.util;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.*;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.util.DataBaseConnection;
import cn.edu.ustb.www.view.InsertUser;
import cn.edu.ustb.www.view.InsertXml;
import cn.edu.ustb.www.view.LoginRecord;
import cn.edu.ustb.www.view.MainSheet;
import cn.edu.ustb.www.view.QueryUser;
import cn.edu.ustb.www.view.QueryXml;
import cn.edu.ustb.www.view.UpdateUser;
import cn.edu.ustb.www.view.ViewArc;
import cn.edu.ustb.www.view.ViewArcpath;
import cn.edu.ustb.www.view.ViewPlace;
import cn.edu.ustb.www.view.ViewTransition;

public class MenuBarEvent implements ActionListener {
	static JDesktopPane JDeskTop = null;
	private String EventName = "";
	private DefaultDesktopManager desktopManager = new DefaultDesktopManager();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void setDeskTop(JDesktopPane deskTop) {
		this.JDeskTop = deskTop;
	}

	public void setEventName(String eventName) {
		this.EventName = eventName;
	}

	public void actionPerformed(ActionEvent e) {
		/* 退出系统 */
		if (EventName.equals("exitSys")) {
			int result = JOptionPane.showOptionDialog(null, "是否真的退出公司信息管理系统?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.YES_OPTION) {
				try {
					/* 设置日期的另一种方法 */

					GregorianCalendar calendar = new GregorianCalendar();
					int cyear = calendar.get(Calendar.YEAR);
					int cmonth = calendar.get(Calendar.MONTH) + 1;
					int cday = calendar.get(Calendar.DAY_OF_MONTH);
					int chour = calendar.get(Calendar.HOUR_OF_DAY);
					int cminute = calendar.get(Calendar.MINUTE);

					String year = String.valueOf(cyear);
					String month = String.valueOf(cmonth);
					String day = String.valueOf(cday);
					String hour = String.valueOf(chour);
					String minute = String.valueOf(cminute);
					String timeday = hour + " : " + minute;

					Connection conn = DataBaseConnection.getConn();
					Statement statement = conn.createStatement();
					String Strsql = "insert into LoginRecord(操作年度,操作月份,操作日期,操作时间,操作人,操作状态) "
							+ "values ('"
							+ year
							+ "','"
							+ month
							+ "','"
							+ day
							+ "','"
							+ timeday
							+ "','"
							+ LoginSystem.user.getText().trim() + "','退出');";
					statement.executeUpdate(Strsql);

				} catch (SQLException sql) {
					sql.printStackTrace();
				}
				System.exit(0);
			}
			return;
		}

		/* 更换操作员 */
		if (EventName.equals("changeUser")) {
			try {
				/* 设置日期的另一种方法 */

				GregorianCalendar calendar = new GregorianCalendar();
				int cyear = calendar.get(Calendar.YEAR);
				int cmonth = calendar.get(Calendar.MONTH) + 1;
				int cday = calendar.get(Calendar.DAY_OF_MONTH);
				int chour = calendar.get(Calendar.HOUR_OF_DAY);
				int cminute = calendar.get(Calendar.MINUTE);

				String year = String.valueOf(cyear);
				String month = String.valueOf(cmonth);
				String day = String.valueOf(cday);
				String hour = String.valueOf(chour);
				String minute = String.valueOf(cminute);
				String timeday = hour + " : " + minute;

				Connection conn = DataBaseConnection.getConn();
				Statement statement = conn.createStatement();
				String Strsql = "insert into LoginRecord(操作年度,操作月份,操作日期,操作时间,操作人,操作状态) "
						+ "values ('"
						+ year
						+ "','"
						+ month
						+ "','"
						+ day
						+ "','"
						+ timeday
						+ "','"
						+ LoginSystem.user.getText().trim() + "','退出');";
				statement.executeUpdate(Strsql);
			} catch (SQLException sql) {
				sql.printStackTrace();
			}

			MainSheet.frame.dispose();
			new LoginSystem();

		}
		/* 上机日志 */
		if (EventName.equals("LoginRecord")) {
			LoginRecord jfInternalFrame = new LoginRecord();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.show();
			jfInternalFrame.setTitle("上机日志");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		/* 添加员工 */
		if (EventName.equals("insertUser")) {
			new InsertUser();
		}
		/* 修改个人信息 */
		if (EventName.equals("updateMyself")) {
			new UpdateUser();
		}
		/* 查询所有用户 */
		if (EventName.equals("queryUser")) {
			QueryUser jfInternalFrame = new QueryUser();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.setTitle("查询所有用户");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		/* 添加模型 */
		if (EventName.equals("insertXml")) {
			new InsertXml();
		}

		/* 查询所有模型 */
		if (EventName.equals("queryXml")) {
			QueryXml jfInternalFrame = new QueryXml();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.setTitle("查询所有模型");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		
		/* 查询模型详情 */
		if (EventName.equals("viewDetailsXml")) {
			ViewPlace IFramePlace = new ViewPlace();
			ViewTransition IFrameTransition = new ViewTransition();
			ViewArc IFrameArc = new ViewArc();
			ViewArcpath IFrameArcpath = new ViewArcpath();
			
			IFramePlace.setBounds(5, 0, 650, 260);
			JDeskTop.add(IFramePlace);
			IFramePlace.setTitle("查询palce节点");
			
			IFrameTransition.setBounds(660, 0, 650, 260);
			JDeskTop.add(IFrameTransition);
			IFrameTransition.setTitle("查询transition节点");
			
			IFrameArc.setBounds(5, 265, 650, 260);
			JDeskTop.add(IFrameArc);
			IFrameArc.setTitle("查询arc节点");
			
			IFrameArcpath.setBounds(660, 265, 650, 260);
			JDeskTop.add(IFrameArcpath);
			IFrameArcpath.setTitle("查询arcpath");
		}
		
		/* 删除选中模型 */
		if (EventName.equals("deleteXml")) {
			int result = JOptionPane.showOptionDialog(null, "是否删除此条记录?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.NO_OPTION)
				return;
			String sqlDel = "Update net set isDel = 1,destroyer='"
					+ LoginSystem.user.getText().trim() + "',destroyTime='"
					+ df.format(new Date()) + "' where id = '"
					+ QueryXml.ID + "'";
			JdbcAdapter jdbcAdapter = new JdbcAdapter();
			if (jdbcAdapter.DeleteObject(sqlDel)) {
				QueryXml.jtf1.setText("");
				QueryXml.jtf2.setText("");
				QueryXml.jtf3.setText("");
				QueryXml.jtf4.setText("");
				QueryXml.jtf5.setText("");
				QueryXml.jtf6.setText("");
				
				String sqldelete = "select id,netId,type,creater,createTime,version from net where isDel = 0;";
				QueryXml.buildTable(sqldelete);
			}
		}

		/* 帮助 */
		if (EventName.equals("help")) {
			try {
				Desktop.getDesktop().open(new File("E:\\毕业论文.doc"));
			} catch (Exception help) {
				help.printStackTrace();
			}
		}
	}
}
