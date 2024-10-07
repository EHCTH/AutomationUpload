package infrastructure.github.service;

import domain.algorithm.problem.ProblemFile;
import infrastructure.github.hanlder.ResponseHandler;
import infrastructure.github.dto.Github;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileUploaderService {
    private final HttpClientService httpClientService;
    private final ResponseHandler responseHandler;
    private final Github github;
    private Logger logger = LoggerFactory.getLogger(FileUploaderService.class);
    private final AtomicInteger uploadFileCnt = new AtomicInteger(0);

    public FileUploaderService(HttpClientService httpClientService, ResponseHandler responseHandler, Github github) {
        this.httpClientService = httpClientService;
        this.responseHandler = responseHandler;
        this.github = github;
    }

    public void uploadFiles(List<ProblemFile> problemFiles) throws IOException {
        for (ProblemFile problem : problemFiles) {
            String url = constructUrl(problem);
            String jsonPayload = constructJsonPayload(problem);

            try (CloseableHttpResponse response = httpClientService.executePut(url, github.getToken(), jsonPayload)) {
                responseHandler.handleResponse(response, problem.getFileName(), logger, uploadFileCnt);
            } catch (HttpResponseException e) {
                logger.warn("레포지토리에 파일 업로드 실패: {}, 파일: {}. HTTP 상태: {} - {}",
                        github.getRepo(), problem.getFileName(), e.getStatusCode(), e.getMessage());
            }
        }
    }

    private String constructUrl(ProblemFile problem) {
        return github.getApiUrl()
                .replace("{owner}", github.getOwner())
                .replace("{repo}", github.getRepo())
                .replace("{path}", problem.getPath());
    }

    private String constructJsonPayload(ProblemFile problem) {
        String encodedContent = Base64.getEncoder()
                .encodeToString(problem.getSourceCode()
                        .getBytes(StandardCharsets.UTF_8));
        return "{"
                + "\"message\": \"새 파일 추가\","
                + "\"content\": \"" + encodedContent + "\""
                + "}";
    }
}
