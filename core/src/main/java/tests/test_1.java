package main.java.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Критерій перевірки рівноімовірності знаків
 * Created by kozubal on 12/7/13.
 */
public class test_1 extends GeneralTest {
    private int sequence_length;

    @Override
    public void runTest(List<Boolean> sequence) {
        sequence_length = sequence.size();
        double nj = sequence_length / (8 * 256);
        int[] byteCounter = new int[256];
        for (int i = 0; i < sequence_length - 8; i += 8) {
            List<Boolean> y = sequence.subList(i, i + 8);
            int number = convertBooleanListToInt(y);
            byteCounter[number]++;
        }

        double Xi2 = 0;
        for (int i = 0; i < 256; i++) {
            Xi2 += Math.pow(byteCounter[i] - nj, 2) / nj;
        }

        List<Double> result = new ArrayList<>(ALPHAS.length);
        for (int i = 0; i < ALPHAS.length; i++) {
            result.add(Math.sqrt(2 * 255) * QUANTILES[i] + 255);
        }
        format(Xi2, result);
    }
}
