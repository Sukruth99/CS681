package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = Person.initializePeople(1000);

        Function<Person, String> ageCategory = person -> {
            int age = Period.between(person.getDob(), LocalDate.now()).getYears();
            if (age <= 12) return "0-12";
            else if (age <= 19) return "13-19";
            else if (age <= 29) return "20-29";
            else if (age <= 39) return "30-39";
            else if (age <= 49) return "40-49";
            else if (age <= 59) return "50-59";
            else return "60+";
        };


        Map<AgeCat, Long> countByCategory = people.stream()
                .collect(Collectors.groupingBy(Person::getAgeCat, Collectors.counting()));

        Map<AgeCat, Long> vaccinatedCountByCategory = people.stream()
                .filter(p -> !p.getDoses().isEmpty())
                .collect(Collectors.groupingBy(Person::getAgeCat, Collectors.counting()));

        Map<AgeCat, Double> vaccinationRateByCategory = vaccinatedCountByCategory.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> (double) e.getValue() / countByCategory.get(e.getKey()) * 100));

        Map<AgeCat, Double> averageVaccinationsByCategory = people.stream()
                .collect(Collectors.groupingBy(Person::getAgeCat,
                        Collectors.averagingInt(p -> p.getDoses().size())));

        OptionalDouble averageAgeNeverVaccinated = people.stream()
                .filter(p -> p.getDoses().isEmpty())
                .mapToInt(p -> Period.between(p.getDob(), LocalDate.now()).getYears())
                .average();

        vaccinationRateByCategory.forEach((age, rate) ->
                System.out.println("Vaccination Rate for age category " + age + ": " + rate + "%"));

        averageVaccinationsByCategory.forEach((age, avgVaccinations) ->
                System.out.println("Average Vaccinations for age category " + age + ": " + avgVaccinations));

        averageAgeNeverVaccinated.ifPresent(avgAge ->
                System.out.println("Average age of people who have never been vaccinated: " + avgAge));

    }
}
