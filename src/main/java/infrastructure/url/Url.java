package infrastructure.url;

public class Url {
    private static final String loginUrl = "https://www.acmicpc.net/login?next=%2F";
    public static String getLoginUrl() {
        return loginUrl;
    }
}
