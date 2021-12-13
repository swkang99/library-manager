package Listener;

import Dialog.HoldDialog;
import Dialog.ReturnDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoldMenuListener implements ActionListener {
    public HoldMenuListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Hold"))
        {
            System.out.println("Hold ... ok");
            HoldDialog dialog = new HoldDialog();
            dialog.setVisible(true);
        }
    }
}
