package com.knox.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void find_sushu() {
        long[] ans = LongStream.range(2, 10000).filter(num -> {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0)
                    return false;
            }
            return true;
        }).toArray();
        System.out.println("count: " + ans.length + ", " + Arrays.toString(ans));
    }
}