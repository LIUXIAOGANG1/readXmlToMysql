package cn.edu.ustb.www.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultDesktopManager;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.util.JdbcAdapter;
import cn.edu.ustb.www.util.MenuBarEvent;
import cn.edu.ustb.www.util.RetrieveObject;

public class QueryXml extends JInternalFrame {
	private static final long serialVersionUID = -331756697340327721L;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	MenuBarEvent MenuBarEvent = new MenuBarEvent();// 自定义事件类处理
	public static String ID = null;

	/* 声明并初始化主界面面板 */
	JSplitPane jsplitPane = new JSplitPane();
	JScrollPane jscrollPane = new JScrollPane();
	JPanel jpl1 = new JPanel();
	JPanel jpl2 = new JPanel();
	/* 声明并初始化数据表 */
	static JTable jtb = new JTable();
	/* 声明并初始化选中表时读写的文本框 */
	public static JTextField jtf1 = new JTextField();
	public static JTextField jtf2 = new JTextField();
	public static JTextField jtf3 = new JTextField();
	public static JTextField jtf4 = new JTextField();
	public static JTextField jtf5 = new JTextField();
	public static JTextField jtf6 = new JTextField();
	/* 声明并初始化部门组合框 */
	/* 声明并初始化按钮 */
	JButton jbtInsert = new JButton("增加");
	JButton jbtDelete = new JButton("删除");
	JButton jbtSave = new JButton("保存");
	JButton jbtExit = new JButton("退出");

