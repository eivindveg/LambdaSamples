package no.westerdals.pg4100.lambdas.ewclean.seed;

import no.westerdals.pg4100.lambdas.ewclean.models.Item;
import no.westerdals.pg4100.lambdas.ewclean.models.Line;

import java.util.List;
import java.util.Random;

public class Seeder {

    public static void seedList(final List<Line> listToSeed, int count) {
        Random r = new Random();
        for(int i = 0; i < count; i++) {
            final int itemsToAdd = r.nextInt(100);
            final double itemValueBias = r.nextDouble() * 100;

            Line line = new Line("Line" + i);
            listToSeed.add(line);
            for(int j = 0; j < itemsToAdd; j++) {
                double itemValue = r.nextDouble() * itemValueBias;
                Item item = new Item();
                item.setPeriod(j);
                item.setValue(itemValue);

                line.addItem(item);
            }
        }
    }
}
