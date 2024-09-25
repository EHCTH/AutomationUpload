package domain.algorithm.problem;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;
public class ProblemTest {
    @Test
    public void test() {
        Problem problem = new Problem.Builder()
                .problemNumber("1000")
                .sourceCode("a, b = map(int, input().split())\n" +
                        "print(a+b)\n")
                .extension("py")
                .algorithmTag("수학")
                .build();

        String problemNumber = problem.getNumber();
        String sourceCode = problem.getSourceCode();
        String fileTxt = problem.getFileName();
        String algorithmTag = problem.getTag();

        assertThat(problemNumber).isEqualTo("1000");
        assertThat(sourceCode).isEqualTo(
                "a, b = map(int, input().split())\n" +
                "print(a+b)\n"
        );
        assertThat(fileTxt).isEqualTo("1000.py");
        assertThat(algorithmTag).isEqualTo("MATHEMATICS");


    }
}
