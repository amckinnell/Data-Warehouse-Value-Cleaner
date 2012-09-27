package com.valuablecode.transform;


public class DataWarehouseValueCleaner {
    
    private final IgnorableValuePolicy ignorableValuePolicy = new IgnorableValuePolicy();
    private final ValueCleaningPolicy valueCleaningPolicy = new ValueCleaningPolicy();

    public String cleanIncomingValues(String value, ResultValueType resultValueType) {
        if (value == null) return value;
        
        switch (resultValueType) {
        case COMPOUND:
            return new CompoundValueCleaner().clean(value);
            
        case DATE:
            return new DateValueCleaner(ignorableValuePolicy, valueCleaningPolicy).clean(value);

        case FLOAT:
            return new FloatValueCleaner(ignorableValuePolicy, valueCleaningPolicy).clean(value);

        case RANGE:
            return new RangeValueCleaner(ignorableValuePolicy, valueCleaningPolicy).clean(value);
        
        case TEXT:
            return new TextValueCleaner().clean(value);
            
        default:
            return null;
        }
    }

}
