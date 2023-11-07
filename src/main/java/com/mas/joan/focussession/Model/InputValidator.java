package com.mas.joan.focussession.Model;

public class InputValidator {
    public boolean isOnlyLetters(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return true;
            }
        }
        return false;
    }
}
