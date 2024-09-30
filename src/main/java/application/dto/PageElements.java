package application.dto;

import org.openqa.selenium.WebElement;

import java.util.List;

public class PageElements {
    private final List<WebElement> pages;
    public PageElements(List<WebElement> pages) {
        this.pages = pages;
    }
    public List<WebElement> getPages() {
        return pages;
    }
    @Override
    public String toString() {
        return pages.toString();
    }
}
