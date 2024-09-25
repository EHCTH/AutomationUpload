package domain.algorithm.problem;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class ExtensionTest {
    @Test
    public void ExtensionTest() {
        String extension = Extension.findExtension("py");
        Assertions.assertThat(extension).isEqualTo("py");
    }
}
