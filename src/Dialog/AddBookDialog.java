package Dialog;

import Main.Const;
import Main.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBookDialog extends JDialog implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField idTextField;
    private JTextField isbnTextField;
    private JTextField numberTextField;
    private JTextField authorsTextField;
    private JTextField titleTextField;
    private JTextField publisherTextField;
    private JTextField book_dateTextField;
    private JCheckBox statusCheckBox;
    private JTextField regist_dateTextField;

    public AddBookDialog() {
        setTitle("Add Book");
        setModal(true);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        add(panel1);

        JPanel panelCenter = new JPanel();
        panel1.add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new FlowLayout());

        panelCenter.add(new JLabel("id        "));
        idTextField = new JTextField(20);
        panelCenter.add(idTextField);

        panelCenter.add(new JLabel("isbn        "));
        isbnTextField = new JTextField(20);
        panelCenter.add(isbnTextField);

        panelCenter.add(new JLabel("number        "));
        numberTextField = new JTextField(20);
        panelCenter.add(numberTextField);

        panelCenter.add(new JLabel("authors        "));
        authorsTextField = new JTextField(20);
        panelCenter.add(authorsTextField);

        panelCenter.add(new JLabel("title        "));
        titleTextField = new JTextField(20);
        panelCenter.add(titleTextField);

        panelCenter.add(new JLabel("publisher        "));
        publisherTextField = new JTextField(20);
        panelCenter.add(publisherTextField);

        panelCenter.add(new JLabel("book_date        "));
        book_dateTextField = new JTextField(20);
        panelCenter.add(book_dateTextField);

        panelCenter.add(new JLabel("regist_date        "));
        regist_dateTextField = new JTextField(16);
        panelCenter.add(regist_dateTextField);

        statusCheckBox = new JCheckBox("status");
        panelCenter.add(statusCheckBox);

        JPanel panel2 = new JPanel();
        panel1.add(panel2, BorderLayout.SOUTH);
        JButton okBtn = new JButton(Const.OK);
        okBtn.addActionListener(this);
        panel2.add(okBtn);
        JButton cancelBtn = new JButton(Const.CANCEL);
        cancelBtn.addActionListener(this);
        panel2.add(cancelBtn);
        setSize(335, 340);
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

            if(idTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The id is empty");
                idTextField.requestFocus();
            }
            else if(isbnTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The isbn is empty");
                isbnTextField.requestFocus();
            }
            else if(numberTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The number is empty");
                numberTextField.requestFocus();
            }
            else if(authorsTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The author is empty");
                authorsTextField.requestFocus();
            }
            else if(titleTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The title is empty");
                titleTextField.requestFocus();
            }
            else if(publisherTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The publisher is empty");
                publisherTextField.requestFocus();
            }
            else if(book_dateTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The book_date is empty");
                book_dateTextField.requestFocus();
            }
            else if(regist_dateTextField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "The regist_date is empty");
                regist_dateTextField.requestFocus();
            }
            else {

                final int id = Integer.parseInt(idTextField.getText());
                final int isbn = Integer.parseInt(isbnTextField.getText());
                final int number = Integer.parseInt(numberTextField.getText());

                final String authors = authorsTextField.getText();
                final String title = titleTextField.getText();
                final String publisher = publisherTextField.getText();
                final String book_date = book_dateTextField.getText();
                final Boolean status = statusCheckBox.isSelected();
                final String regist_date = regist_dateTextField.getText();

                if (Database.getInstance().checkExistBookById(id)) {
                    JOptionPane.showMessageDialog(null, "The Book is already exist");
                    idTextField.requestFocus();
                } else {
                    /* 데이터베이스에 값을 입력합니다. */
                    Database.getInstance().insertBookData(id, isbn, number, authors, title, publisher, book_date, status, regist_date);
                    dispose();
                }
            }
        }
        else if(e.getActionCommand().equals(Const.CANCEL)) {
            dispose();
        }
    }
}
