package com.valuablecode.transform;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ValueCleaningPolicyTest {

    private final String expected;
    private final String dataWarehouseValue;

    public ValueCleaningPolicyTest(String expected, String dataWarehouseValue) {
        this.expected = expected;
        this.dataWarehouseValue = dataWarehouseValue;
    }

    @Parameters
    public static Collection<Object[]> durationValues() {
        return asList(new Object[][] { 
                { "50", "50%" }, 
                { "50", "50% 75%" }, 
                { "75.2", "<75.2" }, 
                { "16.8", ">16.8" },
                { "25.0", "~25.0" }, 
                { "", " extended" }, 
                { "", "venous" },

                { "120/80", "120/80 REG" },
                { "16.8", ">16.8  APPROX" },

                { null, null }, 
                });
    }

    @Test
    public void should_clean_data_warehouse_values() {
        ValueCleaningPolicy sut = new ValueCleaningPolicy();

        assertThat(sut.clean(dataWarehouseValue), equalTo(expected));
    }

}
