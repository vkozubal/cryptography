package main.java.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Критерій перевірки незалежності знаків
 * Created by kozubal on 12/7/13.
 */
public class test_2 extends GeneralTest {
    private int sequence_length;

    @Override
    public void runTest(List<Boolean> sequence) {
        int[][] byteCounter;
        sequence_length = sequence.size();
        byteCounter = calculateStatistic(sequence);
        double Xi2 = calculateResult(byteCounter);
        output(Xi2);
    }

    protected void output(double Xi2) {

        List<Double> result = new ArrayList<>(ALPHAS.length);
        for (int i = 0; i < ALPHAS.length; i++) {
            result.add(Math.sqrt(2 * 255 * 255) * QUANTILES[i] + 255 * 255);
        }
        format(Xi2, result);
    }

    private int[][] calculateStatistic(List<Boolean> sequence) {
        int[][] byteCounter = new int[256][256];
        for (int i = 0; i < sequence.size() - 16; i += 16) {
            int row = convertBooleanListToInt(sequence.subList(i, i + 8));
            int column = convertBooleanListToInt(sequence.subList(i + 8, i + 16));
            byteCounter[row][column]++;
        }
        return byteCounter;
    }

    private double calculateResult(int[][] byteCounter) {
        double Xi2 = 0;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                int aj = 0, vi = 0;
                for (int k = 0; k < 256; k++) {
                    vi += byteCounter[i][k];
                    aj += byteCounter[k][j];
                }
                if (byteCounter[i][j] != 0) {
                    Xi2 += Math.pow(byteCounter[i][j], 2) / (vi * aj);
                }
            }
        }
        return (--Xi2 * (sequence_length / 16));
    }
}
