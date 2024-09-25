package domain.algorithm.problem;

public class SourceCode {
    private final String sourceCode;
    public SourceCode(String sourceCode) {
        validateSourceCode(sourceCode);
        this.sourceCode = sourceCode;

    }
    public String getSourceCode() {
        return sourceCode;
    }
    private void validateSourceCode(String sourceCode) {
        if (sourceCode == null || sourceCode.trim().isEmpty()) throw new IllegalArgumentException("[ERROR] 해당 소스 코드가 없습니다");
    }
}
