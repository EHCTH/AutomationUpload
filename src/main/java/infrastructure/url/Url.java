package infrastructure.url;

public class Url {
    private static final String LOGIN_URL = "https://www.acmicpc.net/login?next=%2F";
    public static String getLoginUrl() {
        return LOGIN_URL;
    }
}
