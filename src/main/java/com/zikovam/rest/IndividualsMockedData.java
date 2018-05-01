package com.zikovam.rest;

import java.util.ArrayList;
import java.util.List;

public class IndividualsMockedData {
    private List<Individual> individuals;
    //counter for changes (every 100 changes will start a delay)
    private int changeChecker;

    //Creating singleton by private static inner class
    private static class SingletonHelper{
        private static final IndividualsMockedData INSTANCE = new IndividualsMockedData();
    }
    public static IndividualsMockedData getInstance(){
        return SingletonHelper.INSTANCE;
    }

    //MORE DATA TO THE GOD OF DATA
    private IndividualsMockedData (){
        individuals = new ArrayList<>();
        changeChecker = 0;
        individuals.add(new Individual(1, "Pupkin Vasiliy Gennadyevich", "123-456-789-01", "01.01.1970"));
        individuals.add(new Individual(2, "Popkin Ivan Vasilyevich", "463-436-384-46", "03.04.1980"));
        individuals.add(new Individual(3, "Kovalev Iliya Izrailevich", "199-900-381-81", "22.10.1990"));
    }

    //get all individuals
    public List<Individual> fetchIndividuals(){
        return individuals;
    }

    //get Individuals by SNILS
    public Individual getIndividualBySnils (String snils){
        for (Individual individual :
                individuals) {
            if (individual.getSnils().equals(snils)){
                return individual;
            }
        }
        return null;
    }

    //create Individual
    public Individual createIndividual(String name, String snils, String dateBirth){
        //TODO: add id generator
        int id = 4;
        Individual individual = new Individual(id, name, snils, dateBirth);
        individuals.add(individual);
        changeChecker++;
        return individual;
    }

    public Individual updateIndividual (String name, String snils, String dateBirth){
        for (Individual individual:
                individuals){
            if (individual.getSnils().equals(snils)){
                //we need to save index of our individual
                int index = individuals.indexOf(individual);

                individual.setName(name);
                individual.setSnils(snils);
                individual.setDateBirth(dateBirth);

                individuals.set(index, individual);
                changeChecker++;
                return individual;
            }
        }
        return null;
    }

    //some additional methods for technical use
    public int getChangeChecker () {
        return changeChecker;
    }
    public int getEntriesNum () {
        return individuals.size();
    }
}
