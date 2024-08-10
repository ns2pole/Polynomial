package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiPolynomialTest {

    //1/2 * a^2
    MultiMonomial m1 = new MultiMonomial(
            new Ratio(2,1),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,2))
            )
    );


    //1/4 * a^2
    MultiMonomial m2 = new MultiMonomial(
            new Ratio(4,1),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,2))
                    )
    );

    //2/3 * a^1b^2
    MultiMonomial m3 = new MultiMonomial(
            new Ratio(3,2),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,1)),
                    new Pair<>('b', new Ratio(1,2)))
    );

    //4/3 a^1b^2
    MultiMonomial m4 = new MultiMonomial(
            new Ratio(3,4),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,1)),
                    new Pair<>('b', new Ratio(1,2)))
    );

    //2/3 * a^2c^1
    MultiMonomial m5 = new MultiMonomial(
            new Ratio(3,2),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,2)),
                    new Pair<>('c', new Ratio(1,1)))
    );

    //2/3
    MultiMonomial m6 = new MultiMonomial(
            new Ratio(3,2),
            Arrays.asList());

    //7/3
    MultiMonomial m7 = new MultiMonomial(
            new Ratio(3,7),
            Arrays.asList());

    //3/4 * a^2
    MultiMonomial resultm1 = new MultiMonomial(
            new Ratio(4,3),
            Arrays.asList(new Pair<>('a', new Ratio(1,2)))
    );

    //2/1 * a^1b^2
    MultiMonomial resultm2 = new MultiMonomial(
            new Ratio(1,2),
            Arrays.asList(
                    new Pair<>('a', new Ratio(1,1)),
                    new Pair<>('b', new Ratio(1,2)))
    );

    //3/1
    MultiMonomial resultm3 = new MultiMonomial(
            new Ratio(1,3),
            Arrays.asList());

    @Test
    void plus() {

        //変数1つの項について足し算が計算できる
        //(1/2 * a^2 + 2/3 * a^2c^1) +
        //(1/4 * a^2 + 4/3 * a^1b^2)
        //(3/4 * a^2 + 2/3 * a^2c^1 + 4/3 * a^1b^2)
        MultiPolynomial mp1_1 = new MultiPolynomial(
                Arrays.asList(m1, m5));
        MultiPolynomial mp1_2 = new MultiPolynomial(
                Arrays.asList(m2, m4));
        MultiPolynomial expected1 = new MultiPolynomial(
                Arrays.asList(resultm1, m5, m4));
        assertEquals(expected1 , mp1_1.plus(mp1_2));

        //多変数の項について足し算が計算できる
        //(1/2 * a^2 + 2/3 * a^1b^2) +
        //(1/4 * a^2 + 4/3 * a^1b^2)
        //(3/4 * a^2 + 2/1 * a^1b^2)
        MultiPolynomial mp2_1 = new MultiPolynomial(
                Arrays.asList(m1, m3));
        MultiPolynomial mp2_2 = new MultiPolynomial(
                Arrays.asList(m2, m4));
        MultiPolynomial expected2 = new MultiPolynomial(
                Arrays.asList(resultm1, resultm2));
        assertEquals(expected2 , mp2_1.plus(mp2_2));

        //定数項がある場合でも計算できる
        //(1/2 * a^2 + 2/3) +
        //(1/4 * a^2 + 7/3)
        //(3/4 * a^2 + 3/1)
        MultiPolynomial mp3_1 = new MultiPolynomial(
                Arrays.asList(m1, m6));
        MultiPolynomial mp3_2 = new MultiPolynomial(
                Arrays.asList(m2, m7));
        MultiPolynomial expected3 = new MultiPolynomial(
                Arrays.asList(resultm1, resultm3));
        assertEquals(expected3 , mp3_1.plus(mp3_2));
    }
}