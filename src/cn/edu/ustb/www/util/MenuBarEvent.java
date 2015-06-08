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
		/* �˳�ϵͳ */
		if (EventName.equals("exitSys")) {
			int result = JOptionPane.showOptionDialog(null, "�Ƿ�����˳���˾��Ϣ����ϵͳ?",
					"ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "��", "��" }, "��");
			if (result == JOptionPane.YES_OPTION) {
				try {
					/* �������ڵ���һ�ַ��� */

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
					String Strsql = "insert into LoginRecord(�������,�����·�,��������,����ʱ��,������,����״̬) "
							+ "values ('"
							+ year
							+ "','"
							+ month
							+ "','"
							+ day
							+ "','"
							+ timeday
							+ "','"
							+ LoginSystem.user.getText().trim() + "','�˳�');";
					statement.executeUpdate(Strsql);

				} catch (SQLException sql) {
					sql.printStackTrace();
				}
				System.exit(0);
			}
			return;
		}

		/* ��������Ա */
		if (EventName.equals("changeUser")) {
			try {
				/* �������ڵ���һ�ַ��� */

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
				String Strsql = "insert into LoginRecord(�������,�����·�,��������,����ʱ��,������,����״̬) "
						+ "values ('"
						+ year
						+ "','"
						+ month
						+ "','"
						+ day
						+ "','"
						+ timeday
						+ "','"
						+ LoginSystem.user.getText().trim() + "','�˳�');";
				statement.executeUpdate(Strsql);
			} catch (SQLException sql) {
				sql.printStackTrace();
			}

			MainSheet.frame.dispose();
			new LoginSystem();

		}
		/* �ϻ���־ */
		if (EventName.equals("LoginRecord")) {
			LoginRecord jfInternalFrame = new LoginRecord();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.show();
			jfInternalFrame.setTitle("�ϻ���־");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		/* ���Ա�� */
		if (EventName.equals("insertUser")) {
			new InsertUser();
		}
		/* �޸ĸ�����Ϣ */
		if (EventName.equals("updateMyself")) {
			new UpdateUser();
		}
		/* ��ѯ�����û� */
		if (EventName.equals("queryUser")) {
			QueryUser jfInternalFrame = new QueryUser();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.setTitle("��ѯ�����û�");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		/* ���ģ�� */
		if (EventName.equals("insertXml")) {
			new InsertXml();
		}

		/* ��ѯ����ģ�� */
		if (EventName.equals("queryXml")) {
			QueryXml jfInternalFrame = new QueryXml();
			jfInternalFrame.setBounds(0, 0, 800, 600);
			JDeskTop.add(jfInternalFrame);
			jfInternalFrame.setTitle("��ѯ����ģ��");
			JDeskTop.getDesktopManager().activateFrame(jfInternalFrame);
		}
		
		/* ��ѯģ������ */
		if (EventName.equals("viewDetailsXml")) {
			ViewPlace IFramePlace = new ViewPlace();
			ViewTransition IFrameTransition = new ViewTransition();
			ViewArc IFrameArc = new ViewArc();
			ViewArcpath IFrameArcpath = new ViewArcpath();
			
			IFramePlace.setBounds(5, 0, 650, 260);
			JDeskTop.add(IFramePlace);
			IFramePlace.setTitle("��ѯpalce�ڵ�");
			
			IFrameTransition.setBounds(660, 0, 650, 260);
			JDeskTop.add(IFrameTransition);
			IFrameTransition.setTitle("��ѯtransition�ڵ�");
			
			IFrameArc.setBounds(5, 265, 650, 260);
			JDeskTop.add(IFrameArc);
			IFrameArc.setTitle("��ѯarc�ڵ�");
			
			IFrameArcpath.setBounds(660, 265, 650, 260);
			JDeskTop.add(IFrameArcpath);
			IFrameArcpath.setTitle("��ѯarcpath");
		}
		
		/* ɾ��ѡ��ģ�� */
		if (EventName.equals("deleteXml")) {
			int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ��������¼?",
					"ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "��", "��" }, "��");
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

		/* ���� */
		if (EventName.equals("help")) {
			try {
				Desktop.getDesktop().open(new File("E:\\��ҵ����.doc"));
			} catch (Exception help) {
				help.printStackTrace();
			}
		}
	}
}
