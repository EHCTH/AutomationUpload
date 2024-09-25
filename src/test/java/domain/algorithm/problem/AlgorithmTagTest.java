package domain.algorithm;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AlgorithmTagTest {
    @Test
    public void isPresentAlgorithmTagTest() {
        String algorithmTag = AlgorithmTag.findTag("구현");
        assertThat(algorithmTag).isEqualTo("IMPLEMENTATION");
    }

    @Test
    public void isAbsentAlgorithmTagTest() {
        String algorithmTag = AlgorithmTag.findTag("없는 태그");
        assertThat(algorithmTag).isEqualTo("UNKNOWN");
    }
}
