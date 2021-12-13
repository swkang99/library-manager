package Listener;

import Windows.LoginWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInMenuListener implements ActionListener {

    public LogInMenuListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Log-in ..."))
        {
            System.out.println("Log-in ... ok");

            new LoginWindows();
        }
    }
}
