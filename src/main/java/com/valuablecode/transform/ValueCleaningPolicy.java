package com.valuablecode.transform;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Knows how data warehouse values should be cleaned by hard coding a list of cleanable strings.
 */
public class ValueCleaningPolicy {

    private static final Pattern cleanableStringRegExp = Pattern.compile(" |%|<|>|~|extended|venous");
    private static final Pattern leadingTextRegExp = Pattern.compile("(\\S)+\\s");

    public String clean(String dataWarehouseValue) {
        if (dataWarehouseValue == null) return null;
        
        return removeCleanableStrings(keepOnlyLeadingText(dataWarehouseValue));
    }

    private String keepOnlyLeadingText(String dataWarehouseValue) {
        Matcher matcher = leadingTextRegExp.matcher(dataWarehouseValue);
        
        return matcher.find() ? matcher.group() : dataWarehouseValue;
    }

    private String removeCleanableStrings(String dataWarehouseValue) {
        return cleanableStringRegExp.matcher(dataWarehouseValue).replaceAll("");
    }

}
