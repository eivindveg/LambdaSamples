package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

public class StreamingMinimumIntegerExtractor implements MinimumIntegerExtractor {

    @Override
    public int getMinimum(final List<Integer> integers) {
        return integers
                .parallelStream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new UnsupportedOperationException("Cannot extract minimum value: List is empty"));
    }
}
