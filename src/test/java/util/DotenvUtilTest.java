package util;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class DotenvUtilTest {
    static Dotenv dotenv;
    @BeforeAll
    public static void init() {
        dotenv = Dotenv.configure().load();
    }

    @Test
    public void userIdTest() {
        String id = DotenvUtil.getID();
        assertThat(id).isEqualTo(dotenv.get("ID"));
    }

    @Test
    public void userPassTest() {
        String pass = DotenvUtil.getPass();
        assertThat(pass).isEqualTo(dotenv.get("PASS"));


    }

    @Test
    public void githubUserNameTest() {
        String githubUserName = DotenvUtil.getGithubUserName();
        assertThat(githubUserName).isEqualTo(dotenv.get("GIT_USER_NAME"));

    }

    @Test
    public void githubRepoUrlTest() {
        String githubRepoUrl = DotenvUtil.getGitHubRepoUrl();
        assertThat(githubRepoUrl).isEqualTo(dotenv.get("GIT_REPO_URL"));

    }

    @Test
    public void githubRepoNameTest() {
        String githubRepoName = DotenvUtil.getGithubRepoName();
        assertThat(githubRepoName).isEqualTo(dotenv.get("GIT_REPO_NAME"));

    }

    @Test
    public void githubTokenTest() {
        String githubToken = DotenvUtil.getGithubToken();
        assertThat(githubToken).isEqualTo(dotenv.get("GIT_HUB_TOKEN"));

    }
}
