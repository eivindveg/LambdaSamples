package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

public class StreamingSquareRootPowerAverager implements SquareRootPowerAverager {
    @Override
    public double getAverage(final List<? extends Number> numbers, final int power) {
        return numbers
                .parallelStream()
                .mapToDouble(Number::doubleValue)
                .map(Math::sqrt)
                .map(d -> Math.pow(d, power))
                .average()
                .orElse(0.0);
    }
}
