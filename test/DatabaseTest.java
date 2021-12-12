import Main.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    @Test
    void checkExistNameTest() {
        boolean ret = Database.getInstance().checkExistName("test");
        assertEquals(true, ret);
    }

    @Test
    void checkExistNameInvalidTest() {
        boolean ret = Database.getInstance().checkExistName("abc");
        assertEquals(false, ret);
    }

    @Test
    void checkExistBookByIdTest() {
        boolean ret = Database.getInstance().checkExistBookById(142);
        assertEquals(true, ret);
    }

    @Test
    void checkExistBookByIdInvalidTest() {
        boolean ret = Database.getInstance().checkExistBookById(000);
        assertEquals(false, ret);
    }

    @Test
    void checkMemberLoginTest() {
        boolean ret = Database.getInstance().checkMemberLogin("test", "1234");
        assertEquals(true, ret);
    }

    @Test
    void checkMemberLoginInvalidTest() {
        boolean ret = Database.getInstance().checkMemberLogin("abc", "123");
        assertEquals(false, ret);
    }

    @Test
    void checkExistTableTest() {
        boolean ret = Database.getInstance().checkExistTable("member");
        assertEquals(true, ret);
    }

    @Test
    void checkExistTableInvalidTest() {
        boolean ret = Database.getInstance().checkExistTable("tbale");
        assertEquals(false, ret);
    }
}