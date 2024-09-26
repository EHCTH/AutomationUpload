import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LoggerTest.class);
        logger.trace("Trace");
        logger.debug("Debug");
        logger.info("Info");
        logger.warn("Warn");
        logger.error("Error");
    }
}
