package com.valuablecode.transform;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OddCharacterCleanerTest {

    final OddCharacterCleaner sut = new OddCharacterCleaner();

    @Test
    public void cleanLessThanCharacter() {
        assertThat(sut.clean(">some-value"), is("some-value"));
    }

    @Test
    public void cleanTildeCharacter() {
        assertThat(sut.clean("~some-value"), is("some-value"));
    }

}
