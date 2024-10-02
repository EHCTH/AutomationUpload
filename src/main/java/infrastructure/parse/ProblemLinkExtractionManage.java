package infrastructure.parse;

import domain.cookie.SeleniumCookie;

import java.io.IOException;
import java.util.List;

public interface ProblemLinkExtractionManage {
    List<String> extractorLink(SeleniumCookie cookie);
    List<String> extractProblemProfile() throws IOException;
    List<String> transformLink(List<String> problemInfo);
}
