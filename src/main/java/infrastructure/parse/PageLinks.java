package infrastructure.parse;

import application.dto.PageElements;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PageLinks {
    private final PageElements pages;
    public PageLinks(PageElements pages) {
        this.pages = pages;
    }
    public List<String> getHrefModel() {
        return pages.getPages().stream()
                .map((page) -> page.getAttribute("href"))
                .collect(toList());
    }
}
