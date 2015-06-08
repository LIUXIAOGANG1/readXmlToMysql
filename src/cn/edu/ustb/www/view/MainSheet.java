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
	MenuBarEvent MenuBarEvent = new MenuBarEvent();// �Զ����¼��ദ��
	JMenuBar jmenubar = new JMenuBar();
	JToolBar jtoolbar = new JToolBar();


	/* ���� */
	JTextArea jtatime = new JTextArea();

	JPanel jpl = new JPanel();
	JPanel jplWEST = new JPanel();
	FlowLayout flowLayout = new FlowLayout();
	Border b1 = BorderFactory.createLineBorder(Color.gray);// �߱߿�
	Border b2 = BorderFactory.createRaisedBevelBorder();// ͹�߿�
	Border b3 = BorderFactory.createLoweredBevelBorder();// ���߿�
	Border b4 = BorderFactory.createEtchedBorder(); // ��ʴ�߿�

	/* �����ܲ��� */
	public MainSheet() {
		try {
			frame.setTitle("�ƴ����޹�˾��Ϣ����ϵͳ");
			contentPane.setLayout(new BorderLayout());
			contentPane.add(desktop, BorderLayout.CENTER);
			contentPane.add(jtoolbar, BorderLayout.NORTH);
			jpl.setLayout(new BorderLayout());
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPane.add(jpl, BorderLayout.SOUTH);
			jpl.setBorder(BorderFactory.createCompoundBorder(b1, b2));

			/* ����ʱ�� */
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
			jtoolbar.setFloatable(false);// ����jtoolbar���ɸ���
			frame.setJMenuBar(jmenubar);
			BuildMenuBar();

			/* ���õ�ǰ����Ա */
			String username = LoginSystem.user.getText().trim();
			// jtfadmin.setFont(new Font("����",Font.BOLD,15));
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
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// Ĭ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* ����ͼƬ */
	protected void loadBackgroundImage() {
		ImageIcon icon = new ImageIcon("D:\\workspace\\petriNet\\image\\2.jpg");
		JLabel jlb = new JLabel(icon);
		jlb.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		desktop.add(jlb, new Integer(Integer.MIN_VALUE));
	}

	/* ���ֲ˵��� */
	private void BuildMenuBar() {
		JMenu[] jMenu = { new JMenu("��ϵͳ����"), new JMenu("���û���Ϣ����"), new JMenu("��petri Net����"), new JMenu("��������") };
		/* ���ò˵���ĿJMenuItemԪ�� */
		JMenuItem[] jMenuItem0 = { new JMenuItem("����������Ա��"), new JMenuItem("���ϻ���־��"), new JMenuItem("���˳���") };
		String[] jMenuItem0Name = { "changeUser", "LoginRecord", "exitSys" };
		
		JMenuItem[] jMenuItem1 = { new JMenuItem("���޸ĸ�����Ϣ��"), new JMenuItem("���鿴�û���Ϣ��") };
		String[] jMenuItem1Name = { "updateMyself", "queryUser" };
		
        JMenuItem[] jMenuItem2 = { new JMenuItem("�����ģ�͡�"), new JMenuItem("���鿴ģ�͡�")};
        String[] jMenuItem2Name = { "insertXml","queryXml" };
		
		JMenuItem[] jMenuItem3 = {new JMenuItem("���������ǡ�")};
		String[] jMenuItem3Name = {"help"};
		
		/* ���ò˵����˵��������� */
		Font MenuItemFont = new Font("����", 0, 15);

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

	/* ���ֹ����� */
	private void BuildToolBar() {
		String ImageName[] = { "��������Ա.gif", "����û�.gif", "���ģ��.GIF", "��ѯ�û�.GIF",
				"ϵͳ�˳�.GIF" };

		String TipString[] = { "��������Ա", "����û�", "���ģ��", "��ѯ�û�", "ϵͳ�˳�" };

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
