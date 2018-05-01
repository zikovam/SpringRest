package com.zikovam.rest;

import java.util.ArrayList;
import java.util.List;

class IndividualsMockedData {
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

    /**
     * Get all entries from list
     *
     * @return all stored individuals
     */
    public List<Individual> fetchIndividuals(){
        return individuals;
    }

    /**
     * Getting Individual by SNILS
     *
     * @param snils - SNILS for searching
     * @return finded individual
     */
    public Individual getIndividualBySnils (String snils){
        for (Individual individual :
                individuals) {
            if (individual.getSnils().equals(snils)){
                return individual;
            }
        }
        return null;
    }

    /**
     * Create one Individual, and put it in the list
     *
     * @param name - name of the individual
     * @param snils - SNILS of the individual
     * @param dateBirth - BirthDate of the individual
     * @return new individual
     */
    public Individual createIndividual(String name, String snils, String dateBirth){
        //if we already store such snils, we need to send an error
        if (getIndividualBySnils(snils) == null) {
            int id = idGenerator();
            Individual individual = new Individual(id, name, snils, dateBirth);
            individuals.add(individual);
            changeChecker++;
            return individual;
        } else {
            return null;
        }
    }

    /**
     * Updating one individual from the list
     *
     * @param name - new name of the individual
     * @param snils - new SNILS of the individual
     * @param dateBirth - new BirthDate of the individual
     * @return updated individual
     */
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

    /**
     * Getting current number of changes
     *
     * @return ChangeChecker counter
     */
    int getChangeChecker () {
        return changeChecker;
    }

    /**
     * Resetting ChangeChecker counter
     */
    void resetChangeChecker () {
        changeChecker = 0;
    }

    /**
     * Getting size of the individuals list
     *
     * @return number of entries in the individuals list
     */
    int getEntriesNum () {
        return individuals.size();
    }

    /**
     * Method for generating ubique id
     * Working by adding +1 to max id in current list of entries
     * (Yes, this isn't the most productive way)
     *
     * @return unique id for saving new individual
     */
    private int idGenerator(){
        int id = 4;
        for (Individual individual :
                individuals) {
            if (individual.getId()>=id){
                id = individual.getId()+1;
            }
        }
        return id;
    }
}
