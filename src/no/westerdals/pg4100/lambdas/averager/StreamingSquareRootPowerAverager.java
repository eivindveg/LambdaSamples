package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

/**
 * Simple implementation that uses Java 8's Streaming API to calculate the average
 */
public class StreamingSquareRootPowerAverager implements SquareRootPowerAverager {
    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        return numbers
                .parallelStream()                   // Run concurrently
                .mapToDouble(Number::doubleValue)   // Turn the stream into a DoubleStream; this allows us to average
                .map(Math::sqrt)                    // Take the square root of the value
                .map(d -> Math.pow(d, power))       // Map the value to the power of the given power
                .average()                          // Return the average
                .orElse(0.0);                       // (or, if the list was empty, 0.0)
    }
}
