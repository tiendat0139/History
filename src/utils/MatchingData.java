package utils;

import com.google.gson.Gson;
import models.Person;
import models.Place;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchingData {

    public static List<Person> getPerson() {
        Gson gson = new Gson();
        List<Person> personList = new ArrayList<>();
        try {
            Person[] list;
            FileReader reader = new FileReader("src/data/nhanvat.json");
            list = gson.fromJson(reader, Person[].class);
            personList = Arrays.asList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public static ArrayList<Person> getRelatedPerson(List<String> personNames) {
        List<Person> personList = getPerson();
        ArrayList<Person> relatedPerson = new ArrayList<>();

        // find person in person data by person's name
        for (String personName : personNames) {
            boolean found = false;
            for (Person person : personList) {
                if (person.getTen().contains(personName)) {
                    relatedPerson.add(person);
                    found = true;
                }
            }
            if (!found) {
                Person newPerson = new Person();
                newPerson.setTen(personName);
                relatedPerson.add(newPerson);
            }
        }
        return relatedPerson;
    }

    public static Place getRelatedPlace(String placeName) {
        Gson gson = new Gson();
        List<Place> placeList = new ArrayList<>();
        try {
            Place[] list;
            FileReader reader = new FileReader("src/data/diadanh.json");
            list = gson.fromJson(reader, Place[].class);
            placeList = Arrays.asList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Place relatedPlace = new Place();
        boolean found = false;
        for (Place place : placeList) {
            if (place.getTenDiaDiem().contains(placeName)) {
                relatedPlace = place;
                found = true;
            }
        }
        if (!found) {
            relatedPlace.setTenDiaDiem(placeName);
        }
        return relatedPlace;
    }

    public static ArrayList<Person> getPersonByPeriodName(String periodName) {
        List<Person> personList = getPerson();
        ArrayList<Person> relatedPerson = new ArrayList<>();

        // find person in person data by person's name
        for (Person person : personList) {
            if (person.getTrieuDai() != null &&
                    periodName.contains(person.getTrieuDai())) {
                relatedPerson.add(person);
            }
        }
        return relatedPerson;
    }
}
