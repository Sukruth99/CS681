package edu.umb.cs681.hw02;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private List<Dose> doses;

    public Person(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.doses = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public List<Dose> getDoses() {
        return doses;
    }

    public void addDose(Dose dose) {
        doses.add(dose);
    }
    public int getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
    public int getVacCount() {
        return this.doses.size();
    }
    public AgeCat getAgeCat() {
        int age = Period.between(this.dob, LocalDate.now()).getYears();
        if (age >= 65) {
            return AgeCat.OLD;
        } else if (age >= 30) {
            return AgeCat.MID;
        } else {
            return AgeCat.YOUNG;
        }
    }


    public static List<Person> initializePeople(int count) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            people.add(createRandomPerson());
        }
        return people;
    }

    private static Person createRandomPerson() {
        String firstName = getRandomFirstName();
        String lastName = getRandomLastName();
        LocalDate dob = getRandomDOB();

        Person person = new Person(firstName, lastName, dob);

        int dosesCount = new Random().nextInt(4);
        for (int i = 0; i < dosesCount; i++) {
            person.addDose(Dose.createRandomDose());
        }

        return person;
    }

    private static String getRandomFirstName() {
        String[] firstNames = {"rahul", "Jane", "Madarchod", "sasuke", "Kim"};
        return firstNames[new Random().nextInt(firstNames.length)];
    }

    private static String getRandomLastName() {
        String[] lastNames = {"reddy", "uchiha", "karsprov", "xiu", "sharma"};
        return lastNames[new Random().nextInt(lastNames.length)];
    }

    private static LocalDate getRandomDOB() {
        int minDay = (int) LocalDate.of(1950, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2018, 12, 31).toEpochDay();
        long randomDay = minDay + new Random().nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static void main(String[] args) {
        List<Person> people = initializePeople(1000);

    }
}