	public QueryXml() {
		/* 声明并初始化JInternalFrame */
		super("查询所有模型", true, true, true, true);// 可以最大最小化
		/* 按钮添加打击时间 */
		jbtExit.addActionListener(new jbtExit_actionAdapter(this));
		jtb.addMouseListener(new jtb_mouseAdapter(this));
		jbtDelete.addActionListener(new jbtDelete_actionAdapter(this));
		jbtSave.addActionListener(new jbtSave_actionAdapter(this));
		jbtInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InsertXml();// 添加模板对话框
			}
		});

		try {
			LayOut();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* 设置布局 */
	private void LayOut() throws Exception {
		String sql = "select id,netId,type,creater,createTime,version from net where isDel = 0;";
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
		jsplitPane.setDividerLocation(520);// 设置分隔条位置

		setSize(420, 340);
		this.setClosable(true);
		setVisible(true);
	}

	/* 创建数据表函数 */
	public static void buildTable(String str) {
		DefaultTableModel tablemodel = null;
		String[] name = { "I D", "netId", "type", "创建人", "创建时间", "版本号" };
		RetrieveObject bdt = new RetrieveObject();
		tablemodel = bdt.getTableModel(name, str);
		jtb.setModel(tablemodel);
		jtb.setRowHeight(24);
	}

	/* 退出按钮 */
	class jbtExit_actionAdapter implements ActionListener {
		private QueryXml adaptee;

		jbtExit_actionAdapter(QueryXml adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtExit_actionPerformed(e);
		}
	}

	public void jbtExit_actionPerformed(ActionEvent e) {
		DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "是否退出PetriNet查询?",
				"系统提示", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "是", "否" },
				"否");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}
	}

	/* 删除按钮 */
	class jbtDelete_actionAdapter implements ActionListener {
		private QueryXml adaptee;

		jbtDelete_actionAdapter(QueryXml adaptee) {
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
			int result = JOptionPane.showOptionDialog(null, "是否删除此条记录?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.NO_OPTION)
				return;
			String sqlDel = "Update net set isDel = 1,destroyer='"
					+ LoginSystem.user.getText().trim() + "',destroyTime='"
					+ df.format(new Date()) + "' where id = '"
					+ QueryXml.jtf1.getText().trim() + "'";
			JdbcAdapter jdbcAdapter = new JdbcAdapter();
			if (jdbcAdapter.DeleteObject(sqlDel)) {
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");

				String sqldelete = "select id,netId,type,creater,createTime,version from net where isDel = 0;";
				buildTable(sqldelete);
			}
		}
	}

	/* 表侦听 */

	class jtb_mouseAdapter extends MouseAdapter {
		private QueryXml adaptee;

		jtb_mouseAdapter(QueryXml adaptee) {
			this.adaptee = adaptee;
		}

		public void mouseClicked(MouseEvent e) {
			adaptee.jtb_mouseClicked(e);
		}
	}

	public void jtb_mouseClicked(MouseEvent e) {
		// 判断选中行
		makeSelected(e);

		// insertflag = false;

		String sqlStr = null;
		int selectrow = 0;
		selectrow = jtb.getSelectedRow();// 返回第一个 选定行 的索引；如果没有选定的行，则返回 -1。
		if (selectrow < 0)
			return;

		ID = jtb.getValueAt(selectrow, 0).toString();
		// getValueAt(int row,int column):返回 row和column 位置的单元格值。
		/*
		 * 注：列是以表视图的显示顺序，而不是以 TableModel 的列顺序指定的。这是一项重要的区别，
		 * 因为在用户重新安排表中的列时，视图中给定索引处的列将更改。同时用户的操作不会影响模型的列顺序。
		 */

		// 添加鼠标右键事件
		if (e.getButton() == MouseEvent.BUTTON3) {
			JPopupMenu popupMenu = new JPopupMenu(); // 实例化弹出菜单
			ButtonGroup colorGroup = new ButtonGroup(); // 实例化按钮组
			String[] str = { "查看", "刷新", "删除", "复制" }; // 菜单项名称
			final String EventNames[] = { "viewDetailsXml", "reloadXml",
					"deleteXml", "copeXml" };
			int optionLength = str.length;
			JRadioButtonMenuItem items[] = new JRadioButtonMenuItem[optionLength]; // 初始化数组
			for (int i = 0; i < optionLength; i++) {
				items[i] = new JRadioButtonMenuItem(str[i]); // 实例化菜单项
				popupMenu.add(items[i]); // 增加菜单项到菜单上
				colorGroup.add(items[i]); // 增加菜单项到按钮组
				items[i].addActionListener(MenuBarEvent); // 菜单项事件处理
				final String eventName = EventNames[i];
				items[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MenuBarEvent.setEventName(eventName);
					}
				});
			}

			popupMenu.show(e.getComponent(), e.getX(), e.getY()); // 显示菜单
		}

		// 填写表最下端的文本框
		sqlStr = "SELECT netId,type,creater,createTime,version from net where isDel = 0 and id = '"
				+ ID + "'";

		Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jtf1.setText(ID);
		jtf1.setEditable(false);
		jtf2.setText(String.valueOf(vdata.get(0)));
		jtf3.setText(String.valueOf(vdata.get(1)));
		jtf4.setText(String.valueOf(vdata.get(2)));
		jtf5.setText(String.valueOf(vdata.get(3)));
		jtf6.setText(String.valueOf(vdata.get(4)));

	}

	/* 保存按钮 */
	class jbtSave_actionAdapter implements ActionListener {
		private QueryXml adaptee;

		jbtSave_actionAdapter(QueryXml adaptee) {
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
			int result = JOptionPane.showOptionDialog(null, "是否保存修改信息?",
					"系统提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "是", "否" }, "否");
			if (result == JOptionPane.NO_OPTION)
				return;
			else {
				String sql = "Update net set netId ='"
						+ QueryXml.jtf2.getText().trim() + "',type='"
						+ QueryXml.jtf3.getText().trim() + "',creater='"
						+ QueryXml.jtf4.getText().trim() + "',createTime='"
						+ QueryXml.jtf5.getText().trim() + "',version='"
						+ QueryXml.jtf6.getText().trim() + "' where id = '"
						+ QueryXml.jtf1.getText().trim() + "'";
				JdbcAdapter jdbcAdapter = new JdbcAdapter();
				jdbcAdapter.infoStr = "更新";
				jdbcAdapter.AdapterObject(sql);
				jtf1.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jtf4.setText("");
				jtf5.setText("");
				jtf6.setText("");

				String sqlsave = "select id,netId,type,creater,createTime,version from net where isDel = 0;";
				buildTable(sqlsave);
			}
		}
	}

	private void makeSelected(MouseEvent e) {
		JTable table = (JTable) e.getComponent();
		// 获取鼠标右键选中的行
		int row = table.rowAtPoint(e.getPoint());
		if (row == -1) {
			return;
		}
		// 获取已选中的行
		int[] rows = table.getSelectedRows();
		boolean inSelected = false;
		// 判断当前右键所在行是否已选中
		for (int r : rows) {
			if (row == r) {
				inSelected = true;
				break;
			}
		}
		// 当前鼠标右键点击所在行不被选中则高亮显示选中行
		if (!inSelected) {
			table.setRowSelectionInterval(row, row);
		}
	}
}
