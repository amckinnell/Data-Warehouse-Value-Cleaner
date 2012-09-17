package com.valuablecode.transform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DataWarehouseValueCleanerTest {

    final DataWarehouseValueCleaner sut = new DataWarehouseValueCleaner();

    @Test
    public void removeLeadingGreaterThanCharacter() {
        assertThat(sut.removeOddCharacters(">some_value"), is("some_value"));
    }

    @Test
    public void removeLeadingTildeCharacter() {
        assertThat(sut.removeOddCharacters("~some_value"), is("some_value"));
    }

}
