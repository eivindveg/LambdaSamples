package no.westerdals.pg4100.lambdas.averager;

import no.westerdals.pg4100.lambdas.averager.SquareRootPowerAverager;
import no.westerdals.pg4100.lambdas.averager.ConcurrentSquareRootPowerAverager;
import no.westerdals.pg4100.lambdas.averager.LinearSquareRootPowerAverager;
import no.westerdals.pg4100.lambdas.averager.StreamingSquareRootPowerAverager;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        final int power = 3;
        final int size = 100000000;
        List<Integer> numbers = new ArrayList<>(size * 2);
        System.out.println("Setting up list");
        for(int i = 0; i < size; i++) {
            numbers.add(i);
        }
        System.out.println("List set up");

        timeAverager(new LinearSquareRootPowerAverager(), numbers, power);
        timeAverager(new ConcurrentSquareRootPowerAverager(), numbers, power);
        timeAverager(new StreamingSquareRootPowerAverager(), numbers, power);
    }

    public static void timeAverager(SquareRootPowerAverager averager, List<? extends Number> numbers, final int power) {
        final long stamp = System.currentTimeMillis();
        double average = averager.getAverage(numbers, power);
        final double seconds = (double)(System.currentTimeMillis() - stamp) / 1000;
        DecimalFormat decimalFormat = new DecimalFormat("0.###", new DecimalFormatSymbols(Locale.getDefault()));
        decimalFormat.setGroupingSize(3);
        decimalFormat.setGroupingUsed(true);

        System.out.println(averager.getClass().getSimpleName() + " calculated " + decimalFormat.format(average) + " for " + decimalFormat.format(numbers.size()) + " numbers in " + decimalFormat.format(seconds) + " seconds");
    }
}
