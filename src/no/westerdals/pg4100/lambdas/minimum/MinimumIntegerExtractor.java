package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

@FunctionalInterface
public interface MinimumIntegerExtractor {

    static final String LIST_EMPTY = "Cannot extract minimum value: List is empty";

    public int getMinimum(final List<Integer> integers);
}
