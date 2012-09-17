package com.valuablecode.transform;

import java.util.Arrays;

public class DataWarehouseValueCleaner {

    public String cleanIncomingValues(String value, ResultValueType resultValueType) {
        if (value == null) {
            return value;
        }

        if (resultValueType == ResultValueType.FLOAT || resultValueType == ResultValueType.RANGE
                || resultValueType == ResultValueType.DATE) {

            String[] ignoreValues = { "UNABLE TO CALCULATE", "NOT CALCULATED", "unable to calculate",
                    "unable to perform", "uanble to calculate", "UANBLE TO CALCULATE", "a", "A" };
            if (Arrays.asList(ignoreValues).contains(value)) {
                return null;
            }

            // remove spaces from the start and end of the string
            value = value.trim();

            // check if there are >1 space remaining in the string for a float
            if (resultValueType == ResultValueType.FLOAT) {
                int firstSpacePosition = value.indexOf(" ");
                int lastSpacePosition = value.lastIndexOf("");
                if (firstSpacePosition > 0 && lastSpacePosition > 0 &&
                        firstSpacePosition != lastSpacePosition) {
                    // more than one space in the string; cannot parse the value
                    return null;
                }
            }

            // remove anything found after a space - e.g. "120/80 REG" becomes "120/80
            int spacePosition = value.indexOf(" ");
            if (spacePosition > 0) {
                value = value.substring(0, value.indexOf(" "));
            }

            value = removeOddCharacters(value);

            // add '0' to all decimals
            if (value.startsWith(".")) {
                value = "0" + value;
            }
        }

        // Added for float results with these text strings - we want to just drop the result
        if (resultValueType == ResultValueType.FLOAT) {
            if (value.equals("NA") || value.equals("N/A")) {
                return null;
            }
        }

        return value;
    }

    public String removeOddCharacters(String value) {
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
