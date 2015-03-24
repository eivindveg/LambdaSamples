package no.westerdals.pg4100.lambdas.ewclean.seed;

import no.westerdals.pg4100.lambdas.ewclean.models.Item;
import no.westerdals.pg4100.lambdas.ewclean.models.Line;

import java.util.List;
import java.util.Random;

public class Seeder {

    public static void seedList(final List<Line> listToSeed, int count) {
        final Random random = new Random();
        for(int i = 0; i < count; i++) {
            final int itemsToAdd = random.nextInt(100) + 1;
            final double itemValueBias = random.nextDouble() * 100;

            Line line = new Line("Line" + i);
            listToSeed.add(line);
            for(int j = 0; j < itemsToAdd; j++) {
                Item item = getRandomItemWithBias(itemValueBias);
                line.addItem(item);
            }
        }
    }


    public static Item getRandomItemWithBias(Double bias) {
        final Random random = new Random();
        int variation = random.nextInt(10);
        if(random.nextBoolean()) {
            variation -= variation;
        }
        final double itemValue = random.nextDouble() + variation + bias;
        final Item item = new Item();
        item.setValue(itemValue);
        return item;
    }
}
