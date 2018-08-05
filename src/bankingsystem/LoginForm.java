package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    int trial=3;
    JLabel L1=new JLabel("Username:  ");
    JLabel L2=new JLabel("Password:  ");
    JTextField T1=new JTextField(20);
    JPasswordField T2=new JPasswordField(20);
    JButton B1=new JButton("Login");
    JButton B2=new JButton("Cancel");
    JLabel L3=new JLabel();
   public LoginForm(){
            addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent E){
                T1.requestFocus();
            }
    });
        JFrame J = new JFrame();    
        setVisible(true);
        setTitle("Welcome to the Banking System - User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.setExtendedState(this.MAXIMIZED_BOTH);
        add(L1); add(T1); add(L2); add(T2); add(B1); add(B2);
        B1.addActionListener(new BB1());
        B2.addActionListener(new BB2());
    }
   
   
       class BB1 implements ActionListener{
    public void actionPerformed(ActionEvent A){
        try{
        int a=Integer.parseInt(T1.getText());
        String b=T2.getText();
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.P=HomeForm.Con.prepareStatement("select * from bank where cid=(?) and cpswd=(?)");
        HomeForm.P.setInt(1,a);
        HomeForm.P.setString(2,b);
        HomeForm.Rs=HomeForm.P.executeQuery();
        if(HomeForm.Rs.next()){
            JOptionPane.showMessageDialog(null, "Login Successul");
            HomeForm.uid=T1.getText();
            new bankingsystem.MenuForm();
            dispose();
          }
        else{
            if(trial==1){
         JOptionPane.showMessageDialog(null, "You have exceeded maximum attempts. Application will shut down");
         System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Login unsuccessful, You have maximum of "+(--trial)+" attempt(s) left");
            T1.setText("");
            T2.setText("");
                T1.requestFocus();
           }
        
    }
    catch(Exception f){
        if(trial==1){
         JOptionPane.showMessageDialog(null, "You have exceeded maximum attempts. Application will shut down");
         System.exit(0);
         }
        JOptionPane.showMessageDialog(null, "Username and password invalid, You have maximum of "+(--trial)+" attempt(s) left");
            T1.setText("");
            T2.setText("");
           T1.requestFocus();
    }   }
} 

   
   class BB2 implements ActionListener{
    public void actionPerformed(ActionEvent B){
        dispose();
        new HomeForm();
    } }

  
}