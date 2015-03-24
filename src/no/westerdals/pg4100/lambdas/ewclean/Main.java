package no.westerdals.pg4100.lambdas.ewclean;

import no.westerdals.pg4100.lambdas.ewclean.models.Item;
import no.westerdals.pg4100.lambdas.ewclean.models.Line;
import no.westerdals.pg4100.lambdas.ewclean.models.LineValidation;
import no.westerdals.pg4100.lambdas.ewclean.seed.Seeder;
import no.westerdals.pg4100.lambdas.ewclean.workers.LineValidationBuilder;
import no.westerdals.pg4100.lambdas.ewclean.workers.LineValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Seed the lines
        List<Line> lines = new ArrayList<>();
        Seeder.seedList(lines, 100);
        lines.forEach(l -> Collections.sort(l.getItems(), (o1, o2) -> Double.compare(o1.getValue(), o2.getValue())));

        // Set up validations for the lines
        LineValidationBuilder builder = new LineValidationBuilder();
        lines.stream().forEach(builder::update);

        // Print lines neatly
        lines.forEach(line -> {
            Item item = Seeder.getRandomItemWithBias(line.getLineValidation().getMin());
            
            LineValidator validator = new LineValidator();
            boolean itemValidForLine = validator.isItemValidForLine(line, item);
            printLineValidation(line);
            System.out.println("\tCould add item with value " + item.getValue() + ": " + itemValidForLine);
        });
    }

    public static void printLineValidation(Line line) {
        LineValidation lineValidation = line.getLineValidation();
        if(lineValidation == null) {
            System.out.println("Line " + line.getTitle() + " has no validation");
        } else {
            System.out.println(lineValidation);
        }
    }
}
