package org.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import java.util.Set;

import static com.codeborne.selenide.Condition.be;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@AllArgsConstructor
public class LikeAndFollowProfiles {

    private String login;
    private String password;
    private Set<String> targetProfileUrls;

    public void go() throws InterruptedException {
        Configuration.timeout = 10000; //10 seconds
        open("https://www.instagram.com/");
        Utils.login(this.login, this.password);

        int i=0;

        for(String targetProfile : targetProfileUrls) {
            open(targetProfile);
            //like
            //follow
            Thread.sleep(3000); //3 sec

            $(By.cssSelector("button._acan")).click(); //follow profile
            Thread.sleep(3500);

            // Check if an element with a specific CSS selector exists
            ElementsCollection elements = $$("div._aagu");
            if(elements.size() >0) {
                elements.get(0).click(); // Select the 2nd element
                Thread.sleep(2000);
                $(By.cssSelector("article._aatb ")).should(be(visible));
                $(By.cssSelector("span._aamw")).click(); //like
                Thread.sleep(2000);

                open(targetProfile);
                Thread.sleep(2000);

                elements.get(1).click();
                Thread.sleep(2000);
                $(By.cssSelector("article._aatb ")).should(be(visible));
                $(By.cssSelector("span._aamw")).click(); //like
                Thread.sleep(2000);

                open(targetProfile);
                Thread.sleep(2000);

                elements.get(2).click();
                Thread.sleep(2000);
                $(By.cssSelector("article._aatb ")).should(be(visible));
                $(By.cssSelector("span._aamw")).click(); //like
                Thread.sleep(2000);

                i++;
                if(i==20) {
                    break;
                }
            }

        }
    }
}
