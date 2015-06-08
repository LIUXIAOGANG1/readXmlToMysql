package cn.edu.ustb.www.view;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.border.Border;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.util.MenuBarEvent;

public class MainSheet {
	public static JFrame frame = new JFrame();
	Container contentPane = frame.getContentPane();
	JDesktopPane desktop = new JDesktopPane();
	MenuBarEvent MenuBarEvent = new MenuBarEvent();// 自定义事件类处理
	JMenuBar jmenubar = new JMenuBar();
	JToolBar jtoolbar = new JToolBar();


	/* 日期 */
	JTextArea jtatime = new JTextArea();

	JPanel jpl = new JPanel();
	JPanel jplWEST = new JPanel();
	FlowLayout flowLayout = new FlowLayout();
	Border b1 = BorderFactory.createLineBorder(Color.gray);// 线边库
	Border b2 = BorderFactory.createRaisedBevelBorder();// 凸边框
	Border b3 = BorderFactory.createLoweredBevelBorder();// 凹边框
	Border b4 = BorderFactory.createEtchedBorder(); // 腐蚀边框

	/* 界面总布局 */
	public MainSheet() {
		try {
			frame.setTitle("科达有限公司信息管理系统");
			contentPane.setLayout(new BorderLayout());
			contentPane.add(desktop, BorderLayout.CENTER);
			contentPane.add(jtoolbar, BorderLayout.NORTH);
			jpl.setLayout(new BorderLayout());
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPane.add(jpl, BorderLayout.SOUTH);
			jpl.setBorder(BorderFactory.createCompoundBorder(b1, b2));

			/* 设置时间 */
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
			String time = year + " / " + month + " / " + day;
			String timeday = hour + " : " + minute;

			jtatime.setText("    " + timeday);
			jtatime.append("\n" + time + "    ");
			jtatime.setEditable(false);
			jpl.add(jtatime, BorderLayout.EAST);

			desktop.setBorder(BorderFactory.createCompoundBorder(b1, b3));
			jtoolbar.setBorder(BorderFactory.createCompoundBorder(b1, b2));
			jmenubar.setBorder(BorderFactory.createLineBorder(Color.gray));
			MenuBarEvent.setDeskTop(desktop);// ??????????????????????????????????
			jtoolbar.setFloatable(false);// 设置jtoolbar不可浮动
			frame.setJMenuBar(jmenubar);
			BuildMenuBar();

			/* 设置当前操作员 */
			String username = LoginSystem.user.getText().trim();
			// jtfadmin.setFont(new Font("行书",Font.BOLD,15));
			// jtfadmin.setText(username);
			// jtfadmin.setHorizontalAlignment(JTextField.CENTER);
			jpl.add(jplWEST, BorderLayout.WEST);
			jplWEST.setLayout(flowLayout);
			// jplWEST.add(jlbadmin);
			// jplWEST.add(jtfadmin);
			// jtfadmin.setEditable(false);

			BuildToolBar();
			loadBackgroundImage();
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// 默认最大化
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* 背景图片 */
	protected void loadBackgroundImage() {
		ImageIcon icon = new ImageIcon("D:\\workspace\\petriNet\\image\\2.jpg");
		JLabel jlb = new JLabel(icon);
		jlb.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		desktop.add(jlb, new Integer(Integer.MIN_VALUE));
	}

	/* 布局菜单栏 */
	private void BuildMenuBar() {
		JMenu[] jMenu = { new JMenu("【系统管理】"), new JMenu("【用户信息管理】"), new JMenu("【petri Net管理】"), new JMenu("【帮助】") };
		/* 设置菜单相目JMenuItem元素 */
		JMenuItem[] jMenuItem0 = { new JMenuItem("【更换操作员】"), new JMenuItem("【上机日志】"), new JMenuItem("【退出】") };
		String[] jMenuItem0Name = { "changeUser", "LoginRecord", "exitSys" };
		
		JMenuItem[] jMenuItem1 = { new JMenuItem("【修改个人信息】"), new JMenuItem("【查看用户信息】") };
		String[] jMenuItem1Name = { "updateMyself", "queryUser" };
		
        JMenuItem[] jMenuItem2 = { new JMenuItem("【添加模型】"), new JMenuItem("【查看模型】")};
        String[] jMenuItem2Name = { "insertXml","queryXml" };
		
		JMenuItem[] jMenuItem3 = {new JMenuItem("【关于我们】")};
		String[] jMenuItem3Name = {"help"};
		
		/* 设置菜单、菜单项字体风格 */
		Font MenuItemFont = new Font("宋体", 0, 15);

		for (int i = 0; i < jMenu.length; i++) {
			jMenu[i].setFont(MenuItemFont);
			jmenubar.add(jMenu[i]);
		}

		for (int j = 0; j < jMenuItem0.length; j++) {
			jMenuItem0[j].setFont(MenuItemFont);
			final String EventName0 = jMenuItem0Name[j];
			jMenuItem0[j].addActionListener(MenuBarEvent);
			jMenuItem0[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuBarEvent.setEventName(EventName0);
				}
			});
			jMenu[0].add(jMenuItem0[j]);
			if (j == 1) {
				jMenu[0].addSeparator();
			}
		}

		for (int j = 0; j < jMenuItem1.length; j++) {
			jMenuItem1[j].setFont(MenuItemFont);
			final String EventName1 = jMenuItem1Name[j];
			jMenuItem1[j].addActionListener(MenuBarEvent);
			jMenuItem1[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuBarEvent.setEventName(EventName1);
				}
			});
			jMenu[1].add(jMenuItem1[j]);
		}
		
        for (int j = 0; j < jMenuItem2.length; j++) 
        {
            jMenuItem2[j].setFont(MenuItemFont);
            final String EventName2 = jMenuItem2Name[j];
            jMenuItem2[j].addActionListener(MenuBarEvent);
            jMenuItem2[j].addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    MenuBarEvent.setEventName(EventName2);
                }
            });
            jMenu[2].add(jMenuItem2[j]);
        }

		for (int j = 0; j < jMenuItem3.length; j++) {
			jMenuItem3[j].setFont(MenuItemFont);
			final String EventName3 = jMenuItem3Name[j];
			jMenuItem3[j].addActionListener(MenuBarEvent);
			jMenuItem3[j].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuBarEvent.setEventName(EventName3);
				}
			});
			jMenu[3].add(jMenuItem3[j]);
		}
	}

	/* 布局工具栏 */
	private void BuildToolBar() {
		String ImageName[] = { "更换操作员.gif", "添加用户.gif", "添加模型.GIF", "查询用户.GIF",
				"系统退出.GIF" };

		String TipString[] = { "更换操作员", "添加用户", "添加模型", "查询用户", "系统退出" };

		final String EventNames[] = { "changeUser", "insertUser",
				"insertXml", "queryUser", "exitSys" };
		
		for (int i = 0;i < EventNames.length; i++) {
			JButton jb = new JButton();
			ImageIcon image = new ImageIcon("D:\\workspace\\petriNet\\image\\"
					+ ImageName[i]);
			jb.setIcon(image);
			jb.setToolTipText(TipString[i]);
			jb.addActionListener(MenuBarEvent);
			final String eventName = EventNames[i];
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MenuBarEvent.setEventName(eventName);
				}
			});
			jtoolbar.add(jb);
		}
	}
}
