package com.zikovam.rest;

import static org.junit.Assert.*;

public class IndividualsMockedDataTest {

    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();

    @org.junit.Test
    public void fetchIndividuals () {
        assertEquals(individualsMockedData.getEntriesNum(), 3);
    }

    @org.junit.Test
    public void getIndividualByActualSnils () {
        //SNILS of default person "Pupkin Vasiliy Gennadyevich", "123-456-789-01", "01.01.1970"
        final String SNILS = "123-456-789-01";
        Individual individual = individualsMockedData.getIndividualBySnils(SNILS);
        assertEquals(individual.getDateBirth(), "01.01.1970");
        assertEquals(individual.getName(), "Pupkin Vasiliy Gennadyevich");
    }

    @org.junit.Test
    public void getIndividualByNonExistingSnils () {
        final String SNILS = "666-666-666-66";
        Individual individual = individualsMockedData.getIndividualBySnils(SNILS);
        assertNull(individual);
    }

    @org.junit.Test
    public void createIndividual () {
        final String snils = "666-666-666-66";
        final String name = "Testovyi Test Testovich";
        final String dateBirth = "06.06.2006";
        Individual result = individualsMockedData.createIndividual(name, snils, dateBirth);
        assertEquals(result.getName(), name);
        assertEquals(individualsMockedData.getEntriesNum(), 4);
        assertEquals(result.getId(), 4);
    }

    @org.junit.Test
    public void updateIndividual () {
        System.out.println(individualsMockedData.getEntriesNum());
    }
}
