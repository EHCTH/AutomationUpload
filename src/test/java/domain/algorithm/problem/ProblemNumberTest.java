package domain.algorithm.problem;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProblemNumberTest {
    @Test
    public void presentNumberTest() {
        ProblemNumber problemNumber = new ProblemNumber("1000");
        String number = problemNumber.getNumber();
        assertThat(number).isEqualTo("1000");
    }
    @Test
    public void absentNumberTest() {
        assertThatThrownBy(() -> new ProblemNumber(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 없는 번호입니다");
    }
}
