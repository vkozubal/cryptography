package main.java.generators;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by kozubal on 12/7/13.
 */
public class FromTextGenerator extends RandomSequenceGenerator {
    public static final char ONE = '1';
    private final String FILE = "/projects/cryptographyFinal/core/src/main/resources/englishtext";

    public FromTextGenerator() {
    }

    @Override
    public ArrayList<Boolean> generateSequence() {
        ArrayList<Boolean> randomSequence = new ArrayList<>(randomSequenceSize);

        BufferedReader reader;
        int c, iter = randomSequenceSize / 8;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE), Charset.forName("UTF-8")));

            reader.read();
            reader.skip((long) (Math.random() * 10000 + 1));

            while ((c = reader.read()) != -1 && iter > 0) {
                if ((c >= 97 && c <= 122)) {// lowercase
                    iter--;
                    String z = Integer.toBinaryString(c);
                    while (z.length() < 8) {
                        z = "0" + z;
                    }
                    for (int k = 0; k < 8; k++) {
                        randomSequence.add(z.charAt(k) == ONE);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND .....");
        }
        return randomSequence;
    }

    @Override
    public String getGeneratorDescription() {
        return "\nFromTextGenerator";
    }
}