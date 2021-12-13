package Windows;

import Listener.LoginButtonListener;
import Main.Const;

import javax.swing.*;
import java.awt.*;

public class LoginWindows extends JFrame {
    private static final long serialVersionUID = 1L;
    private LoginButtonListener buttonListener;
    public JTextField nameTextField = new JTextField(20);
    public JPasswordField pwdTextField = new JPasswordField(20);
    public LoginWindows() {
        buttonListener = new LoginButtonListener(this);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());
        add(new JLabel("name        "));
        add(nameTextField);
        add(new JLabel("password"));
        add(pwdTextField);

        JButton okButton = new JButton(Const.OK);
        okButton.addActionListener(buttonListener);
        add(okButton);
        JButton cancelButton = new JButton(Const.CANCEL);
        cancelButton.addActionListener(buttonListener);
        add(cancelButton);
        JButton joinButton = new JButton(Const.JOIN);
        joinButton.addActionListener(buttonListener);
        add(joinButton);

        setLocationRelativeTo(null);
        setSize(350, 150);
        setVisible(true);
    }
}
