package ru.netology.web.data;
import lombok.AllArgsConstructor;
import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
        }

    public static VerificationCode getVerificationCodeFor() {
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class TransmissionInfo {
        String card;
    }

    public static TransmissionInfo getFirstCardNumber() {
        return new TransmissionInfo("5559000000000001");
    }

    public static TransmissionInfo getSecondCardNumber() {
        return new TransmissionInfo("5559000000000002");
    }

    public static TransmissionInfo getEmptyCardNumber() {
        return new TransmissionInfo("");
    }

    public static TransmissionInfo getNotCorrectCardNumber() {
        return new TransmissionInfo("5555000000000000");
    }

    public static int getExpectedBalanceIfBalanceRise(int balance, int amount) {
        return balance + amount;
    }

    public static int getExpectedBalanceIfBalanceDecrease(int balance, int amount) {
        return balance - amount;
    }
}

