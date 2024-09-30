package infrastructure.selenium.css;

public enum BySelector {
    // TODO 여기에 key = type, value = 형식으로 넣어야겠다
    ID("input[name='login_user_id']"),
    LOGIN_SUBMIT_BUTTON("#submit_button"),
    USER_PAGE("a.username"),
    PASS("input[name='login_password']");

    private final String selector;
    BySelector(String selector) {
        this.selector = selector;
    }
    public String getSelector() {
        return selector;
    }
    public static String getId() {
        return ID.selector;
    }
    public static String getPass() {
        return PASS.selector;
    }
    public static String getLoginSubmitButton() {
        return LOGIN_SUBMIT_BUTTON.selector;
    }
}
/*
    void writeId(User user) {
        WebElement loginUserId = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("login_user_id")));
        loginUserId.sendKeys(user.getUserId());
        logger.info("Your ID : {}", user.getUserId());
    }
    void writePass(User user) {
        WebElement loginPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("login_password")));
        loginPassword.sendKeys(user.getUserPass());
        logger.info("Your PASS : {}", user.getUserPass());

    }
 */
