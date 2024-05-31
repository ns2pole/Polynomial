package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    @org.junit.jupiter.api.Test
    void add() {
        Polynomial testData1_1 =new Polynomial(new ArrayList<>(List.of(new Ratio(2, 1), new Ratio(3, 2), new Ratio(4, 1))));
        Polynomial testData1_2 =new Polynomial(new ArrayList<>(List.of(new Ratio(1, 2), new Ratio(3, 2), new Ratio(1, 1))));
        Polynomial result1 = testData1_1.add(testData1_2);
        Polynomial expected1 = new Polynomial(new ArrayList<>(List.of(new Ratio(2, 5), new Ratio(3, 4), new Ratio(4, 5))));

        Polynomial testData2_1 =new Polynomial(new ArrayList<>(List.of(new Ratio(1, 0), new Ratio(1, 0), new Ratio(4, 1))));
        Polynomial testData2_2 =new Polynomial(new ArrayList<>(List.of(new Ratio(1, 2), new Ratio(3, 2), new Ratio(1, 1))));
        Polynomial result2 = testData2_1.add(testData2_2);
        Polynomial expected2 = new Polynomial(new ArrayList<>(List.of(new Ratio(1, 2), new Ratio(3, 2), new Ratio(4, 5))));

        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }
}