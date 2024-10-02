package application.service;

import application.dto.PageElements;
import application.dto.ProblemInfoDto;
import domain.algorithm.problem.Problem;
import domain.cookie.SeleniumCookie;

import java.io.IOException;
import java.util.List;

public interface TaskManger {
    void login();
    void start();
    void quit();
    void enterMyPage();
    void enterMysSolvedPage();
    List<String> extractProblemLink(SeleniumCookie cookie);
    ProblemInfoDto extractProblemInfoDto(List<String> problemInfo, SeleniumCookie cookies) throws IOException;
}
