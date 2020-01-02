package banking.view;

import banking.controller.BankAccountController;
import banking.entity.BankAccount;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BankAccountGUIForm extends JFrame {

    private BankAccountController service = new BankAccountController();
    
    private JButton btCreate = new JButton("Create");
    private JButton btFind = new JButton("Find");
    private JLabel lbNum = new JLabel("Number:");
    private JLabel lbOwnr = new JLabel("OwnerName:");
    private JLabel lbBalance = new JLabel("Balance:");
    private JTextField txtNum = new JTextField(10);
    private JTextField txtOwner = new JTextField(10);
    private JTextField txtBalance = new JTextField(10);
    private JPanel mainPanel = new JPanel();

    void init() {

        btCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (txtNum.getText().equals("") ||
                    txtBalance.getText().equals("") ||
                    txtOwner.getText().equals("") ) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled!");
                    return;
                }
                BankAccount ba = new BankAccount();
                ba.setNumber(Integer.parseInt(txtNum.getText()));
                ba.setOwner(txtOwner.getText());
                ba.setBalance(Long.parseLong(txtBalance.getText()));
                service.createAccount(ba);
            }
        });
        
        btFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               BankAccount ba = service.findByNumber(Integer.parseInt(txtNum.getText()));
               if ( ba != null ) {
                   txtBalance.setText(Long.toString(ba.getBalance()));
                   txtOwner.setText(ba.getOwner());
               } else {
                   JOptionPane.showMessageDialog(BankAccountGUIForm.this, "Account Not Found!");
               }
               
           }
        });
        
        mainPanel.setLayout(new GridLayout(4, 1));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel1.add(lbNum);
        panel1.add(txtNum);
        mainPanel.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.add(lbOwnr);
        panel2.add(txtOwner);
        mainPanel.add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel3.add(lbBalance);
        panel3.add(txtBalance);
        mainPanel.add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel4.add(btCreate);
        panel4.add(btFind);
        mainPanel.add(panel4);
        
        setSize(230, 200);
        getContentPane().add(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bank Form");
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new BankAccountGUIForm().init();
    }

}
