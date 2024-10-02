package application.service.profile;

import org.openqa.selenium.WebElement;

public interface ProfileManage {
    void enterMyPage();
    void enterMySolvePage();
    void enterMySolveSubmissionPage();
    void enterProblem(String url);
    String findAlgorithmTag();
    WebElement findFirstSolveElement();
}
