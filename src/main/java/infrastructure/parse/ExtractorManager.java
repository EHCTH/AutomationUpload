package infrastructure.parse;

import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public interface ExtractorManager {
    List<String> extractProblemProfile(Parse parse) throws IOException;
    String extractProblemSourceCode(WebElement element);
    String extractProblemExtension(WebElement element);

    String extractProblemNumber(WebElement element);
    List<String> extractProblemLink(List<String> problemInfo);
    String extractorSourceCode(Parse parse) throws IOException;
}
