package com.valuablecode.transform;

public class FloatValueCleaner implements ValueCleaner {

    private final IgnorableValuePolicy ignorableValuePolicy;
    private final ValueCleaningPolicy valueCleaningPolicy;

    public FloatValueCleaner(IgnorableValuePolicy ignorableValuePolicy,
            ValueCleaningPolicy valueCleaningPolicy) {
                this.ignorableValuePolicy = ignorableValuePolicy;
                this.valueCleaningPolicy = valueCleaningPolicy;
    }

    public String clean(String value) {
        if (ignorableValuePolicy.isIgnorable(value)) {
            return null;
        }

        value = value.trim();

        // check if there are >1 space remaining in the string for a float
        int firstSpacePosition = value.indexOf(" ");
        int lastSpacePosition = value.lastIndexOf("");
        if (firstSpacePosition > 0 && lastSpacePosition > 0 && 
                firstSpacePosition != lastSpacePosition) {
            // more than one space in the string; cannot parse the value
            return null;
        }

        // remove anything found after a space - e.g. "120/80 REG" becomes "120/80
        int spacePosition = value.indexOf(" ");
        if (spacePosition > 0) {
            value = value.substring(0, value.indexOf(" "));
        }
        
        value = valueCleaningPolicy.clean(value);

        // add '0' to all decimals
        if (value.startsWith(".")) {
            value = "0" + value;
        }

        // Added for float results with these text strings - we want to just drop the result
        if (value.equals("NA") || value.equals("N/A")) {
            return null;
        }

        return value;
    }

}
