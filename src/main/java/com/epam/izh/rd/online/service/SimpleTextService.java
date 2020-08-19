package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    @Override
    public String removeString(String base, String remove) {
        return base.replaceAll(remove, "");
    }

    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    @Override
    public String concatenate(String... elements) {
        StringBuilder strBuilder = new StringBuilder();

        for (String s : elements) {
            strBuilder.append(s);
        }
        return new String(strBuilder);
    }

    @Override
    public String toJumpCase(String text) {
        StringBuilder strBuilder = new StringBuilder(text);
        for (int i = 0; i < strBuilder.length(); i++) {
            if ((i % 2) == 0) {
                strBuilder.setCharAt(i, Character.toLowerCase(strBuilder.charAt(i)));
            } else {
                strBuilder.setCharAt(i, Character.toUpperCase(strBuilder.charAt(i)));
            }
        }
        return new String(strBuilder);
    }

    @Override
    public boolean isPalindrome(String string) {
        string = string.replaceAll(" ", "");
        int strLength = string.length();

        if (strLength > 0) {
            for (int i = 0; i < strLength / 2; i++) {
                if (Character.toLowerCase(string.charAt(i)) != Character.toLowerCase(string.charAt(strLength - 1 - i)))
                    return false;
            }

            return true;
        }

        return false;
    }
}
