package main.java.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kozubal on 12/7/13.
 */
public class GiffyGenerator extends RandomSequenceGenerator {

    List<Integer> coeficients1 = Arrays.asList(2, 0);
    List<Integer> coeficients2 = Arrays.asList(4, 3, 1, 0);
    List<Integer> coeficients3 = Arrays.asList(3, 0);

    @Override
    public ArrayList<Boolean> generateSequence() {
        ArrayList<Boolean> randomSequence = new ArrayList<>(randomSequenceSize);
        LinearFeedbackShiftRegister lsfr1 = new LinearFeedbackShiftRegister(coeficients1, 11);
        LinearFeedbackShiftRegister lsfr2 = new LinearFeedbackShiftRegister(coeficients2, 9);
        LinearFeedbackShiftRegister lsfr3 = new LinearFeedbackShiftRegister(coeficients3, 10);
        ArrayList<Boolean> seq1 = lsfr1.generateSequence();
        ArrayList<Boolean> seq2 = lsfr2.generateSequence();
        ArrayList<Boolean> seq3 = lsfr3.generateSequence();

        for (int i = randomSequenceSize - 1; i >= 0; i--) {
            randomSequence.add(randomSequenceSize - 1 - i, (seq1.get(i) & seq3.get(i)) ^ (!seq3.get(i) & seq2.get(i)));
        }
        return randomSequence;
    }

    @Override
    public String getGeneratorDescription() {
        return "\nGiffyGenerator";
    }
}

