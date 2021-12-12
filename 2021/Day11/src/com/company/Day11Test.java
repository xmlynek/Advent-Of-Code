package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    private final Day11 day11Example = new Day11("inputExample.txt");
    private final Day11 day11 = new Day11("input.txt");

    public Day11Test() throws IOException {
    }

    @Test
    void day11a() {
        assertEquals(1656, day11Example.day11a());
        assertEquals(1571, day11.day11a());
    }

    @Test
    void day11b() {
        assertEquals(195, day11Example.day11b());
        assertEquals(387, day11.day11b());
    }
}