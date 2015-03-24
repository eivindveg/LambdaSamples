package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

/**
 * Simple implementation that uses Java 8's Streaming API to calculate the average
 */
public class StreamingSquareRootPowerAverager implements SquareRootPowerAverager {
    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        return numbers
                // Run concurrently
                .parallelStream()
                // Turn the stream into a DoubleStream; this allows us to average
                .mapToDouble(Number::doubleValue)
                // Take the square root of the value
                .map(Math::sqrt)
                // Map the value to the power of the given power
                .map(d -> Math.pow(d, power))
                // Return the average
                .average()
                // (or, if the list was empty, 0.0)
                .orElse(0.0);
    }
}
