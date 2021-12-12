package Listener.ActionListener;

import Dialog.AddBookDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookActionListener implements ActionListener {

    public AddBookActionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Add Book"))
        {
            System.out.println("Add Book ... ok");
            AddBookDialog dialog = new AddBookDialog();
            dialog.setVisible(true);
        }
    }
}
