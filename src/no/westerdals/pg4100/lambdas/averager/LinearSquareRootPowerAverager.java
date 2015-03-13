package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

/**
 * Simple implementation that simply iterates through the list to find the average.
 */
public class LinearSquareRootPowerAverager implements SquareRootPowerAverager {

    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        double sum = 0;
        for (final Number number : numbers) {
            final double sqrt = Math.sqrt(number.doubleValue());
            final double pow = Math.pow(sqrt, power);
            sum += pow;
        }
        return sum / (double) numbers.size();
    }
}
