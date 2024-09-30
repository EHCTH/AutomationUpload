package application.service;

import domain.algorithm.problem.Problem;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProblemServiceTest {
    @Test
    public void test() {
//        ProblemService problemService = new ProblemService();
        List<Problem> problemInfo =
                List.of(
                        new Problem.Builder()
                                .problemNumber("1")
                                .sourceCode("1")
                                .algorithmTag("수학")
                                .extension("py")
                                .build()
                        ,
                        new Problem.Builder()
                                .problemNumber("2")
                                .sourceCode("2")
                                .algorithmTag("구현")
                                .extension("java")
                                .build(),
                        new Problem.Builder()
                                .problemNumber("3")
                                .sourceCode("3")
                                .algorithmTag("재귀")
                                .extension("c++")
                                .build()
                );
        for (Problem problem : problemInfo) {
            System.out.println(problem);
        }
    }
}
