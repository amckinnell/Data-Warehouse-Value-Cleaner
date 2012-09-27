package com.valuablecode.transform;


public class DataWarehouseValueCleaner {
    
    private final IgnorableValuePolicy ignorableValuePolicy = new IgnorableValuePolicy();
    private final ValueCleaningPolicy valueCleaningPolicy = new ValueCleaningPolicy();

    public String cleanIncomingValues(String value, ResultValueType resultValueType) {
        if (value == null) return value;
        
        return asCleaner(resultValueType).clean(value);
    }
    
    private ValueCleaner asCleaner(ResultValueType resultValueType) {
        switch (resultValueType) {
        case COMPOUND:
            return new CompoundValueCleaner();
            
        case DATE:
            return new DateValueCleaner(ignorableValuePolicy, valueCleaningPolicy);

        case FLOAT:
            return new FloatValueCleaner(ignorableValuePolicy, valueCleaningPolicy);

        case RANGE:
            return new RangeValueCleaner(ignorableValuePolicy, valueCleaningPolicy);
        
        case TEXT:
            return new TextValueCleaner();
            
        default:
            return null;
        }
    }

}
