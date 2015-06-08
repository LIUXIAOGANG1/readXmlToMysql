package cn.edu.ustb.www.view;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.mysql.jdbc.StringUtils;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.util.DealXml;

public class InsertXml  extends JFrame{
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	JFileChooser f = new JFileChooser();//实例选择器
	JTextField jtf1,jtf2,jtf3;
	public static JTextField version;
    JLabel jlb1,jlb2,jlb3,jlb4;
    JButton jbt1,jbt2,jbt3;
    JPanel jpl;
    
    Statement st;
	Connection conn;
	ResultSet rs;
	
	
	public InsertXml()
	{
		Container contentPane=MainSheet.frame.getContentPane();
		final JDialog jdlog=new JDialog(this,true);
		jdlog.setTitle("添加模型");
		jdlog.setResizable(false);
		
		jtf1=new JTextField(15);
		jtf1.setFont(new Font("Serif",Font.PLAIN,16));
		jtf2=new JTextField(15);
		jtf2.setFont(new Font("Serif",Font.PLAIN,16));
		jtf3=new JTextField(15);
		jtf3.setFont(new Font("Serif",Font.PLAIN,16));
		version=new JTextField(15);
		version.setFont(new Font("Serif",Font.PLAIN,16));
		
		jlb1=new JLabel("选择文件 ：",SwingConstants.CENTER);
		jlb1.setFont(new Font("Serif",Font.PLAIN,16));
		jlb2=new JLabel("创  建  人 ：",SwingConstants.CENTER);
		jlb2.setFont(new Font("Serif",Font.PLAIN,16));
		jlb3=new JLabel("创建时间 ：",SwingConstants.CENTER);
		jlb3.setFont(new Font("Serif",Font.PLAIN,16));
		jlb4=new JLabel("版  本  号 ：",SwingConstants.CENTER);
		jlb4.setFont(new Font("Serif",Font.PLAIN,16));
		jbt1=new JButton("保存");
		jbt1.setFont(new Font("Serif",Font.PLAIN,16));
		jbt2=new JButton("退出");
		jbt2.setFont(new Font("Serif",Font.PLAIN,16));
		
		jbt3=new JButton("浏览");
		jbt3.setFont(new Font("Serif",Font.PLAIN,14));
		
		Border bd2=BorderFactory.createLoweredBevelBorder();
        Border bd3=BorderFactory.createEtchedBorder(); 
        
        jtf1.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
        jtf2.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
        jtf3.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
        version.setBorder(BorderFactory.createCompoundBorder(bd3, bd2));
        
		jpl=new JPanel();
		jdlog.getContentPane().add(jpl);
		jpl.setLayout(null);
		
		jpl.add(jlb1);    jpl.add(jtf1);
		jpl.add(jlb2);    jpl.add(jtf2);
		jpl.add(jlb3);    jpl.add(jtf3);
		jpl.add(jlb4);    jpl.add(version);
		jpl.add(jbt1);    jpl.add(jbt2);
		jpl.add(jbt3);
		jlb1.setBounds(10,20, 100,30);
		jtf1.setBounds(115,20,100,30);
		jbt3.setBounds(230, 20, 70,30);
		jlb2.setBounds(10,60, 100,30);
		jtf2.setBounds(115,60, 100, 30);
		jlb3.setBounds(10, 100, 100, 30);
		jtf3.setBounds(115,100, 100, 30);
		jlb4.setBounds(10, 140, 100, 30);
		version.setBounds(115,140, 100, 30);
		jbt1.setBounds(20, 200, 70, 30);
		jbt2.setBounds(144, 200, 70, 30);
		
		jtf2.setText(LoginSystem.user.getText().trim());
		jtf3.setText(df.format(new Date()));
		jtf2.setEditable(false);
		jtf3.setEditable(false);
		
		jbt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1=jtf1.getText().trim();
				String str2=jtf2.getText().trim();
				String str3=jtf3.getText().trim();
				

				
				try {
					if(StringUtils.isNullOrEmpty(str1)){
						JOptionPane.showMessageDialog(null, "请选择模板！", "提示对话框", 1);
					}else{
//						File file = new File(str1);
//						InputStream in = null;
//						in = new FileInputStream(file);
//						PetriNet pnml = ResponseJaxbParser.toPetriNet(in);
						DealXml dx = new DealXml();
						if(dx.readXml(str1)){
							JOptionPane.showMessageDialog(null, "模板添加成功！", "提示对话框", 1);
							jdlog.dispose();
						}else{
							JOptionPane.showMessageDialog(null, "模板添加失败，请检查模板格式是否正确！", "提示对话框", 1);
						}
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		jbt2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jdlog.dispose();
			}
		});
		
		//浏览文件
		jbt3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JFileChooser jfc = new JFileChooser();
				if(jfc.showOpenDialog(f)==JFileChooser.APPROVE_OPTION ){
					jtf1.setText(jfc.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		jdlog.setBounds(340, 100, 335, 320);
		jdlog.setVisible(true);
	}
}
