package domain.algorithm.problem;

import domain.algorithm.problem.AlgorithmTag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class AlgorithmTagTest {
    @Test
    public void isPresentAlgorithmTagTest() {
        AlgorithmTag algorithmTag = AlgorithmTag.findTag("구현");
        String tag = algorithmTag.getTag();
        assertThat(tag).isEqualTo("IMPLEMENTATION");
    }

    @Test
    public void isAbsentAlgorithmTagTest() {
        AlgorithmTag algorithmTag = AlgorithmTag.findTag("없는 태그");
        String tag = algorithmTag.getTag();
        assertThat(tag).isEqualTo("UNKNOWN");
    }
}
