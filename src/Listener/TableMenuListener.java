package Listener;

import Windows.MainFrame;
import Main.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableMenuListener implements ActionListener {

    private MainFrame mainWindow;
    private JTable table;

    public TableMenuListener(MainFrame mainWindow) {
        this.mainWindow = mainWindow;
        createTable();
    }

    private void createTable() {
        String[] header = {};
        System.out.println("create table for members and booklist");
        DefaultTableModel model = new DefaultTableModel(header,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false: not editable
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        mainWindow.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if(e.getActionCommand().equals("Members ..."))
        {
            System.out.println("members... ok");

            Database.getInstance().insertMemberJTable(table);
        }
        else if (e.getActionCommand().equals("Book List")) {
            System.out.println("Book List ... ok");

            Database.getInstance().insertBookJTable(table);
        }
    }
}
