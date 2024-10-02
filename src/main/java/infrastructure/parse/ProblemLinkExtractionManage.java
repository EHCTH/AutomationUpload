package infrastructure.parse;

import domain.cookie.SeleniumCookie;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.List;

public interface ProblemLinkExtractionManage {
    List<String> extractorLink(SeleniumCookie cookie);
    List<String> extractProblemProfile(Connection connection, SeleniumCookie cookies) throws IOException;
    List<String> transformLink(List<String> problemInfo);
}
