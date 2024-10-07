package application.service.controller;

import application.dto.ProblemInfoDto;
import application.service.login.LoginServiceManage;
import application.service.profile.ProfileManage;
import domain.algorithm.problem.ProblemFile;
import domain.cookie.SeleniumCookie;
import infrastructure.github.controller.UploadManage;
import infrastructure.parse.service.ExtractorManager;
import infrastructure.parse.service.ProblemLinkExtractionManage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ServiceController implements ControllerManager {
    private final Logger logger = LoggerFactory.getLogger(ServiceController.class);
    private final LoginServiceManage loginService;
    private final ProfileManage profileService;
    private final ExtractorManager problemExtractor;
    private final ProblemLinkExtractionManage linkExtractor;
    private final UploadManage githubUpload;

    public ServiceController(LoginServiceManage loginService,
                             ProfileManage profileService,
                             ExtractorManager problemExtractor,
                             ProblemLinkExtractionManage linkExtractor,
                             UploadManage githubUpload
    ) {
        this.loginService = loginService;
        this.profileService = profileService;
        this.problemExtractor = problemExtractor;
        this.linkExtractor = linkExtractor;
        this.githubUpload = githubUpload;
    }
    @Override
    public void start() {
        try {
            loginService.login();
            Thread.sleep(15000);
            SeleniumCookie cookies = loginService.extractCookies();
            profileService.enterMyPage();
            profileService.enterMySolvePage();

            List<String> problemInfo = linkExtractor.extractorLink(cookies);
            ProblemInfoDto problemInfoDto = problemExtractor.extractProblemInfoDto(cookies,
                    profileService,
                    problemInfo);
            List<ProblemFile> problemFiles = problemInfoDto.getProblemInfo();
            githubUpload.uploadFile(problemFiles);
        } catch (InterruptedException e) {
            logger.error("Interrupted during login process", e);
        } catch (IOException e) {
            logger.error("Error extracting problem information", e);
        } catch (Exception e) {
            logger.error("Unexpected Exception");
        }
        finally {
            quit();
        }
    }



    @Override
    public void quit() {
        logger.info("서비스 종료");
        loginService.logout();
    }
}
