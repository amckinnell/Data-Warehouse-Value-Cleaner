package com.valuablecode.transform;

import static java.text.MessageFormat.format;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class IgnorableValuePolicyTest {

    private static boolean IGNORABLE = true;
    private static boolean NOT_IGNORABLE = false;

    private final String dataWarehouseValue;
    private final boolean expected;

    public IgnorableValuePolicyTest(boolean expected, String dataWarehouseValue) {
        this.expected = expected;
        this.dataWarehouseValue = dataWarehouseValue;
    }

    @Parameters
    public static Collection<Object[]> durationValues() {
        return asList(new Object[][] {
                     { NOT_IGNORABLE, "120/180" },
                     { NOT_IGNORABLE, "Mild Intermittent" },

                     { IGNORABLE, "A" },
                     { IGNORABLE, "a" },
                     { IGNORABLE, "UNABLE TO CALCULATE" },
                     { IGNORABLE, "unable to calculate" },
                     { IGNORABLE, "uanble to calculate" },
                     { IGNORABLE, "NOT CALCULATED" },
                     { IGNORABLE, "unable to perform" },

                     { IGNORABLE, null },
                     });
    }

    @Test
    public void detect_ignorable_data_warehouse_values() {
         IgnorableValuePolicy sut = new IgnorableValuePolicy();

         assertThat(reason(), sut.isIgnorable(dataWarehouseValue), equalTo(expected));
    }

    private String reason() {
        return format("Expected ''{0}'' to be an {1} data warehouse value.", dataWarehouseValue,
                        expected ? "ignorable" : "acceptable");

    }

}
