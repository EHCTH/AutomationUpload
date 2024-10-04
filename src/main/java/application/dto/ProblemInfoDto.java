package application.dto;

import domain.algorithm.problem.Problem;
import domain.algorithm.problem.ProblemFile;

import java.util.Collections;
import java.util.List;

public class ProblemInfoDto  {
    private final List<ProblemFile> problemInfo;
    public ProblemInfoDto(List<ProblemFile> problemInfo) {
        this.problemInfo = validateList(problemInfo);
    }
    public List<ProblemFile> getProblemInfo() {
        return problemInfo;
    }
    private List<ProblemFile> validateList(List<ProblemFile> problemInfo) {
        if (problemInfo == null) return Collections.emptyList();
        return Collections.unmodifiableList(problemInfo);
    }

}
