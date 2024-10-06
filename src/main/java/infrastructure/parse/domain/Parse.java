package infrastructure.parse.domain;

import domain.cookie.SeleniumCookie;
import infrastructure.parse.service.CookieManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;


public class Parse {
    private final CookieManager cookieManager;

    public Parse(CookieManager cookieManager) {
        this.cookieManager = cookieManager;
    }

    public Connection initConnection(String url) {
        return Jsoup.connect(url);
    }
    public Connection.Response executeWithCookies(Connection connection, SeleniumCookie cookies) throws IOException {
        return cookieManager.executeWithCookies(connection, cookies);
    }
}
