package main.java.generators;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kozubal on 12/7/13.
 */

public class BBSGenerator extends RandomSequenceGenerator {

    private static BigInteger P;
    private static BigInteger Q;
    final static BigInteger TWO = new BigInteger("10", 2);

    public BBSGenerator() {
        P = new BigInteger("DD4ABECC719B945481602AD72BA43B7E5998C07EB", 16);
        Q = new BigInteger("6C5CF455F6FEE7B8DE5D2D041E80821A207F440253", 16);
    }

    @Override
    public ArrayList<Boolean> generateSequence() {
        ArrayList<Boolean> randomSequence = new ArrayList<>(randomSequenceSize);
        BigInteger N = P.multiply(Q);

        BigInteger Ri = new BigInteger(128, new Random());
        BigInteger Rb;

        for (int i = 1; i <= randomSequenceSize; i++) {
            Ri = Ri.multiply(Ri);
            Ri = Ri.mod(N);
            Rb = Ri.mod(TWO);
            randomSequence.add(Rb.intValue() == 1);
        }
        return randomSequence;
    }

    @Override
    public String getGeneratorDescription() {
        return "\nBBSGenerator";
    }
}