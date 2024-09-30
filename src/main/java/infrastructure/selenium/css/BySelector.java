package infrastructure.selenium.css;

public enum BySelector {
    // TODO 여기에 key = type, value = 형식으로 넣어야겠다
    ID("input[name='login_user_id']"),
    PASS("input[name='login_password']"),
    LOGIN_SUBMIT_BUTTON("#submit_button"),
    USER_PAGE("a.username"),
    USER_SOLVED_PAGE("#statics > tbody > tr:nth-child(2) > td > a"),
    USER_SOLVED_PROBLEM_PAGE("ul.pagination a");

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
    public static String getUserPage() {
        return USER_PAGE.selector;
    }
    public static String getUserSolvedPage() {
        return USER_SOLVED_PAGE.selector;
    }
    public static String getSolvedProblemPage() {
        return USER_SOLVED_PROBLEM_PAGE.selector;
    }
}
