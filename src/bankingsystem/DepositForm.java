package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
public class DepositForm extends JFrame{
    JLabel L0=new JLabel("Deposit Amount: ");
    JLabel L1=new JLabel("User ID:  ");
    JTextField T1=new JTextField(20);
    JLabel L2=new JLabel("Current Balance:  ");
    JTextField T2=new JTextField(20);
    JLabel L3=new JLabel("Add Amount");
    JTextField T3=new JTextField(20);
    JButton B1=new JButton("Deposit");
    JButton B2=new JButton("Cancel");
   
    public DepositForm(){
        addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent E){
                T3.requestFocus();
            }});
        JFrame J = new JFrame();    
        setVisible(true);
        setTitle("Welcome to the Banking System - Deposit Form User ID ("+HomeForm.uid+")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.setExtendedState(this.MAXIMIZED_BOTH);
        L0.setForeground(Color.red);
        add(L0);add(L1); add(T1); add(L2); add(T2); add(L3); add(T3);add(B1);add(B2);
        T1.setEditable(false);
        T2.setEditable(false);
        T1.setText(HomeForm.uid);
        B1.addActionListener(new Db1());
        B2.addActionListener(new Db2());
          
                       try{
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.P=HomeForm.Con.prepareStatement("select caccount from bank where cid=(?)");
        HomeForm.P.setInt(1,Integer.parseInt(HomeForm.uid));
        HomeForm.Rs=HomeForm.P.executeQuery();
        if(HomeForm.Rs.next()){
            T2.setText(HomeForm.Rs.getString(1));
        }}catch(Exception E){JOptionPane.showMessageDialog(null, "Unable to retrieve data");  }
    }
    
   
    
    class Db1 implements ActionListener{
         public void actionPerformed(ActionEvent A){
             try{
                              if(Double.parseDouble(T3.getText())>50000){
                 JOptionPane.showMessageDialog(null, "You can make a transaction of Rs. 50000 INR (at max) at a time");  
                 T3.requestFocus();
                 return;
             }

                              else if(Double.parseDouble(T3.getText())<=0){
                 JOptionPane.showMessageDialog(null, "Please specify correct amount");  
                 T3.requestFocus();
                 return;
             }
             double k=0;
                if(T3.getText()==""){
                     JOptionPane.showMessageDialog(null, "No amount specified");  }
                else{
                    try{
                        k=Double.parseDouble(T3.getText());
        HomeForm.st=HomeForm.Con.createStatement();
        HomeForm.P=HomeForm.Con.prepareStatement("select caccount from bank where cid=(?)");
        HomeForm.P.setInt(1,Integer.parseInt(HomeForm.uid));
        HomeForm.Rs=HomeForm.P.executeQuery();
        if(HomeForm.Rs.next()){
            k=k+HomeForm.Rs.getDouble(1);
            T2.setText(Double.toString(k));
            }
                    }
                    
                    catch(NumberFormatException N){
                        JOptionPane.showMessageDialog(null, "Please provide accurate Amount");
                        T3.setText("");
                        T3.requestFocus();
                        return;
                    }
                    
                    
                    catch(Exception b){
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
                HomeForm.P.setString(3,"Deposit");
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

   
    
    class Db2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            dispose();
            new MenuForm();
        }
    }
        
    
   
}
