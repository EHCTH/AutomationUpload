package domain.cookie;

import org.openqa.selenium.Cookie;

import java.util.Set;

public class SeleniumCookie {
    private final Set<Cookie> cookies;

    public SeleniumCookie(Set<Cookie> cookie) {
        this.cookies = cookie;
    }
    public Set<Cookie> getCookies() {
        return cookies;
    }
    @Override
    public String toString() {
        return cookies.toString();
    }
}
