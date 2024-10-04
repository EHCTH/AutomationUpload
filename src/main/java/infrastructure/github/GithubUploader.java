package infrastructure.github;

import application.dto.ProblemInfoDto;
import domain.algorithm.problem.Problem;
import domain.algorithm.problem.ProblemFile;
import infrastructure.github.dto.Github;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class GithubUploader implements UploadManage {
    private int uploadFileCnt = 0;
    private final Github github;
    private final Logger logger = LoggerFactory.getLogger(GithubUploader.class);
    public GithubUploader(Github github) {
        this.github = github;
    }
    @Override
    public void uploadFile(List<ProblemFile> problemFiles) throws IOException {
        for (ProblemFile problem : problemFiles) {
            String apiUrl = github.getApiUrl();
            String owner = github.getOwner();
            String repo = github.getRepo();
            String url = apiUrl
                    .replace("{owner}", owner)
                    .replace("{repo}", repo)
                    .replace("{path}", problem.getPath());

            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpPut httpPut = new HttpPut(url);
                httpPut.setHeader("Authorization", "token " + github.getToken());
                httpPut.setHeader("Content-Type", "application/json");
                String sourceCode = problem.getSourceCode();
                String encodedContent = Base64.getEncoder().encodeToString(sourceCode.getBytes(StandardCharsets.UTF_8));
                String json = "{"
                        + "\"message\": \"Add new file\","
                        + "\"content\": \"" + encodedContent + "\""
                        + "}";

                StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
                httpPut.setEntity(entity);

                try (CloseableHttpResponse response = httpClient.execute(httpPut)) {
                    handleResponse(response, problem.getFileName());
                } catch (HttpResponseException e) {
                    logger.warn("Failed to upload file to repository: {}, file: {}. HTTP status: {} - {}",
                            repo, problem.getFileName(), e.getStatusCode(), e.getMessage());
                }
            }
        }
    }

    private void checkConnection(StatusLine responseStatusLine) throws HttpResponseException {
        int statusCode = responseStatusLine.getStatusCode();
        String reasonPhrase = responseStatusLine.getReasonPhrase();
        if (!isConnect(statusCode)) {
            throw new HttpResponseException(statusCode, "HTTP [ERROR] " + reasonPhrase);
        }
    }

    private boolean isConnect(int statusCode) {
        return statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED;
    }

    private boolean isResponse(HttpEntity responseEntity) {
        return responseEntity != null;
    }


    private void handleResponse(CloseableHttpResponse response, String fileName) throws IOException {
        StatusLine responseStatusLine = response.getStatusLine();
        HttpEntity responseEntity = response.getEntity();
        checkConnection(responseStatusLine);
        if (isResponse(responseEntity)) {
            logger.info("Successful Response : {}, FileCnt : {}", fileName, ++uploadFileCnt);
        } else logger.warn("Failed Response : Null responseEntity");
    }
}
