package Dialog;

import Main.Const;
import Main.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReturnDialog extends JDialog implements ActionListener {

    private JTextField titleTextField;

    public ReturnDialog() {
        setTitle("Return Book");
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

        JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(350, 150);
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
        if(e.getActionCommand().equals(Const.OK)) {
            final String title = titleTextField.getText();

            if (Database.getInstance().checkExistBookByTitle(title)) {
                if (!Database.getInstance().isCanLend(title)) {
                    int dialogResult = JOptionPane.showConfirmDialog(null,
                            "Would you like to return this book?",
                            "Return Books",
                            JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        // code here
                        Database.getInstance().setBookStatus(title, false);
                        dispose();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "this book is not a borrowed book");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "wrong title");
            }
        }
        else if(e.getActionCommand().equals(Const.CANCEL)) {
            dispose();
        }
    }
}
