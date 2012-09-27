package com.valuablecode.transform;

import static java.util.Arrays.asList;

import java.util.List;

public class IgnorableValuePolicy {
    
    final static List<String> ignorableValues = asList(
            "unable to calculate",
            "a",
            "uanble to calculate",
            "not calculated",
            "unable to perform"
            ); 

    public boolean isIgnorable(String value) {
        if (value == null) return true;
        
        return ignorableValues.contains(value.toLowerCase());
    }

}
