package cn.edu.ustb.www.view;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class ErrorPage extends JFrame
{
    BorderLayout borderLayout=new BorderLayout();
    FlowLayout flowLayout = new FlowLayout();
    
    JTextPane jTextPane=new JTextPane();
    JScrollPane jscrollpane = new JScrollPane();
    JPanel jpl = new JPanel();
    JButton jbt_exit = new JButton("关闭");
    JButton jbt_over = new JButton("终止程序");
    JLabel jlb = new JLabel();
    
    Border b1=BorderFactory.createBevelBorder(BevelBorder.RAISED,
            Color.red, Color.red, Color.magenta, Color.magenta);
    Border b2 = new TitledBorder(b1, "操作数据库错误信息");
    
    String errorInfo=null;
    
    public ErrorPage(String error)
    {
        this.errorInfo = error;
        try 
        {
        	Layout();
        } 
        catch (Exception exception) 
        {
            exception.printStackTrace();
        }
    }

    /*设置布局*/
    private void Layout() throws Exception
    {
    	this.setTitle("系统提示");
        getContentPane().setLayout(borderLayout);
        
        this.getContentPane().add(jlb,BorderLayout.NORTH);
        this.getContentPane().add(jscrollpane,BorderLayout.CENTER);
        this.getContentPane().add(jpl,BorderLayout.SOUTH);
        
        jscrollpane.getViewport().add(jTextPane);
        jTextPane.setBorder(b2);
        jTextPane.setText(this.errorInfo);
        
        flowLayout.setAlignment(FlowLayout.RIGHT);
        jpl.setLayout(flowLayout);
        jpl.add(jbt_over);
        jpl.add(jbt_exit);
        
        /*为按钮添加事件*/
        jbt_exit.addActionListener(new jbt_exit_actionAdapter(this));
        jbt_over.addActionListener(new jbt_over_actionAdapter(this));
        
        /*设置位置和大小*/
        this.setSize(400,300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) 
        {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width)
        {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    class jbt_exit_actionAdapter implements ActionListener 
    {
        private ErrorPage adaptee;
        jbt_exit_actionAdapter(ErrorPage adaptee) 
        {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e) 
        {
            adaptee.jbt_exit_actionPerformed(e);
        }
    }
    public void jbt_exit_actionPerformed(ActionEvent e)
    {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(false);
    }

    class jbt_over_actionAdapter implements ActionListener 
    {
        private ErrorPage adaptee;
        jbt_over_actionAdapter(ErrorPage adaptee)
        {
            this.adaptee = adaptee;
        }

        public void actionPerformed(ActionEvent e)
        {
            adaptee.jbt_over_actionPerformed(e);
        }
    }
    public void jbt_over_actionPerformed(ActionEvent e) 
    {
        System.exit(0);
    }
    
    public static void main(String args[])
    {
        ErrorPage error = new ErrorPage("设置系统提示成功");
    }
}
