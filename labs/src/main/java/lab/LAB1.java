package main.java.lab;

import main.java.generators.BBSGenerator;
import main.java.generators.FromTextGenerator;
import main.java.generators.GiffyGenerator;
import main.java.generators.InlineGenerator;
import main.java.generators.LinearFeedbackShiftRegister;
import main.java.generators.RandomSequenceGenerator;
import main.java.tests.GeneralTest;
import main.java.tests.test_1;
import main.java.tests.test_2;
import main.java.tests.test_3;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kozubal on 12/7/13.
 */
public class LAB1 {

    private static List<RandomSequenceGenerator> generatorsList;
    private static List<GeneralTest> testList;

    static {
        List<Integer> coeficients1 = Arrays.asList(17, 15, 11, 0);
        List<Integer> coeficients2 = Arrays.asList(52, 1);

        generatorsList = Arrays.asList(new InlineGenerator(), new LinearFeedbackShiftRegister(coeficients1, 20),
                new LinearFeedbackShiftRegister(coeficients2, 90), new GiffyGenerator(), new FromTextGenerator(),
                new BBSGenerator());
        testList = Arrays.asList(new test_1(), new test_2(), new test_3());

    }

    public static void main(String[] args) {
        LAB1 firstLab = new LAB1();
        firstLab.run();

    }

    private void run() {
        for (RandomSequenceGenerator generator : generatorsList) {
            List<Boolean> list = generator.generateSequence();
            System.out.println(generator.getGeneratorDescription());
            for (GeneralTest test : testList) {
                test.runTest(list);
            }
        }
    }

}
