package infrastructure.selenium.css;

public enum BySelector {
    // TODO 여기에 key = type, value = 형식으로 넣어야겠다
    ID("input[name='login_user_id']"),
    PASS("input[name='login_password']"),
    LOGIN_SUBMIT_BUTTON("#submit_button"),
    USER_PAGE("a.username"),
    USER_SOLVED_PAGE("#statics > tbody > tr:nth-child(2) > td > a"),
    USER_SOLVED_PROBLEM_PAGE("ul.pagination a"),

    PROBLEM_SET("#problemset > tbody > tr > td:nth-child(2) > a"),
    MY_SUBMIT("ul.problem-menu > li:nth-child(7) > a"),
    ALGORITHM_TAG("ul.spoiler-list a.spoiler-link"),
    STATUS_TABLE("#status-table > tbody > tr"),
    RESULT_TABLE("td:nth-child(4) > span.result-text"),
    PROBLEM_NUMBER("td:nth-child(3) > a"),
    PROBLEM_SOLVE_LANGUAGE("td:nth-child(7) > a"),
    SOURCE_CODE("textarea.no-mathjax.codemirror-textarea");

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
    public static String getProblemSet() {
        return PROBLEM_SET.selector;
    }
    public static String getMySubmit() {
        return MY_SUBMIT.selector;
    }
    public static String getAlgorithmTag() {
        return ALGORITHM_TAG.selector;
    }
    public static String getStatusTable() {
        return STATUS_TABLE.selector;
    }
    public static String getResultTable() {
        return RESULT_TABLE.selector;
    }
    public static String getProblemNumber() {
        return PROBLEM_NUMBER.selector;
    }
    public static String getSolveLanguage() {
        return PROBLEM_SOLVE_LANGUAGE.selector;
    }
    public static String getSourceCode() {
        return SOURCE_CODE.selector;
    }
}
