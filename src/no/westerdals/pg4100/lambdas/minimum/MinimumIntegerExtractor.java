package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

@FunctionalInterface
public interface MinimumIntegerExtractor {
    public int getMinimum(final List<Integer> integers);
}
