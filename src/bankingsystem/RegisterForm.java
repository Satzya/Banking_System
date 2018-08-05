package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterForm extends JFrame {
    int p=1001;
    JFrame J=new JFrame();
    JLabel L1=new JLabel("Auto Generated User-ID:  ");
    JLabel L2=new JLabel("Enter password:  ");
    JLabel L3=new JLabel("Re-enter password:  ");
    JLabel L4=new JLabel("Opening Balance:  ");
    JTextField T1=new JTextField(10);
    JPasswordField T2=new JPasswordField(10);
    JPasswordField T3=new JPasswordField(10);
    JTextField T4=new JTextField(10);
    JButton B1=new JButton("Ok");
    JButton B2=new JButton("Cancel");
    
    public RegisterForm(){
         addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent E){
                T2.requestFocus();
            }
    });
        setVisible(true);
        setTitle("Welcome to the Banking System - Register Customer");
        setLayout(new FlowLayout());       
        T1.setEditable(false);
        add(L1);add(T1);add(L2);add(T2);add(L3);add(T3);add(L4);add(T4);add(B1);add(B2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        B1.addActionListener(new Rb1());
        B2.addActionListener(new Rb2());
        
            
        try{
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.Rs=HomeForm.st.executeQuery("select cid from bank");
        if(HomeForm.Rs.next()){ HomeForm.Rs.beforeFirst();
            while(HomeForm.Rs.next()){
                T1.setText(HomeForm.Rs.getString(1));
            }
            p=Integer.parseInt(T1.getText());
            T1.setText(Integer.toString(++p));
        }
        else{
            T1.setText("1001");
        }
        }
        catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Invalid Entry");  }
        
    }
    
    class Rb1 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            try{
            String a=T2.getText();   String b=T3.getText();
            if(a=="" && b==""){
                    JOptionPane.showMessageDialog(null, "Password field not specified");
                    T2.setText("");
                    T3.setText("");
                    T2.requestFocus();
                    return;    
                }
            else if(a.length()<5 && b.length()<5){
                    JOptionPane.showMessageDialog(null, "Password must be minimum 5 characters");
                    T2.setText("");
                    T3.setText("");
                    T2.requestFocus();
                    return;
                }
            else if(T4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter Opening balance");
                    T4.requestFocus();
                    return;
                }
            else if(Integer.parseInt(T4.getText())<500){
                    JOptionPane.showMessageDialog(null, "Balance cannot be less than Rs. 500");
                    T4.requestFocus();
                    return;
                }
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)!=b.charAt(i)){
                    JOptionPane.showMessageDialog(null, "Password mismatch, Please Re-enter");
                    T2.setText("");
                    T3.setText("");
                    T2.requestFocus();
                    break;
                }
                
                else{
                    try{
                HomeForm.st=HomeForm.Con.createStatement();
                HomeForm.P=HomeForm.Con.prepareCall("insert into bank values (?,?,?)");
                HomeForm.P.setInt(1,p);
                HomeForm.P.setString(2,T2.getText());
                HomeForm.P.setDouble(3,Double.parseDouble(T4.getText()));
                HomeForm.P.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Successful");
                    dispose();
                    HomeForm.Rs=null;
                    new HomeForm();
                    break;
                    }
                    catch(NumberFormatException N){
                        JOptionPane.showMessageDialog(null, "Invalid Opening balance details");
                        T4.setText("");
                        T4.requestFocus();
                        break;
                    }
                    catch(Exception E){
                    JOptionPane.showMessageDialog(null, "Invalid data"+E);
                    break;
                    }
                }
            }
            
            }catch(Exception R){ JOptionPane.showMessageDialog(null, "Invalid data"); }
    
        
        }
    
    }
           
    class Rb2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            dispose();
            new HomeForm();
        }
    }
        
    
    
}
