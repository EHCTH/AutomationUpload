package application.service;

import application.dto.PageElements;
import application.dto.ProblemInfoDto;
import application.service.extract.ExtractPageElementsFactory;
import application.service.login.LoginServiceManage;
import application.service.profile.ProfileManage;
import domain.algorithm.problem.AlgorithmTag;
import domain.algorithm.problem.Problem;
import domain.cookie.SeleniumCookie;
import infrastructure.parse.*;
import infrastructure.selenium.Driver.*;
import infrastructure.url.Url;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceTask implements TaskManger {
    private final Logger logger = LoggerFactory.getLogger(ServiceTask.class);
    private final LoginServiceManage loginService;
    private final DriverController driver;
    private final WaitDriverController waitDriver;
    private final ProfileManage profileService;
    private final ExtractorManager problemExtractor;

    public ServiceTask(LoginServiceManage loginService,
                       DriverController driver,
                       WaitDriverController waitDriver,
                       ProfileManage profileService,
                       ExtractorManager problemExtractor
    ) {
        this.loginService = loginService;
        this.driver = driver;
        this.waitDriver = waitDriver;
        this.profileService = profileService;
        this.problemExtractor = problemExtractor;
    }

    @Override
    public void login() {
        loginService.login(driver, waitDriver);
        logger.info("로그인 완료");
    }

    @Override
    public void start() {
        try {
            String bojUrl = Url.getLoginUrl();
            driver.get(bojUrl);
            login();
            Thread.sleep(25000);
            SeleniumCookie cookies = new SeleniumCookie(driver.getCookie());
            enterMyPage();
            enterMysSolvedPage();
            List<String> problemLink = extractProblemLink(cookies);
            ProblemInfoDto problemInfoDto = extractProblemInfoDto(problemLink, cookies);
            for (Problem problem : problemInfoDto.getProblemInfo()) {
                System.out.println(problem);
            }


        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            quit();
        }
    }


    @Override
    public void quit() {
        driver.quit();
        logger.info("서비스 종료");
    }

    @Override
    public void enterMyPage() {
        profileService.enterMyPage(driver, waitDriver);
        logger.info("나의 페이지 클릭 완료");
    }

    @Override
    public void enterMysSolvedPage() {
        profileService.enterMySolvePage(driver, waitDriver);
        logger.info("나의 맞은 문제 클릭 완료");
    }

    @Override
    public List<String> extractProblemLink(SeleniumCookie cookie) {
        PageElements pages = ExtractPageElementsFactory.extractPage(driver, waitDriver);
        PageLinks pageLinks = new PageLinks(pages);
        Parse parse = new Parse(new CookieManager());
        List<String> problemInfo = new ArrayList<>();
        List<String> hrefModelPage = pageLinks.getHrefModel();
        try {
            for (String url : hrefModelPage) {
                parse.connectAndInitCookies(url, cookie);
                List<String> problemLink = problemExtractor.extractProblemProfile(parse);
                problemInfo.addAll(problemLink);
            }
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return problemExtractor.extractProblemLink(problemInfo);
    }

    @Override
    public ProblemInfoDto extractProblemInfoDto(List<String> problemInfo, SeleniumCookie cookies) throws IOException {
        logger.info("enter DTO {}", problemInfo);
        List<Problem> ret = new ArrayList<>();
        Parse parse = new Parse(new CookieManager());
        for (String url : problemInfo) {
            driver.get(url);
            String algorithmTag = profileService.findAlgorithmTag(driver, waitDriver);
            profileService.enterMySolveSubmissionPage(driver, waitDriver);
            WebElement firstSolveProblemElement = profileService.findFirstSolveElement(driver, waitDriver);
            String problemNumber = problemExtractor.extractProblemNumber(firstSolveProblemElement);
            String extension = problemExtractor.extractProblemExtension(firstSolveProblemElement);
            String link = problemExtractor.extractProblemSourceCode(firstSolveProblemElement);
            parse.connectAndInitCookies(link, cookies);
            String sourceCode = problemExtractor.extractorSourceCode(parse);

            Problem problem = new Problem.Builder()
                    .problemNumber(problemNumber)
                    .algorithmTag(algorithmTag)
                    .extension(extension)
                    .sourceCode(sourceCode)
                    .build();
            ret.add(problem);
        }
        return new ProblemInfoDto(ret);
    }
}
