
package bankingsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class MenuForm extends JFrame{
    String[] columnNames = {"Customer ID", "Amount", "Status","Date","Time"};
    String from;
    JLabel L1=new JLabel("Welcome User- "+HomeForm.uid+", Please select the required option:   ");
    JButton B1=new JButton("      Deposit      ");
    JLabel L2=new JLabel("              ");
    JButton B2=new JButton("     Withdraw      ");
    JLabel L3=new JLabel("              ");
    JButton B3=new JButton("Transaction History");
    JLabel L4=new JLabel("              ");
    JButton B4=new JButton("       Logout      ");
    public MenuForm(){
        JFrame J = new JFrame();    
        setVisible(true);
        setTitle("Welcome to the Banking System - Menu Form User ID ("+HomeForm.uid+")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        this.setExtendedState(this.MAXIMIZED_BOTH);
        L1.setForeground(Color.red);
        add(L1);add(B1);add(L2);add(B2);add(L3);add(B3);add(L4);add(B4);
        B1.addActionListener(new Mb1());
        B2.addActionListener(new Mb2());
        B3.addActionListener(new Mb3());
        B4.addActionListener(new Mb4());
        
}
    
    class Mb1 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new bankingsystem.DepositForm();
            dispose();
        }
    }
    
    class Mb2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new bankingsystem.WithdrawForm();
            dispose();
        }
    }
    
       
    class Mb4 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new bankingsystem.HomeForm();
            dispose();
        }
    }
    
    
    
    
    class Mb3 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            showTableData();
        }
    }
    
    public void showTableData(){
        JFrame J1 = new JFrame("Transaction History of User ID ("+HomeForm.uid+")");
        J1.setExtendedState(J1.MAXIMIZED_BOTH);
     DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        JTable Table = new JTable();
        Table.setModel(model);
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        Table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(Table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        from = (String)HomeForm.uid;
        
        String cid = "";
        String  caccount= "";
        String status = "";
        String date = "";
        String time = "";
        try{
            
                HomeForm.st=HomeForm.Con.createStatement();
                HomeForm.P=HomeForm.Con.prepareCall("select * from tran where cid='"+from+"'");
                HomeForm.Rs=HomeForm.P.executeQuery();
                int i=0;
                while(HomeForm.Rs.next()){
                    cid=HomeForm.Rs.getString(1);
                    caccount=HomeForm.Rs.getString(2);
                    status=HomeForm.Rs.getString(3);
                    date=HomeForm.Rs.getString(4);
                    time=HomeForm.Rs.getString(5);
                     model.addRow(new Object[]{cid, caccount, status, date, time });
                     i++;
                }
                 if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        J1.add(scroll);
        J1.setVisible(true);
        J1.setLayout(new FlowLayout());
        }
    
    
    
    
}
