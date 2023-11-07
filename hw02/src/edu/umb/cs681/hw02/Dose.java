package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.util.Random;

public class Dose {
    private String vacProductName;
    private String lotNumber;
    private LocalDate vaccinationDate;
    private String vaccinationSite;

    public Dose(String vacProductName, String lotNumber, LocalDate vaccinationDate, String vaccinationSite) {
        this.vacProductName = vacProductName;
        this.lotNumber = lotNumber;
        this.vaccinationDate = vaccinationDate;
        this.vaccinationSite = vaccinationSite;
    }

    public String getVacProductName() {
        return vacProductName;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public String getVaccinationSite() {
        return vaccinationSite;
    }


    public static Dose createRandomDose() {
        String vacProductName = getRandomVacProductName();
        String lotNumber = getRandomLotNumber();
        LocalDate vaccinationDate = getRandomVaccinationDate();
        String vaccinationSite = getRandomVacSite();
        return new Dose(vacProductName, lotNumber, vaccinationDate, vaccinationSite);
    }

    private static String getRandomVacProductName() {
        String[] products = {"Moderna", "Pfizer", "J&J", "AstraZeneca", "Novavax"};
        return products[new Random().nextInt(products.length)];
    }

    private static String getRandomLotNumber() {
        return "LOT" + new Random().nextInt(1000);
    }

    private static LocalDate getRandomVaccinationDate() {
        int minDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        long randomDay = minDay + new Random().nextInt(maxDay - minDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private static String getRandomVacSite() {
        String[] sites = {"Left Arm", "Right Arm"};
        return sites[new Random().nextInt(sites.length)];
    }
}
