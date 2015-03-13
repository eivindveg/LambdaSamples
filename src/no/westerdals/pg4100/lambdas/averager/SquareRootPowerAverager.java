package no.westerdals.pg4100.lambdas.averager;

import java.util.List;

public interface SquareRootPowerAverager {
    public double getAverage(final List<? extends Number> numbers, final int power);
}
