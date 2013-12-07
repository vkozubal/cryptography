package main.java.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Критерій перевірки однорідності двійкової послідовності
 * Created by kozubal on 12/7/13.
 */
public class test_3 extends GeneralTest {
    private static final int R = 10;
    private int R_SIZE;
    private int sequence_length;

    @Override
    public void runTest(List<Boolean> sequence) {
        int[][] byteCounter;
        sequence_length = sequence.size();
        R_SIZE = sequence_length / R;
        byteCounter = calculateStatistic(sequence);
        double Xi2 = calculateResult(byteCounter);
        output(Xi2);
    }

    private double calculateResult(int[][] byteCounter) {
        double Xi2 = 0;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < R; j++) {
                int vi = 0;
                for (int k = 0; k < R; k++) {
                    vi += byteCounter[i][k];
                }
                if (byteCounter[i][j] != 0) {
                    Xi2 += Math.pow(byteCounter[i][j], 2) / (vi * (R_SIZE / 8));
                }
            }
        }
        return (--Xi2 * (sequence_length / 8));

    }

    private int[][] calculateStatistic(List<Boolean> sequence) {

        int[][] byteCounter = new int[256][R];

        for (int i = 0, column = 0; i < sequence_length - R_SIZE; i += R_SIZE, column++) {
            for (int j = i; j < i + R_SIZE; j += 8) {
                int row = convertBooleanListToInt(sequence.subList(j, j + 8));
                byteCounter[row][column]++;
            }
        }
        return byteCounter;
    }

    protected void output(double Xi2) {
        List<Double> result = new ArrayList<>(ALPHAS.length);
        for (int i = 0; i < ALPHAS.length; i++) {
            result.add(Math.sqrt(2 * 255 * (R - 1)) * QUANTILES[i] + 255 * (R - 1));
        }
        format(Xi2, result);
    }
}
