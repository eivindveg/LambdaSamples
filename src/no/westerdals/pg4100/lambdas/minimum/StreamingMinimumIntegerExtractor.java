package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

public class StreamingMinimumIntegerExtractor implements MinimumIntegerExtractor {

    @Override
    public int getMinimum(final List<Integer> integers) {
        return integers
                .parallelStream()
                .mapToInt(Integer::intValue)    // mapToInt, not just map: we want an IntStream, not a Stream<Integer>
                .min()
                .orElseThrow(() -> new UnsupportedOperationException("Cannot extract minimum value: List is empty"));
    }
}
