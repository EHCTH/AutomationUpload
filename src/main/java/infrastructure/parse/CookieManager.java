package infrastructure.parse;

import domain.cookie.SeleniumCookie;
import org.jsoup.Connection;

public class CookieManager {
    public void initCookie(Connection connection, SeleniumCookie cookies) {
        cookies.getCookies().forEach(
                (cookie) -> connection.cookie(cookie.getName(), cookie.getValue())
        );
    }
}
