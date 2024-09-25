package domain.algorithm.problem;

import java.util.Arrays;

public enum Extension {
    PYTHON("py", "py"),
    CPP("c++", "cpp"),
    C("c", "c"),
    JAVA("java", "java"),
    RUBY("ruby", "rb"),
    KOTLIN("kotlin", "kt"),
    RUST("rust", "rs");
    private final String key;
    private final String value;
    private static final Extension[] EXTENSIONS = values();
    Extension(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getExtension() {
        return value;
    }
    private boolean findKey(String data) {
        String lowerCaseData = data.toLowerCase();
        return lowerCaseData.contains(key);
    }
    public static Extension findExtension(String data) {
        return Arrays.stream(EXTENSIONS)
                .filter((extension) -> extension.findKey(data))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("[ERROR] 없는 확장자입니다 좀 많이 쓰는 걸 쓰십쇼 아님 직접추가하세요"));
    }

}