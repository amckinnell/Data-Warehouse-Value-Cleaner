package com.valuablecode.transform;

public class DateValueCleaner implements ValueCleaner {

    private final IgnorableValuePolicy ignorableValuePolicy;
    private final ValueCleaningPolicy valueCleaningPolicy;

    public DateValueCleaner(IgnorableValuePolicy ignorableValuePolicy, 
            ValueCleaningPolicy valueCleaningPolicy) {
        this.ignorableValuePolicy = ignorableValuePolicy;
        this.valueCleaningPolicy = valueCleaningPolicy;
    }

    public String clean(String value) {
        if (ignorableValuePolicy.isIgnorable(value)) {
            return null;
        }

        value = value.trim();

        return valueCleaningPolicy.clean(value);
    }

}
