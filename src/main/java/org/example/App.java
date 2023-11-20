package org.example;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        boolean isFetchProfilesOn = true;
        boolean isLikeAndFollowOn = true;
        String file = "inst_automation.txt";

       if(isFetchProfilesOn) {


            FetchProfiles fetchProfiles = new FetchProfiles(
                    "login",
                    "password",
                    "https://instagram.com/profile_to_fetch_followers");
            Set<String> profiles = fetchProfiles.go();
            saveToFile(file, profiles);
        }


        System.out.println("Success!");

    }

    public static Set<String> readFile(String filePath) {
        Set<String> profiles = new LinkedHashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming each line in the file is a URL
                profiles.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return profiles;
    }

    public static void saveToFile(String filePath, Set<String> profiles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String str : profiles) {
                writer.write(str);
                writer.newLine(); // Add a newline after each string to separate them.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
