package util;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvUtil {
    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getID() {
        return dotenv.get("ID");
    }

    public static String getPass() {
        return dotenv.get("PASS");
    }

    public static String getGithubUserName() {
        return dotenv.get("GIT_USER_NAME");
    }

    public static String getGitHubRepoUrl() {
        return dotenv.get("GIT_REPO_URL");

    }

    public static String getGithubRepoName() {
        return dotenv.get("GIT_REPO_NAME");

    }

    public static String getGithubToken() {
        return dotenv.get("GIT_HUB_TOKEN");

    }
}
