package Listener;

import Dialog.LendDialog;
import Dialog.ReturnDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnMenuListener implements ActionListener {
    public ReturnMenuListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Return"))
        {
            System.out.println("Return ... ok");
            ReturnDialog dialog = new ReturnDialog();
            dialog.setVisible(true);
        }
    }
}
