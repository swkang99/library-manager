import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButtonListener implements ActionListener {
    private LoginWindows window;
    public LoginButtonListener(LoginWindows window) {
        this.window = window;
    }
    private void closeWindow() {
        window.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand().equals(Const.OK)){
            System.out.println("ok");
            final String name = window.nameTextField.getText();
            final String pwd = window.pwdTextField.getText();
            /*
             * 1. name, password에 값이 입력되었는지 확인합니다.
             * 1-1. 만약 없다면 경고 메시지를 출력합니다.
             * 2. 데이터베이스에서 member 테이블을 조회하여 입력된
             * name과 password가 같은 같이 있는 지 확인합니다.
             * 2-1. 같은 값이 있다면 MainFrame 화면을 띄웁니다.
             * 2-2. 같은 값이 없다면 경고 메시지를 출력합니다.
             * */
            if (!isTextFieldEmpty(name, pwd)) {
                if (Database.getInstance().checkMemberLogin(name, pwd)) {
                    /* 2-1. 같은 값이 있다면 MainFrame 화면을 띄웁니다.*/
                    new MainFrame();
                    /* 로그인 윈도우창은 닫습니다. */
                    closeWindow();
                }
                else {
                    /* 2-2. 같은 값이 없다면 경고 메시지를 출력합니다.*/
                    JOptionPane.showMessageDialog(null, "wrong id or pwd");
                    window.nameTextField.requestFocus();
                }
            }
        }
        else if(e.getActionCommand().equals(Const.JOIN)) {
            MemberJoinDialog dialog = new MemberJoinDialog();
            dialog.setVisible(true);
            System.out.println("join");
        }
        else if(e.getActionCommand().equals(Const.CANCEL)) {
            closeWindow();
            System.out.println("cancel");
        }
    }

    /* 1. name, password에 값이 입력되었는지 확인합니다.
     * 1-1. 만약 없다면 경고 메시지를 출력합니다.*/
    private boolean isTextFieldEmpty (String name, String pwd)
    {
        boolean retValue = true;

        if(name.length() == 0) {
            JOptionPane.showMessageDialog(null, "The name is empty");
            window.nameTextField.requestFocus();
        }
        else if(pwd.length() == 0) {
            JOptionPane.showMessageDialog(null, "The password is empty");
            window.pwdTextField.requestFocus();
        }
        else
            retValue = false;

        return retValue;
    }
}
