package infrastructure.parse.service;

import domain.cookie.SeleniumCookie;
import org.jsoup.Connection;

import java.io.IOException;

public class CookieManager {
    public void applyCookiesToRequest(Connection connection, SeleniumCookie cookies) {
        cookies.getCookies().forEach(
                (cookie) -> connection.cookie(cookie.getName(), cookie.getValue())
        );
    }
    public Connection.Response executeWithCookies(Connection connection, SeleniumCookie cookies) throws IOException {
        applyCookiesToRequest(connection, cookies);
        return connection.execute();
    }
}
