package org.example;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.awt.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class Utils {

    public static void login(String login, String password) {
        //set login and password
        $("[name='username']").setValue(login);
        $("[name='password']").setValue(password);
        $(By.cssSelector("button[type='submit']")).click();
        //$(byText("Сообщения")).shouldBe(Condition.visible);
        $(byText("Messages")).shouldBe(Condition.visible);
    }

    public static void beepOne() throws InterruptedException {
        Toolkit.getDefaultToolkit().beep();
        Thread.sleep(1000);
    }
    public static void beepThree() throws InterruptedException {
        beepOne();
        beepOne();
        beepOne();
    }
}
