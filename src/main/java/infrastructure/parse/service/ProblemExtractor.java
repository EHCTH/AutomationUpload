package infrastructure.parse.service;

import application.dto.ProblemInfoDto;
import application.service.profile.ProfileManage;
import domain.algorithm.problem.Problem;
import domain.algorithm.problem.ProblemFile;
import domain.cookie.SeleniumCookie;
import infrastructure.parse.domain.Parse;
import infrastructure.selenium.css.BySelector;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProblemExtractor implements ExtractorManager {
    private final Parse parse;
    private final Logger logger = LoggerFactory.getLogger(ProblemExtractor.class);
    int cnt = 0;
    public ProblemExtractor(Parse parse) {
        this.parse = parse;
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
    public String extractorSourceCode(String link, SeleniumCookie cookies) throws IOException {
        Connection connection = parse.initConnection(link);
        Connection.Response response = parse.executeWithCookies(connection, cookies);
        Document document = Jsoup.parse(response.body());
        Element element = document.select(BySelector.getSourceCode()).first();
        return element.text();
    }


    @Override
    public String extractProblemNumber(WebElement element) {
        String number = BySelector.getProblemNumber();
        return element.findElement(By.cssSelector(number)).getAttribute("href");
    }
    @Override
    public ProblemInfoDto extractProblemInfoDto(
                                                SeleniumCookie cookies,
                                                ProfileManage profileService,
                                                List<String> problemInfo) throws IOException {
        List<ProblemFile> ret = new ArrayList<>();
        for (String url : problemInfo) {
            profileService.enterProblem(url);
            String algorithmTag = profileService.findAlgorithmTag();

            profileService.enterMySolveSubmissionPage();

            WebElement firstSolveProblemElement = profileService.findFirstSolveElement();
            String problemNumber = extractProblemNumber(firstSolveProblemElement);
            String extension = extractProblemExtension(firstSolveProblemElement);
            String link = extractProblemSourceCode(firstSolveProblemElement);
            String sourceCode = extractorSourceCode(link, cookies);

            ProblemFile problem = new Problem.Builder()
                    .problemNumber(problemNumber)
                    .algorithmTag(algorithmTag)
                    .extension(extension)
                    .sourceCode(sourceCode)
                    .build();
            ret.add(problem);
            logger.info("success file count {},sourceCodeExtract={}", ++cnt,
                    sourceCodeExtractValidate(sourceCode));
        }
        return new ProblemInfoDto(ret);
    }
    private String sourceCodeExtractValidate(String sourceCode) {
        return sourceCode.length() == 0 ? "fail" : "success";
    }


}
