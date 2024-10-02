package infrastructure.parse;

import infrastructure.selenium.css.BySelector;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ProblemExtractor implements ExtractorManager {
    @Override
    public List<String> extractProblemProfile(Parse parse) throws IOException {
        Connection.Response response = parse.execute();
        Document document = Jsoup.parse(response.body());
        Elements elements = document.select(BySelector.getProblemSet());
        return elements.stream()
                .map((link) -> link.attr("href"))
                .collect(toList());
    }

    @Override
    public String extractProblemSourceCode(WebElement element) {
        String language = BySelector.getSolveLanguage();
        return element.findElement(By.cssSelector(language)).getAttribute("href");
    }


    @Override
    public String extractProblemExtension(WebElement element) {
        String language = BySelector.getSolveLanguage();
        return element.findElement(By.cssSelector(language)).getText();
    }


    @Override
    public List<String> extractProblemLink(List<String> problemInfo) {
        return problemInfo.stream()
                .map((problemLink) -> "https://www.acmicpc.net" + problemLink)
                .collect(Collectors.toList());
    }

    @Override
    public String extractorSourceCode(Parse parse) throws IOException {
        Connection.Response response = parse.execute();
        Document document = Jsoup.parse(response.body());
        Element element = document.select(BySelector.getSourceCode()).first();
        return element.text();
    }

    @Override
    public String extractProblemNumber(WebElement element) {
        String number = BySelector.getProblemNumber();
        return element.findElement(By.cssSelector(number)).getAttribute("href");

    }
}
