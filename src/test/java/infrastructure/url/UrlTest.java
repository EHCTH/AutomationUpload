package infrastructure.url;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
public class UrlTest {
    @Test
    public void urlTest() {
        String url = Url.getLoginUrl();
        assertThat(url).isEqualTo("https://www.acmicpc.net/login?next=%2F");

    }
}
