package com.twu.refactoring;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int optionInt(Option option) {
        IntPredicate intPredicate = null;
        switch (option) {
            case EVEN:
                intPredicate = num -> num % 2 == 0;
                break;
            case ODD:
                intPredicate = num -> num % 2 == 1;
                break;
            case POSITIVE:
                intPredicate = num -> num >= 0;
                break;
            case NEGATIVE:
                intPredicate = num -> num < 0;
        }
        return (int) Arrays.stream(numbers).filter(intPredicate).count();
    }
}
