package no.westerdals.pg4100.lambdas.people;

public class Person {

    private String name;
    private int age;
    private Gender gender;

    public Person(final String name, final int age, final Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
