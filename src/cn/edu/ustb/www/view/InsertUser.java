package cn.edu.ustb.www.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import cn.edu.ustb.www.util.DataBaseConnection;

import java.sql.*;

public class InsertUser extends JFrame {
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6, jtf7, jtf8, jtf10, jtf13;
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6, jlb7, jlb8, jlb9, jlb10, jlb11,
			jlb12, jlb13, jlb14;
	JButton jbt1, jbt2;
	JPanel jpl;

	Statement st;
	Connection conn;
	ResultSet rs;

	JComboBox jcombobox1 = new JComboBox();// 性别
	JComboBox jcombobox2 = new JComboBox();// 系统管理员
	JComboBox jcombobox3 = new JComboBox();// 财务管理员

	public InsertUser() {
		Container contentPane = MainSheet.frame.getContentPane();
		final JDialog jdlog = new JDialog(this, true);
		jdlog.setTitle("添加员工信息");
		jdlog.setResizable(false);

		jtf1 = new JTextField(15);
		jtf1.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf2 = new JTextField(15);
		jtf2.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf3 = new JTextField(15);
		jtf3.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf4 = new JTextField(15);
		jtf4.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf5 = new JTextField(15);
		jtf5.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf6 = new JTextField(15);
		jtf6.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf7 = new JTextField(15);
		jtf7.setFont(new Font("Serif", Font.PLAIN, 16));
		jtf8 = new JTextField(15);
		jtf8.setFont(new Font("Serif", Font.PLAIN, 16));

		jcombobox2.setFont(new Font("楷体", Font.PLAIN, 16));
		jcombobox2.addItem("否");
		jcombobox2.addItem("是");
		jcombobox2.setSelectedIndex(0);

		jtf10 = new JTextField(15);
		jtf10.setFont(new Font("Serif", Font.PLAIN, 16));

		jcombobox3.setFont(new Font("楷体", Font.PLAIN, 16));
		jcombobox3.addItem("否");
		jcombobox3.addItem("是");
		jcombobox3.setSelectedIndex(0);

		jcombobox1.setFont(new Font("楷体", Font.PLAIN, 16));
		jcombobox1.addItem("男");
		jcombobox1.addItem("女");
		jcombobox1.setSelectedIndex(0);

		jtf13 = new JTextField(15);
		jtf13.setFont(new Font("Serif", Font.PLAIN, 16));

		jlb1 = new JLabel(" * * I D ：", SwingConstants.CENTER);
		jlb1.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb2 = new JLabel("* *登录名：", SwingConstants.CENTER);
		jlb2.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb3 = new JLabel("* * 姓 名 ：", SwingConstants.CENTER);
		jlb3.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb4 = new JLabel("* * 密 码 ：", SwingConstants.CENTER);
		jlb4.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb5 = new JLabel("  邮       箱  ：", SwingConstants.CENTER);
		jlb5.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb6 = new JLabel("* * 部 门 ：", SwingConstants.CENTER);
		jlb6.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb7 = new JLabel("* * 电话  ：", SwingConstants.CENTER);
		jlb7.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb8 = new JLabel("  住        址 ：", SwingConstants.CENTER);
		jlb8.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb9 = new JLabel("*系统管理员", SwingConstants.CENTER);
		jlb9.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb10 = new JLabel(" 生      日 ：", SwingConstants.CENTER);
		jlb10.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb11 = new JLabel("*财务管理员", SwingConstants.CENTER);
		jlb11.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb12 = new JLabel("* * 性 别 ：", SwingConstants.CENTER);
		jlb12.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb13 = new JLabel("  备        注 ：", SwingConstants.CENTER);
		jlb13.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb14 = new JLabel("注意：**字段不能为空！", SwingConstants.LEFT);
		jlb14.setFont(new Font("Serif", Font.PLAIN, 16));
		jlb14.setForeground(Color.RED);
		jbt1 = new JButton("保存");
		jbt1.setFont(new Font("Serif", Font.PLAIN, 16));
		jbt2 = new JButton("退出");
		jbt2.setFont(new Font("Serif", Font.PLAIN, 16));

		Border bd1 = BorderFactory.createRaisedBevelBorder();
		Border bd2 = BorderFactory.createLoweredBevelBorder();
		Border bd3 = BorderFactory.createEtchedBorder();
		Border bd4 = BorderFactory.createLineBorder(Color.DARK_GRAY);

		jtf1.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf2.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf3.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf4.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf5.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf6.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf7.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf8.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jcombobox2.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf10.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jcombobox3.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jcombobox1.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
		jtf13.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));

		jpl = new JPanel();
		jdlog.getContentPane().add(jpl);
		jpl.setLayout(null);

		jpl.add(jlb1);
		jpl.add(jtf1);
		jpl.add(jlb2);
		jpl.add(jtf2);
		jpl.add(jlb3);
		jpl.add(jtf3);
		jpl.add(jlb4);
		jpl.add(jtf4);
		jpl.add(jlb5);
		jpl.add(jtf5);
		jpl.add(jlb6);
		jpl.add(jtf6);
		jpl.add(jlb7);
		jpl.add(jtf7);
		jpl.add(jlb8);
		jpl.add(jtf8);
		jpl.add(jlb9);
		jpl.add(jcombobox2);
		jpl.add(jlb10);
		jpl.add(jtf10);
		jpl.add(jlb11);
		jpl.add(jcombobox3);
		jpl.add(jlb12);
		jpl.add(jcombobox1);
		jpl.add(jlb13);
		jpl.add(jtf13);
		jpl.add(jbt1);
		jpl.add(jbt2);
		jpl.add(jlb14);
		jlb1.setBounds(10, 20, 100, 30);
		jtf1.setBounds(115, 20, 100, 30);
		jlb3.setBounds(220, 20, 100, 30);
		jtf3.setBounds(325, 20, 100, 30);
		jlb12.setBounds(430, 20, 100, 30);
		jcombobox1.setBounds(535, 20, 100, 30);
		jlb2.setBounds(10, 60, 100, 30);
		jtf2.setBounds(115, 60, 100, 30);
		jlb4.setBounds(220, 60, 100, 30);
		jtf4.setBounds(325, 60, 100, 30);
		jlb6.setBounds(430, 60, 100, 30);
		jtf6.setBounds(535, 60, 100, 30);
		jlb9.setBounds(10, 100, 100, 30);
		jcombobox2.setBounds(115, 100, 100, 30);
		jlb7.setBounds(220, 100, 100, 30);
		jtf7.setBounds(325, 100, 100, 30);
		jlb5.setBounds(430, 100, 100, 30);
		jtf5.setBounds(535, 100, 100, 30);
		jlb11.setBounds(10, 140, 100, 30);
		jcombobox3.setBounds(115, 140, 100, 30);
		jlb10.setBounds(220, 140, 100, 30);
		jtf10.setBounds(325, 140, 100, 30);
		jlb8.setBounds(430, 140, 100, 30);
		jtf8.setBounds(535, 140, 100, 30);
		jlb13.setBounds(10, 180, 100, 30);
		jtf13.setBounds(115, 180, 520, 30);
		jlb14.setBounds(10, 240, 300, 30);
		jbt1.setBounds(450, 240, 70, 30);
		jbt2.setBounds(550, 240, 70, 30);

		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn = DataBaseConnection.getConn();
					st = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,
							rs.CONCUR_READ_ONLY);

					String str1 = jtf1.getText().trim();
					String str2 = jtf2.getText().trim();
					String str3 = jtf3.getText().trim();
					String str4 = jtf4.getText().trim();
					String str5 = jtf5.getText().trim();
					String str6 = jtf6.getText().trim();
					String str7 = jtf7.getText().trim();
					String str8 = jtf8.getText().trim();
					String str9 = (String) jcombobox2.getSelectedItem();
					String str10 = jtf10.getText().trim();
					String str11 = (String) jcombobox3.getSelectedItem();
					String str12 = (String) jcombobox1.getSelectedItem();
					String str13 = jtf13.getText().trim();
					try {
						String sql1 = "select * from LoginSystem where id='"
								+ str1 + "';";
						rs = st.executeQuery(sql1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if ((str1.equals("")) && (str2.equals(""))
							&& (str3.equals("")) && (str4.equals(""))
							&& (str5.equals("")) && (str6.equals(""))
							&& (str7.equals("")) && (str8.equals(""))
							&& (str9.equals("")) && (str10.equals(""))
							&& (str11.equals("")) && (str12.equals(""))) {
						JOptionPane.showMessageDialog(null, "请输入信息！", "提示对话框",
								1);
					} else if ((str1.equals("")) || (str2.equals(""))
							|| (str3.equals("")) || (str4.equals(""))
							|| (str6.equals("")) || (str7.equals(""))
							|| (str9.equals("")) || (str11.equals(""))
							|| (str12.equals(""))) {
						JOptionPane.showMessageDialog(null, "重要字段(***)不能为空！",
								"提示对话框", 1);
					} else if (rs.next()) {
						JOptionPane.showMessageDialog(null, "ID号已存在，不能相同",
								"提示对话框", 1);
					} else {
						String sql = "insert into LoginSystem(id,name,sex,username,password,Department,telephone,IsSystemManager,IsFinancialManager,EmailAddress,Birthday,HomeAddress,Remark) "
								+ "values ('"
								+ str1
								+ "','"
								+ str3
								+ "','"
								+ str12
								+ "','"
								+ str2
								+ "','"
								+ str4
								+ "','"
								+ str6
								+ "','"
								+ str7
								+ "','"
								+ str9
								+ "','"
								+ str11
								+ "','"
								+ str5
								+ "','"
								+ str10
								+ "','"
								+ str8
								+ "','"
								+ str13 + "');";
						st.executeUpdate(sql);//
						JOptionPane.showMessageDialog(null, "用户添加成功！", "提示对话框",
								1);
						jtf1.setText("");
						jtf2.setText("");
						jtf3.setText("");
						jtf4.setText("");
						jtf5.setText("");
						jtf6.setText("");
						jtf7.setText("");
						jtf8.setText("");
						jtf10.setText("");
						jtf13.setText("");

						String sqlinsert = "select * from LoginSystem ;";
						QueryUser.buildTable(sqlinsert);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		jbt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdlog.dispose();
			}
		});

		jdlog.setBounds(240, 100, 675, 350);
		jdlog.setVisible(true);
	}
}
