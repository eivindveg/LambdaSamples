package no.westerdals.pg4100.lambdas.people;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(final String[] args) {
        final List<Person> people = new ArrayList<>();
        final Random random = new Random();
        final Gender[] genders = Gender.values();

        // Make some people
        for(int i = 0; i < 100; i++) {
            String name = String.valueOf(i);
            final int age = random.nextInt(100);
            final int genderInt = random.nextInt(genders.length);
            final Gender gender = genders[genderInt];
            people.add(new Person(name, age, gender));
        }

        // Count of males that are at least 50
        final long malesAtLeastFifty = people
                .stream()
                .filter(p -> p.getGender() == Gender.MALE)
                .filter(p -> p.getAge() >= 50)
                .count();
        System.out.println("We found " + malesAtLeastFifty + " males that are at least fifty years old.");

        // Count of prime females
        final long femalesBetweenTwentyAndThirty = people
                .stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .filter(p -> {
                    int age = p.getAge();
                    return age >= 20 && age <= 30;
                })
                .count();
        System.out.println("We found " + femalesBetweenTwentyAndThirty + " females that are between twenty and thirty years old");

        // And at last, a map of everyone who doesn't identify with a gender,
        // flattened to name: age
        final Map<String, Integer> peopleWithUnspecifiedGenders = people
                .stream()
                .filter(p -> p.getGender() == Gender.UNSPECIFIED)
                .collect(Collectors.toMap(Person::getName, Person::getAge));

        // Print that map
        peopleWithUnspecifiedGenders
                .entrySet()
                .forEach(e -> System.out.println(e.getKey() + " is " + e.getValue() + " years old"));
    }
}
