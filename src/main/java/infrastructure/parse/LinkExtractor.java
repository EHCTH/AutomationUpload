package infrastructure.parse;

import application.dto.PageElements;
import application.service.extract.ExtractPageElementsFactory;
import domain.cookie.SeleniumCookie;
import infrastructure.selenium.Driver.DriverController;
import infrastructure.selenium.Driver.WaitDriverController;
import infrastructure.selenium.css.BySelector;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class LinkExtractor implements ProblemLinkExtractionManage {
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    private final Parse parse;
    private final Logger logger = LoggerFactory.getLogger(LinkExtractor.class);
    public LinkExtractor(DriverController driver, WaitDriverController waitDriver, Parse parse) {
        this.driver = driver;
        this.waitDriver = waitDriver;
        this.parse = parse;
    }
    @Override
    public List<String> extractorLink(SeleniumCookie cookie) {
        PageElements pages = ExtractPageElementsFactory.extractPage(driver, waitDriver);
        List<String> problemInfo = new ArrayList<>();
        List<String> hrefModelPage = new PageLinks(pages).getHrefModel();
        try {
            for (String url : hrefModelPage) {
                Connection connection = parse.initConnection(url);
                List<String> problemLink = extractProblemProfile(connection, cookie);
                problemInfo.addAll(problemLink);
            }
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        logger.info("Problem cnt {}", problemInfo.size());
        return transformLink(problemInfo);
    }
    @Override
    public List<String> extractProblemProfile(Connection connection, SeleniumCookie cookies) throws IOException {
        Connection.Response response = parse.executeWithCookies(connection, cookies);
        Document document = Jsoup.parse(response.body());
        Elements elements = document.select(BySelector.getProblemSet());
        return elements.stream()
                .map((link) -> link.attr("href"))
                .collect(toList());
    }
    @Override
    public List<String> transformLink(List<String> problemInfo) {
        return problemInfo.stream()
                .map((problemLink) -> "https://www.acmicpc.net" + problemLink)
                .collect(Collectors.toList());
    }
}
