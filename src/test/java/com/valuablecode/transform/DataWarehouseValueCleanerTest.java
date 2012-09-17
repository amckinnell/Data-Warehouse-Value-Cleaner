package com.valuablecode.transform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DataWarehouseValueCleanerTest {

    @Test
    public void removeLeadingGreaterThanCharacter() {
        assertThat(DataWarehouseValueCleaner.removeOddCharacters(">some_value"), is("some_value"));
    }

    @Test
    public void removeLeadingTildeCharacter() {
        assertThat(DataWarehouseValueCleaner.removeOddCharacters("~some_value"), is("some_value"));
    }

}
