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
import cn.edu.ustb.www.util.JdbcAdapter;
import cn.edu.ustb.www.util.RetrieveObject;

public class ViewPlace extends JInternalFrame {
	private static final long serialVersionUID = -331756697340327721L;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/* ��������ʼ����������� */
	JSplitPane jsplitPane = new JSplitPane();
	JScrollPane jscrollPaneNet = new JScrollPane();
	JPanel jpl1 = new JPanel();
	JPanel jpl2 = new JPanel();
	/* ��������ʼ�����ݱ� */
	static JTable jtb = new JTable();
	/* ��������ʼ��ѡ�б�ʱ��д���ı��� */
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
	/* ��������ʼ��������Ͽ� */
	/* ��������ʼ����ť */
//	JButton jbtInsert = new JButton("����");
	JButton jbtDelete = new JButton("ɾ��");
	JButton jbtSave = new JButton("����");
	JButton jbtExit = new JButton("�˳�");

	public ViewPlace() {
		/* ��������ʼ��JInternalFrame */
		super("��ѯplace�ڵ�", true, true, true, true);// ���������С��
		/* ��ť��Ӵ���¼� */
		jbtExit.addActionListener(new jbtExit_actionAdapter(this));
		jtb.addMouseListener(new jtb_mouseAdapter(this));
		jbtDelete.addActionListener(new jbtDelete_actionAdapter(this));
		jbtSave.addActionListener(new jbtSave_actionAdapter(this));
//		jbtInsert.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new InsertXml();// ���ģ��Ի���
//			}
//		});

		try {
			LayOut();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* ���ò��� */
	private void LayOut() throws Exception {
		String sql = "select id, netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, initMarkValue, initMarkOffsetX, initMarkOffsetY, capacity "
				+ "from place where isDel = 0 and netId = " + QueryXml.ID;

		buildTable(sql);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jpl1, BorderLayout.NORTH);
		FlowLayout flowLayout = new FlowLayout();
		jpl1.setLayout(flowLayout);
		flowLayout.setAlignment(FlowLayout.RIGHT);// ���ò��ֵĶ��䷽ʽ
//		jpl1.add(jbtInsert);
		jpl1.add(jbtDelete);
		jpl1.add(jbtSave);
		jpl1.add(jbtExit);
		jpl2.setLayout(new GridLayout(1, 13));
		this.getContentPane().add(jsplitPane, BorderLayout.CENTER);
		jsplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jsplitPane.add(jscrollPaneNet, JSplitPane.TOP);
		jscrollPaneNet.getViewport().add(jtb);// ����Ϊ��ͼ������
		// jtb.setPreferredSize(new Dimension(3000,1000));
		// ������jtb�㹻������������ʾ������
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
		jsplitPane.setDividerLocation(520);// ���÷ָ���λ��

		setSize(420, 340);
		this.setClosable(true);
		setVisible(true);
	}

	/* �������ݱ��� */
	public static void buildTable(String str) {
		DefaultTableModel tablemodel = null;
		String[] name = { "I D", "netId", "positionX", "positionY",
				"nameValue", "nameOffsetX", "nameOffsetY", "initMarkValue",
				"initMarkOffsetX", "initMarkOffsetY", "capacity" };
		RetrieveObject bdt = new RetrieveObject();
		tablemodel = bdt.getTableModel(name, str);
		jtb.setModel(tablemodel);
		jtb.setRowHeight(24);
	}

	/* �˳���ť */
	class jbtExit_actionAdapter implements ActionListener {
		private ViewPlace adaptee;

		jbtExit_actionAdapter(ViewPlace adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtExit_actionPerformed(e);
		}
	}

	public void jbtExit_actionPerformed(ActionEvent e) {
		DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳�PetriNet��ѯ?",
				"ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, new String[] { "��", "��" },
				"��");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}
	}

	/* ɾ����ť */
	class jbtDelete_actionAdapter implements ActionListener {
		private ViewPlace adaptee;

		jbtDelete_actionAdapter(ViewPlace adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtDelete_actionPerformed(e);
		}
	}

	public void jbtDelete_actionPerformed(ActionEvent e) {
		if (jtf1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "��ѡ��ɾ������", "����",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int result = JOptionPane.showOptionDialog(null, "�Ƿ�ɾ��������¼?",
					"ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "��", "��" }, "��");
			if (result == JOptionPane.NO_OPTION)
				return;
			String sqlDel = "Update place set isDel = 1,destroyer='"
					+ LoginSystem.user.getText().trim() + "',destroyTime='"
					+ df.format(new Date()) + "' where id = '"
					+ jtf1.getText().trim() + "' and netId = " + QueryXml.ID;
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

				String sqldelete = "select id, netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, initMarkValue, initMarkOffsetX, initMarkOffsetY, capacity "
						+ "from place where isDel = 0 and netId = "
						+ QueryXml.ID;
				buildTable(sqldelete);
			}
		}
	}

	/* ������ */

	class jtb_mouseAdapter extends MouseAdapter {
		private ViewPlace adaptee;

		jtb_mouseAdapter(ViewPlace adaptee) {
			this.adaptee = adaptee;
		}

		public void mouseClicked(MouseEvent e) {
			adaptee.jtb_mouseClicked(e);
		}
	}

	public void jtb_mouseClicked(MouseEvent e) {
		// �ж�ѡ����
		makeSelected(e);

		// insertflag = false;
		String ID = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jtb.getSelectedRow();// ���ص�һ�� ѡ���� �����������û��ѡ�����У��򷵻� -1��
		if (selectrow < 0)
			return;

		ID = jtb.getValueAt(selectrow, 0).toString();
		// getValueAt(int row,int column):���� row��column λ�õĵ�Ԫ��ֵ��
		/*
		 * ע�������Ա���ͼ����ʾ˳�򣬶������� TableModel ����˳��ָ���ġ�����һ����Ҫ������
		 * ��Ϊ���û����°��ű��е���ʱ����ͼ�и������������н����ġ�ͬʱ�û��Ĳ�������Ӱ��ģ�͵���˳��
		 */

		// ��д�����¶˵��ı���
		sqlStr = "select netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, initMarkValue, initMarkOffsetX, initMarkOffsetY, capacity "
				+ "from place where isDel = 0 and id = '" + ID + "'";

		Vector vdata = null;
		RetrieveObject retrive = new RetrieveObject();
		vdata = retrive.getObjectRow(sqlStr);

		jtf1.setText(ID);
		jtf1.setEditable(false);
		jtf2.setText(String.valueOf(vdata.get(0)));
		jtf2.setEditable(false);
		jtf3.setText(String.valueOf(vdata.get(1)));
		jtf4.setText(String.valueOf(vdata.get(2)));
		jtf5.setText(String.valueOf(vdata.get(3)));
		jtf6.setText(String.valueOf(vdata.get(4)));
		jtf7.setText(String.valueOf(vdata.get(5)));
		jtf8.setText(String.valueOf(vdata.get(6)));
		jtf9.setText(String.valueOf(vdata.get(7)));
		jtf10.setText(String.valueOf(vdata.get(8)));
		jtf11.setText(String.valueOf(vdata.get(9)));

	}

	/* ���水ť */
	class jbtSave_actionAdapter implements ActionListener {
		private ViewPlace adaptee;

		jbtSave_actionAdapter(ViewPlace adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {

			adaptee.jbtSave_actionPerformed(e);
		}
	}

	public void jbtSave_actionPerformed(ActionEvent e) {
		if (jtf1.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "�����ݿɱ��棡", "����",
					JOptionPane.WARNING_MESSAGE);
		} else {
			int result = JOptionPane.showOptionDialog(null, "�Ƿ񱣴��޸���Ϣ?",
					"ϵͳ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null,
					new String[] { "��", "��" }, "��");
			if (result == JOptionPane.NO_OPTION)
				return;
			else {
				String sql = "Update place set positionX = '"
						+ jtf3.getText().trim() + "',positionY = '"
						+ jtf4.getText().trim() + "',nameValue = '"
						+ jtf5.getText().trim() + "',nameOffsetX = '"
						+ jtf6.getText().trim() + "', nameOffsetY = '"
						+ jtf7.getText().trim() + "',initMarkValue = '"
						+ jtf8.getText().trim() + "',initMarkOffsetX = '"
						+ jtf9.getText().trim() + "',initMarkOffsetY = '"
						+ jtf10.getText().trim() + "', capacity = '"
						+ jtf11.getText().trim() + "' where id = '"
						+ jtf1.getText().trim() + "' and netId = "
						+ QueryXml.ID;

				JdbcAdapter jdbcAdapter = new JdbcAdapter();
				jdbcAdapter.infoStr = "����";
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

				String sqlsave = "select id, netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, initMarkValue, initMarkOffsetX, initMarkOffsetY, capacity "
						+ "from place where isDel = 0 and netId = "
						+ QueryXml.ID;
				buildTable(sqlsave);
			}
		}
	}

	private void makeSelected(MouseEvent e) {
		JTable table = (JTable) e.getComponent();
		// ��ȡ����Ҽ�ѡ�е���
		int row = table.rowAtPoint(e.getPoint());
		if (row == -1) {
			return;
		}
		// ��ȡ��ѡ�е���
		int[] rows = table.getSelectedRows();
		boolean inSelected = false;
		// �жϵ�ǰ�Ҽ��������Ƿ���ѡ��
		for (int r : rows) {
			if (row == r) {
				inSelected = true;
				break;
			}
		}
		// ��ǰ����Ҽ���������в���ѡ���������ʾѡ����
		if (!inSelected) {
			table.setRowSelectionInterval(row, row);
		}
	}
}
