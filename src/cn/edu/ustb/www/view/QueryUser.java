package cn.edu.ustb.www.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.util.DataBaseConnection;
import cn.edu.ustb.www.util.JdbcAdapter;
import cn.edu.ustb.www.util.RetrieveObject;

public class QueryUser extends JInternalFrame // implements ActionListener
{
	private static final long serialVersionUID = -331756697340327721L;
	/* 声明并初始化主界面面板 */
	JSplitPane jsplitPane = new JSplitPane();
	JScrollPane jscrollPane = new JScrollPane();
	JPanel jpl1 = new JPanel();
	JPanel jpl2 = new JPanel();
	/* 声明并初始化数据表 */
	static JTable jtb = new JTable();
	// JLabel jlb14=new JLabel("选择部门 :",JLabel.CENTER);
	/* 声明并初始化选中表时读写的文本框 */
	static JTextField jtf1 = new JTextField();
	static JTextField jtf2 = new JTextField();
	static JTextField jtf3 = new JTextField();
	static JTextField jtf4 = new JTextField();
	static JTextField jtf5 = new JTextField();
	static JTextField jtf6 = new JTextField();
	static JTextField jtf7 = new JTextField();
	static JTextField jtf8 = new JTextField();
	static JTextField jtf9 = new JTextField();
	static JTextField jtf10 = new JTextField();
	static JTextField jtf11 = new JTextField();
	static JTextField jtf12 = new JTextField();
	static JTextField jtf13 = new JTextField();
	/* 声明并初始化部门组合框 */
	/* 声明并初始化按钮 */
	JButton jbtInsert = new JButton("增加");
	JButton jbtDelete = new JButton("删除");
	JButton jbtSave = new JButton("保存");
	JButton jbtExit = new JButton("退出");

