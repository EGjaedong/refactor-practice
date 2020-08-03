package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Direction {
    private final char direction;
    private List<Character> directionList = new ArrayList<>(Arrays.asList('N', 'E', 'N', 'W'));

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turnRight() {
        int index = directionList.indexOf(direction);
        if (index < directionList.size() - 1)
            return new Direction(directionList.get(index + 1));
        else
            return new Direction(directionList.get(0));
    }

    public Direction turnLeft() {
        int index = directionList.indexOf(direction);
        if (index > 0)
            return new Direction(directionList.get(index - 1));
        else
            return new Direction(directionList.get(directionList.size() - 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
