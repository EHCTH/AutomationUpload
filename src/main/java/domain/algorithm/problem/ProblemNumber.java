package domain.algorithm.problem;


public class ProblemNumber {
    private final String number;
    public String getNumber() {
        return number;
    }
    public ProblemNumber(String number) {
        validateBlank(number);
        int idx = number.lastIndexOf("/") + 1;
        this.number = number.substring(idx);
    }

    private void validateBlank(String number) {
        if (number == null || number.trim().isEmpty()) throw new IllegalArgumentException("[ERROR] 없는 번호입니다");
    }
}
