package no.westerdals.pg4100.lambdas.people;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        Random random = new Random();
        Gender[] genders = Gender.values();
        for(int i = 0; i < 100; i++) {
            String name = String.valueOf(i);
            final int age = random.nextInt(100);
            final int genderInt = random.nextInt(genders.length);
            Gender gender = genders[genderInt];
            people.add(new Person(name, age, gender));
        }

        final long malesAtLeastFifty = people
                .stream()
                .filter(p -> p.getGender() == Gender.MALE)
                .filter(p -> p.getAge() >= 50)
                .count();
        System.out.println("We found " + malesAtLeastFifty + " males that are at least fifty years old.");
        final long femalesBetweenTwentyAndThirty = people
                .stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .filter(p -> {
                    int age = p.getAge();
                    return age >= 20 && age <= 30;
                })
                .count();
        System.out.println("We found " + femalesBetweenTwentyAndThirty + " females that are between twenty and thirty years old");

        Map<String, Integer> peopleWithUnspecifiedGenders = people
                .stream()
                .filter(p -> p.getGender() == Gender.UNSPECIFIED)
                .collect(Collectors.toMap(Person::getName, Person::getAge));
        peopleWithUnspecifiedGenders.entrySet().forEach(e -> System.out.println(e.getKey() + " is " + e.getValue() + " years old"));
    }
}
