package test;

import com.company.Day14;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    private final Day14 day14Example = new Day14("inputExample.txt");
    private final Day14 day14 = new Day14("input.txt");

    public Day14Test() throws IOException {
    }

    @Test
    void day14a(){
        assertEquals(1588L, day14Example.day14a());
        assertEquals(3408L, day14.day14a());
    }

    @Test
    void day14b() {
        assertEquals(2188189693529L, day14Example.day14b());
        assertEquals(3724343376942L, day14.day14b());
    }
}