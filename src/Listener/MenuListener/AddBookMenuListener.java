package Listener.MenuListener;

import Dialog.AddBookDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookMenuListener implements ActionListener {

    public AddBookMenuListener() {
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
