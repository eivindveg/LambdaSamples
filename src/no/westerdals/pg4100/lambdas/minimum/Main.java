package no.westerdals.pg4100.lambdas.minimum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final List<Integer> integers = new ArrayList<>();
        final Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            boolean positive = random.nextBoolean();
            if (positive) {
                integers.add(random.nextInt());
            } else {
                integers.add(-random.nextInt());
            }
        }
        Collections.shuffle(integers);
        extractMinimum(new SimpleMinimumIntegerExtractor(), integers);
        extractMinimum(new StreamingMinimumIntegerExtractor(), integers);

    }

    public static void extractMinimum(final MinimumIntegerExtractor extractor, final List<Integer> integers) {
        final int minimum = extractor.getMinimum(integers);
        System.out.println(extractor.getClass().getSimpleName() + " extracted " + minimum);
    }
}
