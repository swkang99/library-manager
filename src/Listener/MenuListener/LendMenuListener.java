package Listener.MenuListener;

import Dialog.LendDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LendMenuListener implements ActionListener {
    public LendMenuListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Lend"))
        {
            System.out.println("Lend ... ok");
            LendDialog dialog = new LendDialog();
            dialog.setVisible(true);
        }
    }
}
