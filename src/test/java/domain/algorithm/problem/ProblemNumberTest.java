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
        ProblemNumber problemNumber = new ProblemNumber("");
        String number = problemNumber.getNumber();
        String expectedNumber = String.valueOf(Integer.MAX_VALUE);
        assertThat(number).isEqualTo(expectedNumber);
    }
}
