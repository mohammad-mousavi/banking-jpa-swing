package banking.view;

import banking.controller.BankAccountController;
import java.util.List;
import javax.swing.*;

public class BankAccountTableView extends JFrame {

    private BankAccountController bac = new BankAccountController();
    
    void init() {

        String[] columnNames = { "Number", "Owner", "Balance" };

        List list = bac.findAll();
        
        Object[][] model = new Object[list.size()][];
        for (int i=0; i<list.size(); i++) 
            model[i] = (Object[])list.get(i);
        
        JTable table = new JTable(model, columnNames);
        add(new JScrollPane(table));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,200);
        setTitle("Bank Form");
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        new BankAccountTableView().init();
    }
    
}
