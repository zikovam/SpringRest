package com.zikovam.rest;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class IndividualsMockedDataTest {

    private IndividualsMockedData individualsMockedData = IndividualsMockedData.getInstance();
    private static final String SNILS_NEW = "666-666-666-66";
    private static final String SNILS_EXISTED = "123-456-789-01";

    @Before
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        // Get the private field
        final Field field = individualsMockedData.getClass().getDeclaredField("individuals");
        field.setAccessible(true);
        // Sets the field to the new value for this instance
        final List<Individual> individuals = new ArrayList<>();
        individuals.add(new Individual(1, "Pupkin Vasiliy Gennadyevich", "123-456-789-01", "01.01.1970"));
        individuals.add(new Individual(2, "Popkin Ivan Vasilyevich", "463-436-384-46", "03.04.1980"));
        individuals.add(new Individual(3, "Kovalev Iliya Izrailevich", "199-900-381-81", "22.10.1990"));
        field.set(individualsMockedData, individuals);
    }

    @Test
    public void getAllEntries () {
        assertEquals(individualsMockedData.getEntriesNum(), 3);
    }

    @Test
    public void getIndividualByActualSnils () {
        //SNILS_NEW of default person "Pupkin Vasiliy Gennadyevich", "123-456-789-01", "01.01.1970"
        Individual individual = individualsMockedData.getIndividualBySnils(SNILS_EXISTED);
        assertEquals(individual.getDateBirth(), "01.01.1970");
        assertEquals(individual.getName(), "Pupkin Vasiliy Gennadyevich");
    }

    @Test
    public void getIndividualByNonExistingSnils () {
        Individual individual = individualsMockedData.getIndividualBySnils(SNILS_NEW);
        assertNull(individual);
    }

    @Test
    public void createActualIndividual () {
        final String name = "Testovyi Test Testovich";
        final String dateBirth = "06.06.2006";
        Individual result = individualsMockedData.createIndividual(name, SNILS_NEW, dateBirth);
        assertEquals(result.getName(), name);
        assertEquals(individualsMockedData.getEntriesNum(), 4);
        assertEquals(result.getId(), individualsMockedData.fetchIndividuals().size());
    }

    @Test
    public void createDuplicateIndividual () {
        final String name = "Testovyi Test Testovich";
        final String dateBirth = "06.06.2006";
        Individual result = individualsMockedData.createIndividual(name, SNILS_EXISTED, dateBirth);
        assertNull(result);
    }

    @Test
    public void updateActualIndividual () {
        final String name = "Tuleev Viktor Fedorovich";
        final String dateBirth = "01.05.1941";

        Individual individual = individualsMockedData.updateIndividual(name, SNILS_EXISTED, dateBirth);
        assertEquals(individual, individualsMockedData.getIndividualBySnils(SNILS_EXISTED));
        assertEquals(individual.getId(), 1);
    }

    @Test
    public void updateNotExistedIndividual () {
        final String name = "Tuleev Viktor Fedorovich";
        final String dateBirth = "01.05.1941";

        Individual individual = individualsMockedData.updateIndividual(name, SNILS_NEW, dateBirth);
        assertNull(individual);
    }

}