	public QueryUser() {
		/* 声明并初始化JInternalFrame */
		super("查询所有用户", true, true, true, true);// 可以最大最小化
		/* 按钮添加打击时间 */
		jbtExit.addActionListener(new jbtExit_actionAdapter(this));
		jbtDelete.addActionListener(new jbtDelete_actionAdapter(
				this));
		jbtSave.addActionListener(new jbtSave_actionAdapter(this));
		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InsertUser();// 添加成员对话框
			}
		});

		jtb.addMouseListener(new jtb_mouseAdapter(this));

		try {
			LayOut();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* 设置布局 */
	private void LayOut() throws Exception {
		String sql = "select * from LoginSystem;";
		buildTable(sql);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jpl1, BorderLayout.NORTH);
		FlowLayout flowLayout = new FlowLayout();
		jpl1.setLayout(flowLayout);
		flowLayout.setAlignment(FlowLayout.RIGHT);// 设置布局的对其方式
		jpl1.add(jbtInsert);
		jpl1.add(jbtDelete);
		jpl1.add(jbtSave);
		jpl1.add(jbtExit);
		jpl2.setLayout(new GridLayout(1, 13));
		this.getContentPane().add(jsplitPane, BorderLayout.CENTER);
		jsplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jsplitPane.add(jscrollPane, JSplitPane.TOP);
		jscrollPane.getViewport().add(jtb);// 设置为视图中心区
		// jtb.setPreferredSize(new Dimension(3000,1000));
		// 设置让jtb足够大以至于能显示滚动条
		jsplitPane.add(jpl2, JSplitPane.BOTTOM);
		jpl2.add(jtf1);
		jpl2.add(jtf2);
		jpl2.add(jtf3);
		jpl2.add(jtf4);
		jpl2.add(jtf5);
		jpl2.add(jtf6);
		jpl2.add(jtf7);
		jpl2.add(jtf8);
		jpl2.add(jtf9);
		jpl2.add(jtf10);
		jpl2.add(jtf11);
		jpl2.add(jtf12);
		jpl2.add(jtf13);
		jsplitPane.setDividerLocation(520);// 设置分隔条位置

		setSize(420, 340);
		this.setClosable(true);
		setVisible(true);
	}

	/* 创建数据表函数 */
	public static void buildTable(String str) {
		DefaultTableModel tablemodel = null;
		String[] name = { "I D", "姓 名", "性 别", "登录名", "密 码", "部 门", "电 话",
				"系统管理员", "财务管理员", "邮 件", "生 日", "住 址", "备 注" };
		RetrieveObject bdt = new RetrieveObject();
		tablemodel = bdt.getTableModel(name, str);
		jtb.setModel(tablemodel);
		jtb.setRowHeight(24);
	}

	/* 退出按钮 */

	class jbtExit_actionAdapter implements ActionListener {
		private QueryUser adaptee;

		jbtExit_actionAdapter(QueryUser adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtExit_actionPerformed(e);
		}
	}

	public void jbtExit_actionPerformed(ActionEvent e) {
		DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出用户查询?", "系统提示",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new String[] { "是", "否" }, "否");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}

	}

	/* 删除按钮 */

	class jbtDelete_actionAdapter implements ActionListener {
		private QueryUser adaptee;

		jbtDelete_actionAdapter(QueryUser adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtDelete_actionPerformed(e);
		}
	}

	public void jbtDelete_actionPerformed(ActionEvent e) {
		if (jtf1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "请选择删除对象！", "警告",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int result = JOptionPane.showOptionDialog(null, "是否删除用户信息?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.NO_OPTION)
				return;
			String sqlDel = "delete from LoginSystem where id = '"
					+ jtf1.getText().trim() + "'";
			JdbcAdapter jdbcAdapter = new JdbcAdapter();
			if (jdbcAdapter.DeleteObject(sqlDel)) {
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				jtf7.setText("");
				jtf8.setText("");
				jtf9.setText("");
				jtf10.setText("");
				jtf11.setText("");
				jtf12.setText("");
				jtf13.setText("");

				String sqldelete = "select * from LoginSystem;";
				buildTable(sqldelete);
			}
		}
	}

	/* 表侦听 */

	class jtb_mouseAdapter extends MouseAdapter {
		private QueryUser adaptee;

		jtb_mouseAdapter(QueryUser adaptee) {
			this.adaptee = adaptee;
		}

		public void mouseClicked(MouseEvent e) {
			adaptee.jtb_mouseClicked(e);
		}
	}

	public void jtb_mouseClicked(MouseEvent e) {
		// insertflag = false;
		String ID = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jtb.getSelectedRow();// 返回第一个 选定行 的索引；如果没有选定的行，则返回 -1。
		if (selectrow < 0)
			return;

		ID = jtb.getValueAt(selectrow, 0).toString();// getValueAt(int row,int
														// column):返回 row 和
														// column 位置的单元格值。
		/*
		 * 注：列是以表视图的显示顺序，而不是以 TableModel 的列顺序指定的。这是一项重要的区别，
		 * 因为在用户重新安排表中的列时，视图中给定索引处的列将更改。同时用户的操作不会影响模型的列顺序。
		 */

		sqlStr = "SELECT * from LoginSystem" + " where id = '" + ID + "'";

		Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jtf1.setText(vdata.get(0).toString());
		jtf1.setEditable(false);
		jtf2.setText(vdata.get(1).toString());
		jtf3.setText(vdata.get(2).toString());
		jtf4.setText(vdata.get(3).toString());
		jtf5.setText(vdata.get(4).toString());
		jtf6.setText(vdata.get(5).toString());
		jtf7.setText(vdata.get(6).toString());
		jtf8.setText(vdata.get(7).toString());
		jtf9.setText(vdata.get(8).toString());
		jtf10.setText(vdata.get(9).toString());
		jtf11.setText(vdata.get(10).toString());
		jtf12.setText(vdata.get(11).toString());
		jtf13.setText(vdata.get(12).toString());

	}

	/* 保存按钮 */
	class jbtSave_actionAdapter implements ActionListener {
		private QueryUser adaptee;

		jbtSave_actionAdapter(QueryUser adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {

			adaptee.jbtSave_actionPerformed(e);
		}
	}

	public void jbtSave_actionPerformed(ActionEvent e) {
		if (jtf1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "无数据可保存！", "警告",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int result = JOptionPane.showOptionDialog(null, "是否保存用户信息?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.NO_OPTION)
				return;
			else {
				String sql = "Update LoginSystem set name ='"
						+ QueryUser.jtf2.getText().trim() + "',sex='"
						+ QueryUser.jtf3.getText().trim() + "',username='"
						+ QueryUser.jtf4.getText().trim() + "',password='"
						+ QueryUser.jtf5.getText().trim() + "',Department='"
						+ QueryUser.jtf6.getText().trim() + "',telephone='"
						+ QueryUser.jtf7.getText().trim()
						+ "',IsSystemManager='"
						+ QueryUser.jtf8.getText().trim()
						+ "',IsFinancialManager='"
						+ QueryUser.jtf9.getText().trim() + "',EmailAddress='"
						+ QueryUser.jtf10.getText().trim() + "',Birthday='"
						+ QueryUser.jtf11.getText().trim() + "',HomeAddress='"
						+ QueryUser.jtf12.getText().trim() + "',Remark='"
						+ QueryUser.jtf13.getText().trim() + "' where id = '"
						+ QueryUser.jtf1.getText().trim() + "'";
				JdbcAdapter jdbcAdapter = new JdbcAdapter();
				jdbcAdapter.infoStr = "更新";
				jdbcAdapter.AdapterObject(sql);
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");
				jtf7.setText("");
				jtf8.setText("");
				jtf9.setText("");
				jtf10.setText("");
				jtf11.setText("");
				jtf12.setText("");
				jtf13.setText("");

				String sqlsave = "select * from LoginSystem;";
				buildTable(sqlsave);
			}
		}
	}
}
