package domain.algorithm.problem;

import java.util.Arrays;

public class ProblemNumber {
    private final String number;
    public String getNumber() {
        return number;
    }
    public ProblemNumber(String number) {
        validateBlank(number);
        this.number = number.substring(number.lastIndexOf("/"));;
    }

    private void validateBlank(String number) {
        if (number == null || number.trim().isEmpty()) throw new IllegalArgumentException("[ERROR] 없는 번호입니다");
    }
}
