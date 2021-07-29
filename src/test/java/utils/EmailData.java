package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class EmailData {
public static String createEmail(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers) + "@mailinator.com";
    }
}