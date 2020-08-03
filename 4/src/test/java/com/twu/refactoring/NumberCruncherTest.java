package com.twu.refactoring;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NumberCruncherTest {
    @Test
    public void shouldCountEvenNumbers() {
        int evens = new NumberCruncher(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).optionInt(Option.EVEN);
        assertThat(evens, is(5));
    }

    @Test
    public void shouldCountOddNumbers() {
        int odds = new NumberCruncher(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11).optionInt(Option.ODD);
        assertThat(odds, is(6));
    }

    @Test
    public void shouldCountPositiveNumbers() {
        int positives = new NumberCruncher(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4).optionInt(Option.POSITIVE);
        assertThat(positives, is(5));
    }

    @Test
    public void shouldCountNegativeNumbers() {
        int negatives = new NumberCruncher(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4).optionInt(Option.NEGATIVE);
        assertThat(negatives, is(5));
    }
}
