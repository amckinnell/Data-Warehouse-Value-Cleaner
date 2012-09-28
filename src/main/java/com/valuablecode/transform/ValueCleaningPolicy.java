package com.valuablecode.transform;

import java.util.regex.Pattern;


/**
 * Knows how data warehouse values should be cleaned by hard coding a list of cleanable strings.
 */
public class ValueCleaningPolicy {

    private static final Pattern cleanableStringRegExp = Pattern.compile(" |%|<|>|~|extended|venous");

    public String clean(String dataWarehouseValue) {
        if (dataWarehouseValue == null) return null;
        
        return removeCleanableStrings(removeTrailingText(dataWarehouseValue));
    }

    private String removeTrailingText(String value) {
        int spacePosition = value.indexOf(" ");
        
        if (spacePosition > 0) {
            value = value.substring(0, value.indexOf(" "));
        }
        
        return value;
    }

    private String removeCleanableStrings(String dataWarehouseValue) {
        return cleanableStringRegExp.matcher(dataWarehouseValue).replaceAll("");
    }

}
