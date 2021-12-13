package Windows;

import Listener.*;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Library Manager");
        setSize(800, 500);
        createMenu();
        setVisible(true);
        //화면 가운데 생성
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        /* Menu를 만듭니다. */
        JMenuBar mb = new JMenuBar();

        JMenu personMenu = new JMenu("Person");
        JMenuItem loginMenuItem = new JMenuItem("Log-in ...");
        JMenuItem logoutMenuItem = new JMenuItem("Log-out ...");
        personMenu.add(loginMenuItem);
        personMenu.add(logoutMenuItem);

        JMenu memberMenu = new JMenu("Members");
        JMenuItem membersMenuItem = new JMenuItem("Members ...");
        memberMenu.add(membersMenuItem);

        JMenu bookMenu = new JMenu("Book");
        JMenuItem addbookMenuItem = new JMenuItem("Add Book");
        JMenuItem booklistMenuItem = new JMenuItem("Book List");
        JMenuItem lendMenuItem = new JMenuItem("Lend");
        JMenuItem returnMenuItem = new JMenuItem("Return");
        JMenuItem holdMenuItem = new JMenuItem("Hold");
        bookMenu.add(addbookMenuItem);
        bookMenu.add(booklistMenuItem);
        bookMenu.add(lendMenuItem);
        bookMenu.add(returnMenuItem);
        bookMenu.add(holdMenuItem);

        mb.add(personMenu);
        mb.add(memberMenu);
        mb.add(bookMenu);

        LogInMenuListener loginListener = new LogInMenuListener();
        loginMenuItem.addActionListener(loginListener);

        LogOutMenuListener logoutListener = new LogOutMenuListener(this);
        logoutMenuItem.addActionListener(logoutListener);

        TableMenuListener tableListener = new TableMenuListener(this);
        membersMenuItem.addActionListener(tableListener);
        booklistMenuItem.addActionListener(tableListener);

        AddBookMenuListener addBookMenuListener = new AddBookMenuListener();
        addbookMenuItem.addActionListener(addBookMenuListener);

        LendMenuListener lendListener = new LendMenuListener();
        lendMenuItem.addActionListener(lendListener);

        ReturnMenuListener returnListener = new ReturnMenuListener();
        returnMenuItem.addActionListener(returnListener);

        HoldMenuListener holdListener = new HoldMenuListener();
        holdMenuItem.addActionListener(holdListener);

        setJMenuBar(mb);
    }
}
