package domain.algorithm.problem;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SourceCodeTest {
    @Test
    public void presentSourceCodeTest() {
        SourceCode sourceCodeObj = new SourceCode(
                "a, b = map(int, input().split())\n" +
                        "print(a+b)\n");
        String sourceCode = sourceCodeObj.getSourceCode();
        assertThat(sourceCode).isEqualTo(
                "a, b = map(int, input().split())\n" +
                        "print(a+b)\n");
    }
    @Test
    public void absentSourceCodeTest() {
        assertThatThrownBy(() -> new SourceCode("")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 소스 코드가 없습니다");
    }
}
