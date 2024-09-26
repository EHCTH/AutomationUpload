package application.service;

import domain.user.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {

    private static String expectedId;
    private static String expectedPass;
    @BeforeAll
    public static void init() {
        Dotenv dotenv = Dotenv.configure().load();
        expectedId = dotenv.get("ID");
        expectedPass = dotenv.get("PASS");
    }
    @Test
    public void createUserTest() {
        UserService userService = new UserService();
        User user = userService.createUser();
        String id = user.getId();
        String pass = user.getPass();
        assertThat(id).isEqualTo(expectedId);
        assertThat(pass).isEqualTo(expectedPass);
    }
}
