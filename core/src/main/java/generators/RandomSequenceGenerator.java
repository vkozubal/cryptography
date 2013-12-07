package main.java.generators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozubal on 12/7/13.
 */
public abstract class RandomSequenceGenerator {
    protected final int randomSequenceSize = 8388608;

    public abstract ArrayList<Boolean> generateSequence();

    public abstract String getGeneratorDescription();

    protected void generateintialState(List<Integer> coeficients, ArrayList<Boolean> INITSTATE, int len) {
        for (int i = 0; i < len; i++) {
            INITSTATE.add(i, coeficients.contains(i));
        }
    }
}
