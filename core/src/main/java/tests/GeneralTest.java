package main.java.tests;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by kozubal on 12/7/13.
 */
public abstract class GeneralTest {
    protected final static double[] ALPHAS = {0.01, 0.05, 0.1};
    protected final double[] QUANTILES = {2.3263, 1.6449, 1.2816};
    protected final static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#####");

    public abstract void runTest(List<Boolean> sequence);

    protected int convertBooleanListToInt(List<Boolean> list) {
        int result = 0;
        for (Boolean bit : list) {
            if (bit) {
                result++;
            }
            result <<= 1;
        }

        int result2 = 0;
        assert (list.size() == 8);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)) {
                result2 += (int) Math.pow(2, 7 - i);
            }
        }
        result >>= 1;
        assert (result == result2);
        return result;
    }

    protected void format(double Xi2, List<Double> statistic) {
        System.out.println("Xi^2 is: " + DECIMAL_FORMAT.format(Xi2));

        for (int i = 0; i < ALPHAS.length; i++) {
//            System.out.print("Alpha equals " + ALPHAS[i] + " and Xi(1-a)^2 equals: " +
//                    DECIMAL_FORMAT.format(statistic.get(i)));
            System.out.print("Alpha:  " + ALPHAS[i] + "    ");

            System.out.print(Xi2 <= statistic.get(i) ? " .... Passed\n" : " .... Failed\n");
        }
    }
}
