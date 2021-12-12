import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MemberJoinDialog extends JDialog implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField nameTextField;
    private JPasswordField pwdTextField;
    public MemberJoinDialog() {
        setTitle("Join Member");
        setModal(true);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        add(panel1);

        JPanel panelCenter = new JPanel();
        panel1.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());
        panelCenter.add(new JLabel("name        "));
        nameTextField = new JTextField(20);
        panelCenter.add(nameTextField);
        panelCenter.add(new JLabel("password"));
        pwdTextField = new JPasswordField(20);
        panelCenter.add(pwdTextField);

        JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(350, 200);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); //다이얼로그 제거
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals(Const.OK)) {
            final String name = nameTextField.getText();
            final String pwd = pwdTextField.getText();

            /* 1. name과 password에 값이 있는지 체크합니다. */
            if(name.length() == 0) {
                JOptionPane.showMessageDialog(null, "The name is empty");
                nameTextField.requestFocus();
            }
            else if(pwd.length() == 0) {
                JOptionPane.showMessageDialog(null, "The password is empty");
                pwdTextField.requestFocus();
            }
            else
            {
                /* 데이터베이스에 같은 name이 있는지 체크합니다. */
                if (Database.getInstance().checkExistName(name)) {
                    JOptionPane.showMessageDialog(null, "The name is already exist");
                    nameTextField.requestFocus();
                }
                else {
                    /* 데이터베이스에 값을 입력합니다. */
                    Database.getInstance().insertMemberData(name, pwd);
                    dispose();
                }
            }
        }
        else if(e.getActionCommand().equals(Const.CANCEL)) {
            dispose();
        }
    }

}
