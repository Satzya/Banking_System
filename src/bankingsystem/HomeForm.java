package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class HomeForm extends JFrame{
    static String uid;
    static Connection Con=null;
    static Statement st=null;
    static PreparedStatement P=null; 
    static ResultSet Rs=null;
    
    JLabel L1=new JLabel("Welcome, Please select the required option:            ");
    JButton B1=new JButton("Login");
    JLabel L2=new JLabel("              ");
    JButton B2=new JButton("Register");
    JLabel L3=new JLabel("              ");
    JButton B3=new JButton("Exit");
    public HomeForm(){
        JFrame J = new JFrame();
        setTitle("Welcome to the Banking System - Home Form");
        setVisible(true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        L1.setForeground(Color.red);
        add(L1);add(B1);add(L2);add(B2);add(L3);add(B3);
        B1.addActionListener(new HB1());
        B2.addActionListener(new HB2());
        B3.addActionListener(new HB3());
        try{
    Class.forName("com.mysql.jdbc.Driver");
    Con=DriverManager.getConnection("jdbc:mysql://localhost/banking","root","root");
    }
    catch(Exception E){
    JOptionPane.showMessageDialog(null, "Database Connectivity Unsucccessful");
    dispose();
    System.exit(0);
    }     
    }
    
    class HB1 implements ActionListener{
        
        public void actionPerformed(ActionEvent A){
            new LoginForm();
            dispose();
        }
    }
    
    class HB2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new RegisterForm();
            dispose();
        }
    }
    
    class HB3 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            System.exit(0);
        }
    }
    
    
    
    
    
    public static void main(String[] args) {
        new HomeForm();
    }
    
    
}
