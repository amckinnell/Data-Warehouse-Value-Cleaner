package com.valuablecode.transform;

public class RangeValueCleaner implements ValueCleaner {

    private final IgnorableValuePolicy ignorableValuePolicy;
    private final ValueCleaningPolicy valueCleaningPolicy;

    public RangeValueCleaner(IgnorableValuePolicy ignorableValuePolicy, 
            ValueCleaningPolicy valueCleaningPolicy) {
        this.ignorableValuePolicy = ignorableValuePolicy;
        this.valueCleaningPolicy = valueCleaningPolicy;
    }

    public String clean(String value) {
        if (ignorableValuePolicy.isIgnorable(value)) {
            return null;
        }

        // remove spaces from the start and end of the string
        value = value.trim();

        // remove anything found after a space - e.g. "120/80 REG" becomes "120/80
        int spacePosition = value.indexOf(" ");
        if (spacePosition > 0) {
            value = value.substring(0, value.indexOf(" "));
        }
        
        return valueCleaningPolicy.clean(value);
    }

}
