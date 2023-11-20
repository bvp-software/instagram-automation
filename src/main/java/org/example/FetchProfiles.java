package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.example.Utils.*;

@AllArgsConstructor
public class FetchProfiles {

    private String login;
    private String password;
    private String targetProfileUrl;


    /**
     * Call this methos to start profile fetch.
     * Do not forget to call class constructor(...) to configure the fetcher.
     *
     * @return
     * @throws InterruptedException
     */
    public Set<String> go() throws InterruptedException {
        Configuration.timeout = 10000; //10 seconds
        open("https://www.instagram.com/");
        Utils.login(this.login, this.password);
        open(this.targetProfileUrl+"/followers/");
        String profileName = this.targetProfileUrl.split("\\/")[this.targetProfileUrl.split("\\/").length-1];
        $(byText(profileName)).shouldBe(Condition.visible);
        //$("div._aano").shouldBe(visible);

        Thread.sleep(15000); //15 sec

        int initialSize = 0;
        int currentSize = -1;
        int i = 0;

        while (currentSize != initialSize) {
            // Update the initial size with the current size
            initialSize = currentSize;
            scroll();
            long delay = getRandomDelay(2000, 3000);
            System.out.println("Delay:" + delay);
            Thread.sleep(delay); //1.5 - 6 seconds

            // Refresh the list of elements
            ElementsCollection elements = Selenide.$$("div._aano > div > div > div");

            // Update the current size
            currentSize = elements.size();

            i++;

            if (i % 10 == 0) { //each 10 iterations make beep and delay
                beepOne();
                Thread.sleep(60000); //1 minutes
            }
            if(i == 200) break;

            System.out.println("Fetch iteration count:" + i);
        }

        beepThree();
        System.out.println("--Scroll finished in " + i + " iterations--");
        return getProfiles();
    }



    private void scroll() {
        Selenide.executeJavaScript("document.querySelector('._aano').scrollBy(0, 700);");
    }


    private Set<String> getProfiles() {
        Set<String> profiles = new HashSet<>();
        ElementsCollection followers = Selenide.$$("div._aano > div > div > div a"); //list of contacts

        System.out.println("List Size:" + followers.size());

        for (int i = 0; i < followers.size(); i++) {
            String profileUrl = followers.get(i).getAttribute("href");
            profiles.add(profileUrl);
            System.out.println("Fetching profiles:" + i);
        }

        return profiles;
    }

    private long getRandomDelay(int min, int max) {
        Random random = new Random();
        return random.nextLong(max - min + 1) + min;
    }

}
