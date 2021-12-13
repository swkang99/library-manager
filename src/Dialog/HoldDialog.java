package Dialog;

import Main.Const;
import Main.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HoldDialog extends JDialog implements ActionListener {

    private JTextField titleTextField;
    private JTextField nameTextField;

    public HoldDialog() {
        setTitle("Hold Book");
        setModal(true);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        add(panel1);

        JPanel panelCenter = new JPanel();
        panel1.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());
        panelCenter.add(new JLabel("title        "));
        titleTextField = new JTextField(20);
        panelCenter.add(titleTextField);
        panelCenter.add(new JLabel("user name   "));
        nameTextField = new JTextField(20);
        panelCenter.add(nameTextField);

        JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(350, 250);
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
        if (e.getActionCommand().equals(Const.OK)) {
            final String title = titleTextField.getText();
            final String name = nameTextField.getText();

            if (Database.getInstance().checkExistBookByTitle(title)) {
                if (Database.getInstance().checkExistName(name)) {
                    int dialogResult = JOptionPane.showConfirmDialog(null,
                            nameTextField.getText() + ", Would you like to Hold this book?",
                            "Hold Books",
                            JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        // code here
                        //Database.getInstance().setBookStatus(title, false);
                        System.out.println("Holding func");
                        Database.getInstance().reserveBook(title, name);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "wrong name member");
                }
            } else {
                JOptionPane.showMessageDialog(null, "wrong title");
            }
        } else if (e.getActionCommand().equals(Const.CANCEL)) {
            dispose();
        }
    }
}
