package Listener.MenuListener;

import Windows.MainFrame;
import Windows.LoginWindows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutMenuListener implements ActionListener {
    private MainFrame mainWindow;
    public LogOutMenuListener(MainFrame window) {
        this.mainWindow = window;
    }
    private void closeWindow() {
        mainWindow.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO Auto-generated method stub
        if (e.getActionCommand().equals("Log-out ...")) {
            System.out.println("Log-out ... ok");

            int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to Logout?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                // code here
                new LoginWindows();
                closeWindow();
            }
        }
    }
}
