package domain.algorithm.problem;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;


public class ExtensionTest {

    @Test
    public void presentExtensionTest() {
        List<String> extensionInfo = List.of("Python 3", "C++17", "C99", "java 17",
                "Ruby", "Kotlin (JVM)", "Rust 2018", "PyPy3"
        );
        List<String> expectedInfo = List.of("py", "cpp", "c", "java",
                "rb", "kt", "rs", "py"
        );
        int size = extensionInfo.size();
        IntStream.range(0, size).forEach(
                (number) -> {
                    Extension extension = Extension.findExtension(extensionInfo.get(number));
                    String fileExtension = extension.getExtension();
                    assertThat(fileExtension).isEqualTo(expectedInfo.get(number));
                }
        );
    }
    @Test
    public void absentExtensionTest() {
        assertThatThrownBy(() -> Extension.findExtension("예외발생"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 없는 확장자입니다 좀 많이 쓰는 걸 쓰십쇼 아님 직접추가하세요");

    }
}
