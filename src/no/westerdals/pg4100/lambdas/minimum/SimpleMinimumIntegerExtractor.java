package no.westerdals.pg4100.lambdas.minimum;

import java.util.List;

public class SimpleMinimumIntegerExtractor implements MinimumIntegerExtractor {

    @Override
    public int getMinimum(final List<Integer> integers) {
        int min = 0;
        for (final Integer integer : integers) {
            if(integer < min) {
                min = integer;
            }
        }
        return min;
    }
}
