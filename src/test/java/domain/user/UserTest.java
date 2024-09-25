package domain.user;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class UserTest {
    static Dotenv dotenv = Dotenv.configure().load();
    static String expectedId;
    static String expectedPass;
    static String actureId;
    static String acturePass;
    @BeforeAll
    public static void userInfo() {
        actureId = expectedId = dotenv.get("ID");
        acturePass = expectedPass = dotenv.get("PASS");
    }
    @Test
    public void userTest() {
        User user = new User(actureId, acturePass);
        String id = user.getId();
        String pass = user.getPass();
//
        assertThat(id).isEqualTo(expectedId);
        assertThat(pass).isEqualTo(expectedPass);
    }
}
