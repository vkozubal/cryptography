package main.java.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kozubal on 12/7/13.
 */
public class LinearFeedbackShiftRegister extends RandomSequenceGenerator {

    private final ArrayList<Boolean> initialState;
    private static Random random = new Random();
    private final ArrayList<Boolean> polynomial;

    public LinearFeedbackShiftRegister(List<Integer> coeficients, int n) {
        ArrayList<Boolean> polinom = new ArrayList<>(n);
        generateintialState(coeficients, polinom, n);
        polinom.trimToSize();
        polynomial = polinom;
        initialState = generateInitialState(polynomial.size());
    }

    private ArrayList<Boolean> generateInitialState(int size) {
        ArrayList<Boolean> initial = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            initial.add(i, random.nextBoolean());
        }
        return initial;
    }

    public ArrayList<Boolean> generateSequence() {
        ArrayList<Boolean> randomSequence = new ArrayList<>(randomSequenceSize);

        ArrayList<Boolean> initialState_copy = new ArrayList<>(initialState);
        initialState_copy.ensureCapacity(2 * polynomial.size());

        lfsr(initialState_copy, polynomial, 2 * polynomial.size());

        for (Boolean i : initialState_copy.subList(initialState.size(), initialState_copy.size())) {
            randomSequence.add(i);
        }
        lfsr(randomSequence, polynomial, randomSequenceSize);
        return randomSequence;
    }

    private List<Boolean> lfsr(List<Boolean> sequence, List<Boolean> pattern, int len) {
        for (int i = pattern.size(); i < len; ++i) {
            int sum = 0;
            for (int j = 0; j < pattern.size(); ++j) {
                if (pattern.get(j) & sequence.get(i - pattern.size() + j)) {
                    sum++;
                }
            }
            sequence.add(i, (sum % 2 == 0) ? Boolean.FALSE : Boolean.TRUE);
        }
        return null;
    }

    public String getGeneratorDescription() {
        return "\nLFSR Generator ";
    }
}