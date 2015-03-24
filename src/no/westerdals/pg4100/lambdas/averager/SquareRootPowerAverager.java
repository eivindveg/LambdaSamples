package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

/**
 * This interface is used to get the average value of numbers in a list,
 * using first the square root of each value, then increasing the exponent to a given power.
 */
@FunctionalInterface
public interface SquareRootPowerAverager {
    public double getAverage(final List<? extends Number> numbers, final int power);
}
