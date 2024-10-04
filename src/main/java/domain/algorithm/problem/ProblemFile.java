package domain.algorithm.problem;

public interface ProblemFile {
    String getFileName();
    String getPath();
    String getSourceCode();
    String getNumber();
    String getTag();

    String getExtension();
}
