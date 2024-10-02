package infrastructure.parse;

import domain.cookie.SeleniumCookie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


public class Parse {
    private final CookieManager cookieManager;
    private Connection connection;

    public Parse(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public void connectAndInitCookies(String url, SeleniumCookie cookies) {
        connection = Jsoup.connect(url);
        cookieManager.initCookie(connection, cookies);
    }

    public Connection.Response execute() throws IOException {
        return connection.execute();
    }
}
