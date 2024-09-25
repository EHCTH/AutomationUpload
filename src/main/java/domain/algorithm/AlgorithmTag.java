package domain.algorithm;

import java.util.Arrays;

public enum AlgorithmTag {
    IMPLEMENT("구현", "IMPLEMENTATION"),
    UNKNOWN("UNKNOWN", "UNKNOWN");
    private final String key;
    private final String value;
    private final static AlgorithmTag[] TAGS = values();
    AlgorithmTag(String key, String value) {
        this.key = key;
        this.value = value;
    }
    private String getTag() {
        return value;
    }
    private boolean findKey(String key) {
        return this.key.equals(key);
    }
    private static String unknownTag() {
        return UNKNOWN.value;
    }
    public static String findTag(String key) {
        return Arrays.stream(TAGS)
                .filter((tag) -> tag.findKey(key))
                .findFirst()
                .map(AlgorithmTag::getTag)
                .orElseGet(AlgorithmTag::unknownTag);
    }
}
