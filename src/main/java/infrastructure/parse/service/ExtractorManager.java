package infrastructure.parse.service;

import application.dto.ProblemInfoDto;
import application.service.profile.ProfileManage;
import domain.cookie.SeleniumCookie;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public interface ExtractorManager {
    String extractProblemSourceCode(WebElement element);
    String extractProblemExtension(WebElement element);

    String extractProblemNumber(WebElement element);
    String extractorSourceCode(String link, SeleniumCookie cookies) throws IOException;
    ProblemInfoDto extractProblemInfoDto(SeleniumCookie cookies,
                                         ProfileManage profileService, List<String> problemInfo) throws IOException;
}
