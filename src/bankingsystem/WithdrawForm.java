package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class WithdrawForm extends JFrame{
    JLabel L0=new JLabel("Withdraw Form: ");
    JLabel L1=new JLabel("User ID:  ");
    JTextField T1=new JTextField(20);
    JLabel L2=new JLabel("Current Balance:  ");
    JTextField T2=new JTextField(20);
    JLabel L3=new JLabel("Withdrawl Amount");
    JTextField T3=new JTextField(20);
    JButton B1=new JButton("Withdraw");
    JButton B2=new JButton("Cancel");
    public WithdrawForm(){
                addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent E){
                T3.requestFocus();
            }});
        JFrame J = new JFrame();    
        setVisible(true);
        setTitle("Welcome to the Banking System - Withdraw Form User ID ("+HomeForm.uid+")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.setExtendedState(this.MAXIMIZED_BOTH);
        L0.setForeground(Color.red);
        add(L0);add(L1); add(T1); add(L2); add(T2); add(L3); add(T3);add(B1);add(B2);
        T1.setEditable(false);
        T2.setEditable(false);
        T1.setText(HomeForm.uid);

        
        try{
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.P=HomeForm.Con.prepareStatement("select caccount from bank where cid=(?)");
        HomeForm.P.setInt(1,Integer.parseInt(HomeForm.uid));
        HomeForm.Rs=HomeForm.P.executeQuery();
        if(HomeForm.Rs.next()){
            T2.setText(HomeForm.Rs.getString(1));
        }}catch(Exception E){JOptionPane.showMessageDialog(null, "Unable to retrieve data");  }
                B1.addActionListener(new Wb1());
                B2.addActionListener(new Wb2());
        
    }
    
    
    class Wb1 implements ActionListener{
        public void actionPerformed(ActionEvent A){
             try{
             double a=Double.parseDouble(T2.getText()); double b=Double.parseDouble(T3.getText());
             double k=0;
             if(T3.getText()==""){
                     JOptionPane.showMessageDialog(null, "No amount specified");  }
           
             else if(Double.parseDouble(T3.getText())<=0){
                 JOptionPane.showMessageDialog(null, "Please specify correct amount");  
                 T3.requestFocus();
                 return;
             }
                else if(b>a){
                     JOptionPane.showMessageDialog(null, "Insuffecient Balance");  
                     T3.requestFocus();}
                else{
                    try{
                        k=Double.parseDouble(T3.getText());
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.P=HomeForm.Con.prepareStatement("select caccount from bank where cid=(?)");
        HomeForm.P.setInt(1,Integer.parseInt(HomeForm.uid));
        HomeForm.Rs=HomeForm.P.executeQuery();
        if(HomeForm.Rs.next()){
            k=HomeForm.Rs.getDouble(1)-k;
            T2.setText(Double.toString(k));
            }
                    }
                    
                    catch(NumberFormatException N){
                        JOptionPane.showMessageDialog(null, "Please provide accurate Amount");
                        T3.setText("");
                        T3.requestFocus();
                        return;
                         }
                    
                    
                    catch(Exception B){
                        JOptionPane.showMessageDialog(null, "Invalid Data");}

                    try{
                HomeForm.st=HomeForm.Con.createStatement();
                HomeForm.P=HomeForm.Con.prepareCall("update bank set caccount=(?) where cid="+HomeForm.uid);
                HomeForm.P.setDouble(1,k);
                HomeForm.P.executeUpdate();
                
                
                HomeForm.st=HomeForm.Con.createStatement();
                HomeForm.P=HomeForm.Con.prepareCall("insert into tran values (?,?,?,?,?)");
                HomeForm.P.setInt(1,Integer.parseInt(HomeForm.uid));
                HomeForm.P.setDouble(2,Double.parseDouble(T3.getText()));
                HomeForm.P.setString(3,"Withdrawl");
                HomeForm.P.setString(4,String.valueOf(LocalDate.now()));
                HomeForm.P.setString(5,String.valueOf(LocalTime.now()));
                HomeForm.P.executeUpdate();
                    
                JOptionPane.showMessageDialog(null, "Successful");
                    T3.setText("");
                    T3.requestFocus();
                    }
                    catch(Exception O){  JOptionPane.showMessageDialog(null, "Invalid entry"); }
                }
         }catch(Exception R){JOptionPane.showMessageDialog(null, "Invalid details");
            T3.setText("");
            T3.requestFocus();
         }
            }
        }

    class Wb2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            dispose();
            new MenuForm();
        }
    }
    
    
    
    
    
    
    
}
