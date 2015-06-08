package cn.edu.ustb.www.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import cn.edu.ustb.www.util.DataBaseConnection;
import cn.edu.ustb.www.view.MainSheet;

public class LoginSystem implements ActionListener{
	JFrame frame;
	Container contentPane;
	JPanel jpl;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5;
	JButton b1, b2;
	public static JTextField user;
	public static JPasswordField password;
	static JPasswordField password1;
	Connection conn = null;

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

	/* ���̵�¼�¼� */
	public class myKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				String username = user.getText();
				String pwd = password.getText().trim();

				if (username.length() != 0) {

					try {
						conn = DataBaseConnection.getConn();
						String sql = "SELECT * FROM LoginSystem WHERE username='"
								+ username + "'";
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery(sql);
						if (rs.next()) {
							String sql1 = "SELECT * FROM LoginSystem WHERE password='"
									+ pwd + "'";
							Statement st1 = (Statement) conn.createStatement();
							ResultSet rs1 = (ResultSet) st1.executeQuery(sql1);
							if (rs1.next()) {
								new MainSheet();
								frame.dispose();

								/* �ϻ���־��¼ */
								try {
									conn = DataBaseConnection.getConn();
									Statement statement = conn
											.createStatement();
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
											+ user.getText().trim()
											+ "','����');";
									statement.execute(Strsql);
								} catch (Exception ex) {
									ex.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null, "�������",
										"����", JOptionPane.WARNING_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "û�д��˺�", "����",
									JOptionPane.WARNING_MESSAGE);
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(null, "�������˺�", "����",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO �Զ����ɵķ������

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO �Զ����ɵķ������

		}
	}

	//
	/* ���캯�����в��� */
	public LoginSystem() {
		myKeyListener mykeylistener = new myKeyListener();
		frame = new JFrame();
		frame.setTitle("�û���¼");
		frame.setUndecorated(true); // ȥ��������
		frame.setResizable(false);// ���������ô�С
		frame.getRootPane().setWindowDecorationStyle(
				JRootPane.INFORMATION_DIALOG);// ����ָ���Ĵ���װ�η��
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//Ĭ�����
		contentPane = frame.getContentPane();
		jpl = new JPanel();
		jlb1 = new JLabel();
		jlb1.setBorder(BorderFactory.createRaisedBevelBorder());// ���ñ߿���ʽ
		jlb1.setFont(new Font("Serif", Font.PLAIN, 30));
		jlb2 = new JLabel("��   �ţ�", SwingConstants.CENTER);
		jlb2.setFont(new Font("Serif", Font.PLAIN, 20));
		jlb3 = new JLabel("��   �룺", SwingConstants.CENTER);
		jlb3.setFont(new Font("Serif", Font.PLAIN, 20));

		// ��ӱ���ͼƬ
		ImageIcon i = new ImageIcon("D:\\workspace\\petriNet\\image\\1.jpg");
		jlb4 = new JLabel(i);
		jlb4.setBounds(0, 0, 580, 350);
		frame.getLayeredPane().add(jlb4, new Integer(Integer.MIN_VALUE));// JFrame�ĵڶ������ͼƬ;
		/*
		 * jlb4�����desktopPane�ı�����desktopPane.add(jlb4,new
		 * Integer(Integer.MIN_VALUE));��ʾjlb4��Զ��desktopPane������������һ�������
		 * Ҳ����˵��backLabel��Զ��desktopPane����ײ�
		 */
		JPanel panel = (JPanel) frame.getContentPane();// �Ѷ�������ת��Ϊ��壬��Ϊ����û��setOpaque��������
		panel.setOpaque(false);// ���ö�������Ϊ͸��
		jpl.setOpaque(false);// ���ö�����������ӵ������Ϊ͸��
		// ͼƬ������

		jlb5 = new JLabel("����֧�� | �����Ƽ���ѧ", JLabel.RIGHT);
		jlb5.setBorder(BorderFactory.createRaisedBevelBorder());// ���ñ߿���ʽ
		jlb5.setFont(new Font("Serif", Font.PLAIN, 20));
		b1 = new JButton("��¼");
		b2 = new JButton("�˳�");
		user = new JTextField(10);
		user.setFont(new Font("Serif", Font.PLAIN, 18));
		password = new JPasswordField(10);
		password.setFont(new Font("Serif", Font.PLAIN, 18));
		Border bd1 = BorderFactory.createRaisedBevelBorder();
		Border bd2 = BorderFactory.createLoweredBevelBorder();
		Border bd3 = BorderFactory.createEtchedBorder();
		Border bd4 = BorderFactory.createLineBorder(Color.DARK_GRAY);
		jpl.add(jlb1);
		jlb1.setBounds(0, 0, 580, 80);
		jlb1.setBorder(BorderFactory.createCompoundBorder(bd4, bd1));
		jpl.add(jlb2);
		jlb2.setBounds(170, 120, 100, 50);
		jpl.add(jlb3);
		jlb3.setBounds(170, 160, 100, 50);
		jpl.add(jlb5);
		jlb5.setBounds(0, 270, 570, 50);
		jlb5.setBorder(BorderFactory.createCompoundBorder(bd4, bd1));
		jpl.add(user);
		user.setBounds(270, 130, 120, 30);
		user.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jpl.add(password);
		password.setBounds(270, 167, 120, 30);
		password.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jpl.add(b1);
		b1.setBounds(205, 220, 65, 30);
		jpl.add(b2);
		b2.setBounds(310, 220, 65, 30);
		b1.addActionListener(this);

		user.requestFocus();// ?????????????????????????????????????????
		user.addKeyListener(mykeylistener);
		password.requestFocus();// ?????????????????????????????????????????
		password.addKeyListener(mykeylistener);

		b2.addActionListener(this);
		contentPane.add(jpl);
		jpl.setLayout(null);
		frame.setBounds(400, 200, 580, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run()// ?????????????????????????????????????�����¼�
			{
				try {
					new LoginSystem();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* ��¼�˳���ť��Ӧ */
	public void actionPerformed(ActionEvent e1) {
		String cmd = e1.getActionCommand();
		String username = user.getText();
		String pwd = password.getText().trim();
		if (cmd.equals("�˳�")) {
			System.exit(0);
		}
		if (username.length() != 0) {
			if (cmd.equals("��¼")) {
				try {
					conn = DataBaseConnection.getConn();
					String sql = "SELECT * FROM LoginSystem WHERE username='"
							+ username + "'";
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(sql);
					if (rs.next()) {
						String sql1 = "SELECT * FROM LoginSystem WHERE password='"
								+ pwd + "'";
						Statement st1 = conn.createStatement();
						ResultSet rs1 = st1.executeQuery(sql1);
						if (rs1.next()) {

							new MainSheet();
							frame.dispose();
							/* �ϻ���־��¼ */
							try {
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
										+ user.getText().trim() + "','����');";
								statement.executeUpdate(Strsql);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "�������", "����",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "û�д��˺�", "����",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "�������˺�", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
