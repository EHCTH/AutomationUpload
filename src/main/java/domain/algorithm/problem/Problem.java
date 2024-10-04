package domain.algorithm.problem;

public class Problem implements ProblemFile {
    private final ProblemNumber problemNumber;
    private final SourceCode sourceCode;
    private final AlgorithmTag algorithmTag;
    private final Extension extension;

    private Problem(Builder builder) {
        this.problemNumber = builder.problemNumber;
        this.sourceCode = builder.sourceCode;
        this.algorithmTag = builder.algorithmTag;
        this.extension = builder.extension;
    }
    @Override
    public String getSourceCode() {
        return sourceCode.getSourceCode();
    }

    @Override
    public String getNumber() {
        return problemNumber.getNumber();
    }

    @Override
    public String getTag() {
        return algorithmTag.getTag();
    }

    public String getExtension() {
        return extension.getExtension();
    }

    @Override
    public String getFileName() {
        return getNumber() + "." + getExtension();
    }
    @Override
    public String getPath() {
        StringBuilder sb = new StringBuilder();
        sb.append("/")
                .append(getTag())
                .append("/")
                .append(getFileName());
        return sb.toString();
    }



    public static class Builder {
        private ProblemNumber problemNumber;
        private SourceCode sourceCode;
        private AlgorithmTag algorithmTag;
        private Extension extension;

        public Builder() {}

        public Builder problemNumber(String problemNumber) {
            this.problemNumber = new ProblemNumber(problemNumber);
            return this;
        }

        public Builder sourceCode(String sourceCode) {
            this.sourceCode = new SourceCode(sourceCode);
            return this;
        }

        public Builder algorithmTag(String algorithmTag) {
            this.algorithmTag = AlgorithmTag.findTag(algorithmTag);
            return this;
        }

        public Builder extension(String extension) {
            this.extension = Extension.findExtension(extension);
            return this;
        }

        public Problem build() {
            return new Problem(this);
        }
    }
    @Override
    public String toString() {
        return String.format(
                "FileName: %s\nSourceCode: %s\nAlgorithmTag: %s\n"
                ,getFileName(), getSourceCode(), getTag()
        );
    }
}
