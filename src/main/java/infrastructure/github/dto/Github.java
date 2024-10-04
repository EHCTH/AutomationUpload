package infrastructure.github.domain;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Github {
    private static final Dotenv config = Dotenv.configure().load();
    private static final String API_URL = "https://api.github.com/repos/{owner}/{repo}/contents/{path}";
    private static final String TOKEN = config.get("GIT_HUB_TOKEN");
    private static final String OWNER = config.get("GIT_USER_NAME");
    private static final String REPO = config.get("GIT_REPO_NAME");
    public String getApiUrl() {
        return API_URL;
    }
    public String getToken() {
        return TOKEN;
    }
    public String getOwner() {
        return OWNER;
    }
    public String getRepo() {
        return REPO;
    }

}
