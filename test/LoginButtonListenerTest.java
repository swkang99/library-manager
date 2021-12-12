import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class LoginButtonListenerTest {

    @Test
    void actionPerformedTest() {
        LoginButtonListener btn = new LoginButtonListener(new LoginWindows());
        btn.actionPerformed(new ActionEvent(null, 0, Const.OK));
    }
}