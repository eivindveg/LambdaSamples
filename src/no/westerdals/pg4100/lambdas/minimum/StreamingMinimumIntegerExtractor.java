package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

public class StreamingMinimumIntegerExtractor implements MinimumIntegerExtractor {

    @Override
    public int getMinimum(final List<Integer> integers) {
        return integers.parallelStream()
                // mapToInt, not just map: we want an IntStream, not a Stream<Integer>
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new UnsupportedOperationException(LIST_EMPTY));
    }
}
