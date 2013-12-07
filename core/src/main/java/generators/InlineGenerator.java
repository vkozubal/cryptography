package main.java.generators;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kozubal on 12/7/13.
 */
public class InlineGenerator extends RandomSequenceGenerator {
    @Override
    public ArrayList<Boolean> generateSequence() {
        ArrayList<Boolean> randomSequence = new ArrayList<>(randomSequenceSize);
        Random random = new Random();

        for (int i = 0; i < randomSequenceSize; i++) {
            randomSequence.add(random.nextBoolean());
        }
        return randomSequence;
    }

    @Override
    public String getGeneratorDescription() {
        return "\nInlineGenerator";
    }
}