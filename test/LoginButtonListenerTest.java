import Listener.LoginButtonListener;
import Main.Const;
import Windows.LoginWindows;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

class LoginButtonListenerTest {

    @Test
    void actionPerformedTest() {
        LoginButtonListener btn = new LoginButtonListener(new LoginWindows());
        btn.actionPerformed(new ActionEvent(null, 0, Const.OK));
    }
}