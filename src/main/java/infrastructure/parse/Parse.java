package infrastructure.parse;

import application.dto.PageElements;
import domain.cookie.SeleniumCookie;

public class Parse {
    private final SeleniumCookie cookie;
    private final PageElements pages;
    public Parse(SeleniumCookie cookie, PageElements pages) {
        this.cookie = cookie;
        this.pages = pages;
    }
}
