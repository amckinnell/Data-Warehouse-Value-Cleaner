package com.valuablecode.transform;

public class OddCharacterCleaner {

    public String clean(String value) {
        value = value.replaceAll("%", "");
        value = value.replaceAll("<", "");
        value = value.replaceAll(">", "");
        value = value.replaceAll("~", "");
        value = value.replaceAll("extended", "");
        value = value.replaceAll("venous", "");
        value = value.replaceAll(" ", "");
        value = value.replaceAll("%", "NOT CALCULATED");

        return value;
    }

}
