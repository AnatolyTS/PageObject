package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement FirstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private final SelenideElement SecondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");

    public DashboardPage() {
        SelenideElement heading = $("[data-test-id=dashboard]");
        heading.shouldBe(visible);
    }

    public TransmissionPage transmissionToFirstCard() {
        FirstCardButton.click();
        return new TransmissionPage();
    }

    public TransmissionPage transmissionToSecondCard() {
        SecondCardButton.click();
        return new TransmissionPage();
    }

    public int getCurrentBalanceOfFirstCard() {
        val currentBalance = $(".list__item [data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getText();
        return getBalance(currentBalance);
    }

    public int getCurrentBalanceOfSecondCard() {
        val currentBalance = $(".list__item [data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getText();
        return getBalance(currentBalance);
    }

    public int getBalance(String currentBalance) {
        val substring = currentBalance.split(",");
        val getArraysLength = substring[substring.length - 1];
        val value = getArraysLength.replaceAll("[^-0-9]+", "");
        return Integer.parseInt(value);
    }

}
