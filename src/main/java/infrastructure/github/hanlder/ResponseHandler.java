package infrastructure.github.hanlder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class ResponseHandler {
    public void checkConnection(StatusLine responseStatusLine) throws HttpResponseException {
        int statusCode = responseStatusLine.getStatusCode();
        String reasonPhrase = responseStatusLine.getReasonPhrase();
        if (!isConnect(statusCode)) {
            throw new HttpResponseException(statusCode, "HTTP [ERROR] " + reasonPhrase);
        }
    }

    private boolean isConnect(int statusCode) {
        return statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED;
    }

    public void handleResponse(CloseableHttpResponse response, String fileName, Logger logger, AtomicInteger uploadFileCnt) throws IOException {
        StatusLine responseStatusLine = response.getStatusLine();
        HttpEntity responseEntity = response.getEntity();
        checkConnection(responseStatusLine);
        if (isResponse(responseEntity)) {
            logger.info("성공적인 응답: {}, 파일 카운트: {}", fileName, uploadFileCnt.incrementAndGet());
        } else {
            logger.warn("실패한 응답: 응답 엔티티가 null입니다.");
        }
    }

    private boolean isResponse(HttpEntity responseEntity) {
        return responseEntity != null;
    }
}
