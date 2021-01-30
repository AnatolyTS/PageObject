package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class TransmissionTest {
    @BeforeEach
    void setUp () {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val verificationPage = loginPage.validLogin(DataHelper.getAuthInfo());
        val verificationCode = DataHelper.getVerificationCodeFor();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransmitFromFirstToSecond() {
        val dashboardPage = new DashboardPage();
        val amount = 100;
        val currentBalanceOfFirstCard = dashboardPage.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = dashboardPage.getCurrentBalanceOfSecondCard();
        val transmissionPage = dashboardPage.transmissionToSecondCard();
        transmissionPage.transmission(getFirstCardNumber(), amount);

        val balanceOfFirstCardAfterTransmit = dashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfterTransmit = dashboardPage.getCurrentBalanceOfSecondCard();
        val expectedBalanceOfFirstCardAfterTransmit = getExpectedBalanceIfBalanceDecrease(currentBalanceOfFirstCard, amount);
        val expectedBalanceOfSecondCardAfterTransmit = getExpectedBalanceIfBalanceRise(currentBalanceOfSecondCard, amount);
        assertEquals(expectedBalanceOfFirstCardAfterTransmit, balanceOfFirstCardAfterTransmit);
        assertEquals(expectedBalanceOfSecondCardAfterTransmit, balanceOfSecondCardAfterTransmit);
    }

    @Test
    void shouldTransmitFromSecondToFirst() {
        val dashboardPage = new DashboardPage();
        val amount = 150;
        val currentBalanceOfFirstCard = dashboardPage.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = dashboardPage.getCurrentBalanceOfSecondCard();
        val transmissionPage = dashboardPage.transmissionToFirstCard();
        transmissionPage.transmission(getSecondCardNumber(), amount);

        val balanceOfFirstCardAfterTransmit = dashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfterTransmit = dashboardPage.getCurrentBalanceOfSecondCard();
        val expectedBalanceOfFirstCardAfterTransmit = getExpectedBalanceIfBalanceRise(currentBalanceOfFirstCard, amount);
        val expectedBalanceOfSecondCardAfterTransmit = getExpectedBalanceIfBalanceDecrease(currentBalanceOfSecondCard, amount);
        assertEquals(expectedBalanceOfFirstCardAfterTransmit, balanceOfFirstCardAfterTransmit);
        assertEquals(expectedBalanceOfSecondCardAfterTransmit, balanceOfSecondCardAfterTransmit);
    }

    @Test
    void shouldTransmitZeroAmount() {
        val dashboardPage = new DashboardPage();
        val amount = 0;
        val currentBalanceOfFirstCard = dashboardPage.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = dashboardPage.getCurrentBalanceOfSecondCard();
        val transmissionPage = dashboardPage.transmissionToSecondCard();
        transmissionPage.transmission(getFirstCardNumber(), amount);

        val balanceOfFirstCardAfterTransmit = dashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfterTransmit = dashboardPage.getCurrentBalanceOfSecondCard();
        val expectedBalanceOfFirstCardAfterTransmit = getExpectedBalanceIfBalanceDecrease(currentBalanceOfFirstCard, amount);
        val expectedBalanceOfSecondCardAfterTransmit = getExpectedBalanceIfBalanceRise(currentBalanceOfSecondCard, amount);
        assertEquals(expectedBalanceOfFirstCardAfterTransmit, balanceOfFirstCardAfterTransmit);
        assertEquals(expectedBalanceOfSecondCardAfterTransmit, balanceOfSecondCardAfterTransmit);
    }

    @Test
    void shouldBeErrorIfFromInputCardIsEmpty() {
        val dashboardPage = new DashboardPage();
        val amount = 1000;
        val transmissionPage = dashboardPage.transmissionToSecondCard();
        transmissionPage.transmission(getEmptyCardNumber(), amount);
        transmissionPage.errorTransmittion();
    }

    @Test
    void shouldBeErrorIfNotCorrectCardNumber() {
        val dashboardPage = new DashboardPage();
        val amount = 1000;
        val transmissionPage = dashboardPage.transmissionToSecondCard();
        transmissionPage.transmission(getNotCorrectCardNumber(), amount);
        transmissionPage.errorTransmittion();
    }

    @Test
    void shouldBeTransmitAmountEqualsCurrentBalance(){
        val dashboardPage = new DashboardPage();
        val amount = dashboardPage.getCurrentBalanceOfFirstCard();
        val currentBalanceOfFirstCard = dashboardPage.getCurrentBalanceOfFirstCard();
        val currentBalanceOfSecondCard = dashboardPage.getCurrentBalanceOfSecondCard();
        val transmissionPage = dashboardPage.transmissionToSecondCard();
        transmissionPage.transmission(getFirstCardNumber(), amount);

        val balanceOfFirstCardAfterTransmit = dashboardPage.getCurrentBalanceOfFirstCard();
        val balanceOfSecondCardAfterTransmit = dashboardPage.getCurrentBalanceOfSecondCard();
        val expectedBalanceOfFirstCardAfterTransmit = getExpectedBalanceIfBalanceDecrease(currentBalanceOfFirstCard, amount);
        val expectedBalanceOfSecondCardAfterTransmit = getExpectedBalanceIfBalanceRise(currentBalanceOfSecondCard, amount);
        assertEquals(expectedBalanceOfFirstCardAfterTransmit, balanceOfFirstCardAfterTransmit);
        assertEquals(expectedBalanceOfSecondCardAfterTransmit, balanceOfSecondCardAfterTransmit);
    }
}
