package cn.edu.ustb.www.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import cn.edu.ustb.www.util.RetrieveObject;

public class LoginRecord extends JInternalFrame {
	/* ��������ʼ����������� */
	JSplitPane jsplitPane = new JSplitPane();
	JScrollPane jscrollPane = new JScrollPane();
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

	/* ��������ʼ����ť */
	JButton jbtPrint = new JButton("��ӡ");
	JButton jbtExit = new JButton("�˳�");

	static String ID[] = null;// buildJcombox()���õ�

	static boolean f = true;// ��ֹ��δ�

	public LoginRecord() {
		/* ��������ʼ��JInternalFrame */
		super("�ϻ���־", true, true, true, true);// ���������С��
		/* ��ť��Ӵ��ʱ�� */
		jbtExit.addActionListener(new jbtExit_actionAdapter(this));
		jtb.addMouseListener(new jtb_mouseAdapter(this));

		try {
			LayOut();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* ���ò��� */
	private void LayOut() throws Exception {
		String sql = "select * from LoginRecord;";
		buildTable(sql);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jpl1, BorderLayout.NORTH);
		FlowLayout flowLayout = new FlowLayout();
		jpl1.setLayout(flowLayout);
		flowLayout.setAlignment(FlowLayout.RIGHT);// ���ò��ֵĶ��䷽ʽ
		jpl1.add(jbtPrint);
		jpl1.add(jbtExit);
		jpl2.setLayout(new GridLayout(1, 7));
		this.getContentPane().add(jsplitPane, BorderLayout.CENTER);
		jsplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jsplitPane.add(jscrollPane, JSplitPane.TOP);
		jscrollPane.getViewport().add(jtb);// ����Ϊ��ͼ������
		jsplitPane.add(jpl2, JSplitPane.BOTTOM);
		jpl2.add(jtf1);
		jpl2.add(jtf2);
		jpl2.add(jtf3);
		jpl2.add(jtf4);
		jpl2.add(jtf5);
		jpl2.add(jtf6);
		jpl2.add(jtf7);

		jtf1.setEditable(false);
		jtf2.setEditable(false);
		jtf3.setEditable(false);
		jtf4.setEditable(false);
		jtf5.setEditable(false);
		jtf6.setEditable(false);
		jtf7.setEditable(false);

		jsplitPane.setDividerLocation(520);// ���÷ָ���λ��

		setSize(420, 340);
		this.setClosable(true);
		setVisible(true);
	}

	/* �������ݱ��� */
	public static void buildTable(String str) {
		DefaultTableModel tablemodel = null;
		String[] name = { "��ˮ��", "�������", "�����·�", "��������", "����ʱ��", "������", "����״̬" };
		RetrieveObject bdt = new RetrieveObject();
		tablemodel = bdt.getTableModel(name, str);
		jtb.setModel(tablemodel);
		jtb.setRowHeight(24);
	}

	/* �˳���ť */

	class jbtExit_actionAdapter implements ActionListener {
		private LoginRecord adaptee;

		jbtExit_actionAdapter(LoginRecord adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.jbtExit_actionPerformed(e);
		}
	}

	public void jbtExit_actionPerformed(ActionEvent e) {
		DefaultDesktopManager manger = new DefaultDesktopManager();
		int result = JOptionPane.showOptionDialog(null, "�Ƿ��˳��ϻ���־?", "ϵͳ��ʾ",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new String[] { "��", "��" }, "��");
		if (result == JOptionPane.YES_OPTION) {
			manger.closeFrame(this);
		}
	}

	/* ������ */

	class jtb_mouseAdapter extends MouseAdapter {
		private LoginRecord adaptee;

		jtb_mouseAdapter(LoginRecord adaptee) {
			this.adaptee = adaptee;
		}

		public void mouseClicked(MouseEvent e) {
			adaptee.jtb_mouseClicked(e);
		}
	}

	public void jtb_mouseClicked(MouseEvent e) {
		String ID = null;
		String sqlStr = null;
		int selectrow = 0;
		selectrow = jtb.getSelectedRow();// ���ص�һ�� ѡ���� �����������û��ѡ�����У��򷵻� -1��
		if (selectrow < 0)
			return;

		ID = jtb.getValueAt(selectrow, 0).toString();// getValueAt(int row,int
														// column):���� row ��
														// column λ�õĵ�Ԫ��ֵ��
		/*
		 * ע�������Ա���ͼ����ʾ˳�򣬶������� TableModel ����˳��ָ���ġ�����һ����Ҫ������
		 * ��Ϊ���û����°��ű��е���ʱ����ͼ�и������������н����ġ�ͬʱ�û��Ĳ�������Ӱ��ģ�͵���˳��
		 */

		sqlStr = "SELECT * from LoginRecord" + " where ��ˮ�� = '" + ID + "'";

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

	}
}
