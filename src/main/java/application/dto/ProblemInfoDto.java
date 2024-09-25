package application.dto;

import domain.algorithm.problem.Problem;

import java.util.Collections;
import java.util.List;

public class ProblemInfoDto {
    private final List<Problem> problemInfo;
    public ProblemInfoDto(List<Problem> problemInfo) {
        this.problemInfo = validateList(problemInfo);
    }
    public List<Problem> getProblemInfo() {
        return problemInfo;
    }
    private List<Problem> validateList(List<Problem> problemInfo) {
        if (problemInfo == null) return Collections.emptyList();
        return Collections.unmodifiableList(problemInfo);
    }

}
